package sparkapp.collation.receiver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import sparkapp.collation.receiver.manager.PartialPageCountManager;

@Configuration
@PropertySource({"classpath:kafka.properties"})
public class ManagerConfig {

	@Value("${kafka.zookeepers}")
	private String zookeepers;
	
	@Autowired
	private SparkContext sparkContext;
	
	
	@Bean
	public PartialPageCountManager partialPageCountManager() throws Exception{
		return new PartialPageCountManager(sparkContext.sQLContext(), zookeepers);
	}
}
