package sparkapp.collation.receiver.config;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hbase.dao.HBaseDao;
import hbase.dao.impl.HBaseDaoImpl;
import usertracker.browser.dao.VisitorLogDao;
import usertracker.browser.dao.impl.VisitorLogDaoImpl;

@Configuration
public class DaoConfig {
	
	@Bean
	public VisitorLogDao visitorLogDao() throws Exception{
		return new VisitorLogDaoImpl(HBaseDao());
	}
	
	 @Bean
	public org.apache.hadoop.conf.Configuration hbaseConfig(){
		 org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
			config.addResource(new Path("/etc/hbase/conf/hbase-site.xml"));
			config.addResource(new Path("/etc/hadoop/conf/core-site.xml"));
//			config.set("hbase.zookeeper.quorum", "188.166.183.50,103.253.145.213");
//			config.set("hbase.zookeeper.property.maxClientCnxns", "1000");
//			config.set("hbase.zookeeper.property.clientPort", "2181");
//			config.set("hbase.client.scanner.timeout.period", "90000");
//			config.set("hbase.master", "188.166.187.47:16000");
			
		return config;
	}
	public @Bean
	HBaseDao HBaseDao() throws Exception {
		
		return new HBaseDaoImpl(hbaseConfig());
		
	}
}
