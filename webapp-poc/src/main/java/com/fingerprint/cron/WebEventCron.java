package com.fingerprint.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import usertracker.browser.service.VisitorLogService;

@Component
@PropertySource("classpath:com/fingerprint/properties/cron.properties")
public class WebEventCron {

	@Autowired
	private VisitorLogService visitorLogService;
	
	
	@Scheduled(fixedRateString = "${cron.webeventview.update}")
	public void updateWebEventView() {
	    System.out.println("Fixed rate task - " + System.currentTimeMillis()/1000);
	}
}
