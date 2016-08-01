package helper.mongodb.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import helper.mongodb.util.mapper.DBObjectMapper;

public abstract class HelperMongodbDao<T> {

	protected MongoTemplate template;
	
	public HelperMongodbDao(MongoTemplate template) {
		// TODO Auto-generated constructor stub
		this.template = template;
	}
	public List<T> findAll(Map<String, String> parameters) throws Exception {
		Type type = getClass().getGenericSuperclass();
		Class<T> thisClass = null;
		if (type instanceof ParameterizedType) {
		    ParameterizedType parameterizedType = (ParameterizedType) type;
		    Type[] typeArguments = parameterizedType.getActualTypeArguments();
		    thisClass = (Class<T>) typeArguments[0];
		}
		
		List<DBObject> pipelines = new ArrayList<DBObject>();
		for(String key : parameters.keySet()){
			DBObject pipeline = new BasicDBObject(key, parameters.get(key));
			pipelines.add(pipeline);
		}
		DBObject[] test = new DBObject[pipelines.size()-1];
		for(int ctr = 0; ctr < pipelines.size(); ctr++){
			test[ctr] = pipelines.get(ctr+1);
		}
		
		final AggregationOutput aggregate = template.getCollection(thisClass.getAnnotation(Document.class).collection()).aggregate(pipelines.get(0), test);

		return DBObjectMapper.map(thisClass, aggregate.results());
	}
}
