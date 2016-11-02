package sparkapp.collation.receiver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import helper.spring.ZookeeperContext;

@Configuration
@PropertySource({"classpath:zookeeper.properties"})
public class ZookeeperConfig implements ZookeeperContext{
	
	@Value("${zookeeper.brokers}")
	private String brokers;
	
	@Override
	public String getZookeepers() {
		// TODO Auto-generated method stub
		return brokers;
	}
}
