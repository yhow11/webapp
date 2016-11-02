package com.nurtureretargeting.cron;

import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:cron.properties")
public class WebEventCron {

	
	@Scheduled(fixedRateString = "${cron.webeventview.update}")
	public void updateWebEventView() {
	    System.out.println("Fixed rate task - " + System.currentTimeMillis()/1000);
	}
}
