package com.syf.study.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueConfig {

	@Value("${my_queue}")
	private String myQueue1;
	
	//将队列注入到springboot容器中
	@Bean
	public Queue queue() {
		System.out.println(myQueue1);
		return new ActiveMQQueue(myQueue1);
	}
	
}
