package sparkapp.collation.receiver.config;

import org.apache.phoenix.spark.SparkSqlContextFunctions;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.hive.HiveContext;
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
		SparkConf config = new SparkConf().setAppName(appName);
//		config.setMaster("local");
		return config;
	}
	
	@Bean
	public JavaSparkContext javaSparkContext(){
		return new JavaSparkContext(sparkConf());
	}
	
	@Bean
	public JavaStreamingContext javaStreamingContext(){
		return new JavaStreamingContext(javaSparkContext(), new Duration(3000));
	}
	
	@Bean
	public HiveContext hiveContext(){
		System.setProperty("hadoop.home.dir", "c:\\winutils\\");
		return new HiveContext(javaSparkContext());
	}
	
	@Bean
	public SparkSqlContextFunctions sparkSqlContextFunctions(){
		return new SparkSqlContextFunctions(hiveContext());
	}
}
