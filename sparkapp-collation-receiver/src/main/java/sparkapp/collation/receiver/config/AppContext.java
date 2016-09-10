package sparkapp.collation.receiver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class AppContext {

	@Bean 
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	return new PropertySourcesPlaceholderConfigurer();
	}
}
