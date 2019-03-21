package com.syf.study.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueConfig {

	@Value("${my_topic}")
	private String myTopic;
	
	//将队列注入到springboot容器中
	@Bean
	public Topic topic() {
		System.out.println(myTopic);
		return new ActiveMQTopic(myTopic);
	}
	
}
