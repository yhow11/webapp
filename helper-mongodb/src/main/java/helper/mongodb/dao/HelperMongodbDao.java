package helper.mongodb.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.AggregationOutput;
import com.mongodb.DBObject;

public abstract class HelperMongodbDao {

	protected MongoTemplate template;
	
	public HelperMongodbDao(MongoTemplate template) {
		// TODO Auto-generated constructor stub
		this.template = template;
	}
	public <T> List<T> getAll(Class<T> clazz, DBObject pipeline, DBObject... pipelines) throws Exception {
		final AggregationOutput aggregate = template.getCollection(clazz.getAnnotation(Document.class).collection()).aggregate(pipeline, pipelines);

		List<T> results = new ArrayList<T>();
		for(DBObject dbobject: aggregate.results()){
			results.add(template.getConverter().read(clazz, dbobject));
		}
		return results;
	}
	
	public <T> T save(Class<T> clazz, Object object) throws Exception {
		template.save(object);
		return clazz.cast(object);
	}
}
