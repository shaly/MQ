package com.syf.study.producer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class P2PProducer {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;
	
	@Scheduled(fixedDelay=5000)
	public void send() {
		String userName=System.currentTimeMillis()+"";
		JSONObject j=new JSONObject();
		j.put("userName", userName);
		j.put("email", "yushengjun6442018@163.com");
		String msg=j.toJSONString();
		jmsMessagingTemplate.convertAndSend(queue,msg);
		System.out.println("采用点对点"+msg);
	}
	

}
