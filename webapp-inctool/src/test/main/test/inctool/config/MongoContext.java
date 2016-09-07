package test.inctool.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
@PropertySource("classpath:com/inctool/properties/application.properties")
public class MongoContext extends AbstractMongoConfiguration
{

	@Value("${mongo.host}")
	private String host;

	@Value("${mongo.port}")
	private Integer port;
	
	@Value("${mongo.db}")
	private String db;
	
    @Override
    protected String getDatabaseName() {
        return db;
    }

    @Override
    public Mongo mongo() throws Exception {
    	return new MongoClient(host, port);
    }

    
}
