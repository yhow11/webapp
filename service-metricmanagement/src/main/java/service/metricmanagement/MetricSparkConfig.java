package service.metricmanagement;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import helper.spring.CustomFilter;




@Configuration
@EnableTransactionManagement
@PropertySource("classpath:service/metricmanagement/sparkconfig.properties")
public class MetricSparkConfig implements ApplicationContextAware {

	@Value("#{'${metric.component.classes}'.split(',')}") 
	private List<String> classes;
	
	@Value("${metric.component.package}")
	private String packageName;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry) arg0.getAutowireCapableBeanFactory();
		ClassPathBeanDefinitionScanner scanner =
				new ClassPathBeanDefinitionScanner(registry, false);
		scanner.addIncludeFilter(new CustomFilter(classes));
		System.out.println(arg0.getBeanDefinitionCount());
		System.out.println(scanner.scan(packageName));
		System.out.println(arg0.getBeanDefinitionCount());
	}
}
