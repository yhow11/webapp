package sparkapp.collation.receiver.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import helper.spring.AppTemplateContext;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Import({ SparkConfig.class, KafkaConfig.class, PhoenixConfig.class, ZookeeperConfig.class })
public class AppConfig extends AppTemplateContext {

	public AppConfig() throws IOException {
		super("application.properties","usertracker.properties","metric.properties");
		// TODO Auto-generated constructor stub
	}
	
	@Bean
	public RestTemplate restTemplateService() {
		RestTemplate rt = new RestTemplate();

		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		rt.getMessageConverters().add(new StringHttpMessageConverter());
		return rt;
	}
}
