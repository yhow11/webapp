package com.fingerprint.util.wsclient.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import com.fingerprint.util.wsclient.service.WebSocketService;

public class WebSocketServiceImpl implements WebSocketService {
	
	public WebSocketServiceImpl(StompSessionHandler handler){
		List<Transport> transports = new ArrayList<>(1);
		transports.add(new WebSocketTransport( new StandardWebSocketClient()) );
		WebSocketClient transport = new SockJsClient(transports);
		WebSocketStompClient stompClient = new WebSocketStompClient(transport);
		stompClient.setMessageConverter(new StringMessageConverter());
		String url = "ws://localhost:8080/webapp-poc/notify";
		stompClient.connect(url, handler);
	}

}
