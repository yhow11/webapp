package sparkapp.collation.receiver.config;

import java.util.Properties;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import usertracker.browser.model.VisitorLogModel;

@Configuration
@PropertySource({"classpath:phoenix.properties"})
public class PhoenixContext {

	@Value("${phoenix.driverclassname}")
	private String DRIVER_CLASS_NAME;
	
	@Value("${phoenix.url}")
	private String URL;
	
    @Autowired
    private AppContext configContext;
    
    @Bean
    public DriverManagerDataSource dataSource() {
    	System.setProperty("hadoop.home.dir", "c:\\winutils\\");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(URL);
        return dataSource;
    }
    @Bean
    public Random createRandom() {
        return new Random();
    }
    
    @Bean
    public SessionFactory sessionFactory() {
     
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
     
        sessionBuilder.addAnnotatedClasses(VisitorLogModel.class);
        sessionBuilder.addProperties(hibernateProperties());
        return sessionBuilder.buildSessionFactory();
    }

	@Bean
	public Properties hibernateProperties() {
	    Properties properties = new Properties();
//	    properties.put("hibernate.show_sql", "true");
//	    properties.put("hibernate.format_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//	    properties.put("hibernate.hbm2ddl.auto", "create-drop");
	    
	    return properties;
	}
	
    @Bean
    public HibernateTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory());
     
        return transactionManager;
    }

}
