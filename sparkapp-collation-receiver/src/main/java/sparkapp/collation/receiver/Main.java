package sparkapp.collation.receiver;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import sparkapp.collation.receiver.config.AppConfig;
import sparkapp.collation.receiver.service.LogReceiver;

@Component
public class Main {

	@Resource(name="${Main.receiver}")
	private LogReceiver receiver;
	
	public static void main(String[] args) throws Exception {
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	    Main p = ctx.getBean(Main.class);
	    p.run(args);
	}
	
	public void run(String[] args) throws Exception{
		receiver.run();
	}
}
