package sparkapp.collation.receiver.config;

import java.util.Random;

import org.hibernate.SessionFactory;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import usertracker.browser.model.VisitorLogModel;

@Configuration
@EnableTransactionManagement
@ComponentScan({"usertracker.browser.dao"})
public class PhoenixContext {

    private static final String DRIVER_CLASS_NAME = "org.apache.phoenix.jdbc.PhoenixDriver";
    private static final String URL = "jdbc:phoenix:master,slave1:2181/hbase";

    @Autowired
    private ConfigContext configContext;
    
    @Bean
    public DriverManagerDataSource dataSource() {
    	System.setProperty("hadoop.home.dir", "c:\\winutils\\");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(URL);
        return dataSource;
    }
    @Bean
    public TransactionAwareDataSourceProxy dataSourceProxy() {
        TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy();
        dataSourceProxy.setTargetDataSource(dataSource());
        return dataSourceProxy;
    }

    @Bean
    public DataSourceConnectionProvider dataSourceConnectionProvider() {
        return new DataSourceConnectionProvider(dataSourceProxy());
    }

    @Bean
    public DefaultDSLContext defaultDSLContext() {
        return new DefaultDSLContext(dataSourceConnectionProvider(), SQLDialect.MYSQL);
    }

    @Bean
    public Random createRandom() {
        return new Random();
    }
    
    @Bean
    public SessionFactory sessionFactory() {
     
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
     
        sessionBuilder.addAnnotatedClasses(VisitorLogModel.class);
        sessionBuilder.addProperties(configContext.hibernateProperties());
        return sessionBuilder.buildSessionFactory();
    }
    
    @Bean
    public HibernateTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory());
     
        return transactionManager;
    }

}
