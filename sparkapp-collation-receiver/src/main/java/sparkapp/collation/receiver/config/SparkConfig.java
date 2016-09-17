package sparkapp.collation.receiver.config;

import org.apache.phoenix.spark.SparkSqlContextFunctions;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:spark.properties"})
public class SparkConfig {
  
	@Value("${spark.appname}")
	private String appName;

	@Bean
	public SparkConf sparkConf(){
		return new SparkConf().setAppName(appName);
	}
	
	@Bean
	public JavaSparkContext javaSparkContext(){
		return new JavaSparkContext(sparkConf());
	}
	
	@Bean
	public JavaStreamingContext javaStreamingContext(){
		return new JavaStreamingContext(javaSparkContext(), new Duration(5000));
	}
	
	@Bean
	public SQLContext sQLContext(){
		return new SQLContext(javaSparkContext());
	}
	
	@Bean
	public SparkSqlContextFunctions sparkSqlContextFunctions(){
		return new SparkSqlContextFunctions(sQLContext());
	}
}
