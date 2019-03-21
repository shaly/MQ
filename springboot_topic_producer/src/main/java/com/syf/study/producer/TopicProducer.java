package com.syf.study.producer;

import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TopicProducer {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Topic topic;
	
	@Scheduled(fixedDelay=5000)
	public void send() {
		String time=System.currentTimeMillis()+"";
		jmsMessagingTemplate.convertAndSend(topic,time);
		System.out.println("采用发布订阅："+time);
	}
	

}
