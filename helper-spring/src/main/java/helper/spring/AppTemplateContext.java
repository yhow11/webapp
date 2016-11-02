package helper.spring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public abstract class AppTemplateContext implements ApplicationContextAware {

	private List<Properties> props = new ArrayList<>();
	
	public AppTemplateContext(String... files) throws IOException {
		// TODO Auto-generated constructor stub
		for(String file: files){
			props.add(PropertiesLoaderUtils.loadAllProperties(file));
		}
	}
	
	public Map<String, String> componentProp() throws IOException{
		Map<String, String> map = new HashMap<>();
		for(Properties prop: props){
			String packageName = prop.getProperty(String.valueOf(prop.keySet().stream().filter(item->String.valueOf(item).contains("component.package")).findFirst().get()));
			String classes = prop.getProperty(String.valueOf(prop.keySet().stream().filter(item->String.valueOf(item).contains("component.classes")).findFirst().get()));
			map.put(packageName, classes);
		}
		return map;
	}
	
	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
		PropertySourcesPlaceholderConfigurer source = new PropertySourcesPlaceholderConfigurer();
		source.setPropertiesArray(props.toArray(new Properties[]{}));
		return source;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry) arg0.getAutowireCapableBeanFactory();
		try {
			for(String packageName: componentProp().keySet()){
				List<String> classes = Arrays.asList(((String) componentProp().get(packageName)).split(","));
				ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry, false);
				scanner.addIncludeFilter(new CustomFilter(classes));
				System.out.println(scanner.scan(packageName));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
