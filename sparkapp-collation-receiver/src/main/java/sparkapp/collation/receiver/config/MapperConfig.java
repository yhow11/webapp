package sparkapp.collation.receiver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import usertracker.browser.mapper.impl.VisitorLogRowMapper;
import usertracker.browser.mapper.impl.VisitorLogStringMapper;

@Configuration
public class MapperConfig {

	@Bean
	public VisitorLogStringMapper visitorLogStringMapper() throws Exception{
		return new VisitorLogStringMapper();
	}
	
	@Bean
	public VisitorLogRowMapper visitorLogRowMapper() throws Exception{
		return new VisitorLogRowMapper();
	}
	
	
}
