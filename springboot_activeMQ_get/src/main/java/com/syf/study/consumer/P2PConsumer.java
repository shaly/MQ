package com.syf.study.consumer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class P2PConsumer {
	//幂等性
	@JmsListener(destination="${my_queue}")
	public void receive(String msg) {
		System.out.println("点对点消费者成功收到消息："+msg);
	}
	

}
