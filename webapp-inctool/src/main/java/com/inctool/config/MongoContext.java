package com.inctool.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;

import com.inctool.ScanMarker;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
@ComponentScan(basePackageClasses= ScanMarker.class, includeFilters={ @ComponentScan.Filter(Controller.class) } )
@PropertySource("classpath:com/inctool/properties/application.properties")
public class MongoContext extends AbstractMongoConfiguration
{


    @Override
    protected String getDatabaseName() {
        return "demo";
    }

    @Override
    public Mongo mongo() throws Exception {
    	return new MongoClient("188.166.214.159", 27017);
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.johnathanmarksmith.mongodb.example.domain";
    }

    
}
