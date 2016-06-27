package com.fingerprint.util.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fingerprint.util.service.impl.KafkaService;

@Controller
@RequestMapping("kafka")
public class KafkaController {

	@Autowired
	private KafkaService kafkaService;
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() throws ExecutionException, InterruptedException {
		kafkaService.send("test test test test jofel jofel jofel");
        return "";
    }
}
