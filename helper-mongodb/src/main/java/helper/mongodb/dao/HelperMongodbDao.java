package helper.mongodb.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import common.ObjectUtil;
import common.orm.query.param.Param;
import common.util.JAnnotationUtils;

@Transactional
public abstract class HelperMongodbDao {

	protected MongoTemplate template;

	public HelperMongodbDao(MongoTemplate template) {
		// TODO Auto-generated constructor stub
		this.template = template;
	}

	private DBObject generateMatchPipeline(Class<?> clazz, Object object) throws Exception {
		List<Field> fields = JAnnotationUtils.findFields(clazz,
				org.springframework.data.mongodb.core.mapping.Field.class, null);
		Field primary = JAnnotationUtils.findFields(clazz, org.springframework.data.annotation.Id.class, null).get(0);

		List<DBObject> conditions = new ArrayList<DBObject>();
		for (Field field : fields) {
			if (PropertyUtils.getProperty(object, field.getName()) != null) {
				DBObject eq = new BasicDBObject("$regex", PropertyUtils.getProperty(object, field.getName()));
				DBObject and = new BasicDBObject(field.getName(), eq);
				conditions.add(and);
			}
		}
		ObjectUtil.isPresent(PropertyUtils.getProperty(object, primary.getName()), value ->{
			DBObject eq = new BasicDBObject("$regex",
					value);
			DBObject and = new BasicDBObject("_id", eq);
			conditions.add(and);
		});

		DBObject and = new BasicDBObject("$and", conditions);
		return new BasicDBObject("$match", and);

	}

	public <T> List<T> get(Param<T> param) throws Exception {

		Class<T> clazz = param.getModelClass();
		Object object = param.getModel();
		Class<?> paramClass = param.getParamClass();
		
		List<DBObject> pipelines = new ArrayList<>();

		if (object != null) {
			pipelines.add(generateMatchPipeline(paramClass, object));
		}

		if (param.getLimit() != null) {
			DBObject limit = new BasicDBObject("$limit", param.getLimit());
			pipelines.add(limit);
		}
		if (param.getOffset() != null) {
			DBObject skip = new BasicDBObject("$skip", param.getOffset());
			pipelines.add(skip);
		}
		final AggregationOutput aggregate = template.getCollection(paramClass.getAnnotation(Document.class).collection())
				.aggregate(pipelines);

		List<T> results = new ArrayList<T>();
		for (DBObject dbobject : aggregate.results()) {
			results.add(template.getConverter().read(clazz, dbobject));
		}
		return results;
	}

	public Long getCount(Param<?> param) throws Exception {
		List<DBObject> pipelines = new ArrayList<>();
		ObjectUtil.isPresent(param.getModel(), value -> pipelines.add(generateMatchPipeline(param.getParamClass(), param.getModel())));

		DBObject group = new BasicDBObject("_id", null);
		group.put("count", new BasicDBObject("$sum", 1));
		pipelines.add(new BasicDBObject("$group", group));

		final AggregationOutput aggregate = template.getCollection(param.getParamClass().getAnnotation(Document.class).collection())
				.aggregate(pipelines);
		if(aggregate.results().iterator().hasNext()){
			return Long.valueOf((Integer) aggregate.results().iterator().next().get("count"));
		} else {
			return 0L;
		}
		
	}

	public <T> T save(Class<T> clazz, Object object) throws Exception {
		template.save(object);
		return clazz.cast(object);
	}
}
