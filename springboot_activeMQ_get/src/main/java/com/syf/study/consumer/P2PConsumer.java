package com.syf.study.consumer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class P2PConsumer {
	//�ݵ���
	@JmsListener(destination="${my_queue}")
	public void receive(String msg) {
		System.out.println("��Ե������߳ɹ��յ���Ϣ��"+msg);
	}
	

}
