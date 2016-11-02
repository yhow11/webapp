package sparkapp.collation.receiver;

import javax.annotation.Resource;

import org.apache.spark.api.java.function.VoidFunction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import sparkapp.collation.receiver.config.AppConfig;
import sparkapp.collation.receiver.impl.LogReceiver;

@Component
public class Main {

	@Resource(name="${Main.receiver}")
	private VoidFunction<String> receiver;
	
	public static void main(String[] args) throws Exception {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	    Main p = ctx.getBean(Main.class);
	    p.run(args);
	}
	
	public void run(String[] args) throws Exception{
		receiver.call(null);
	}
}
