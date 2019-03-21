package com.syf.study.producer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class P2PProducer {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;
	
	@Scheduled(fixedDelay=5000)
	public void send() {
		String time=System.currentTimeMillis()+"";
		jmsMessagingTemplate.convertAndSend(queue,time);
		System.out.println("采用点对点"+time);
	}
	

}
