package com.syf.study.consumer;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener; 
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

@Component
public class P2PConsumer {
	@Autowired
	private JavaMailSender javaMailSender;
	//�ݵ���
	@JmsListener(destination="${my_queue}")
	public void receive(String msg) {
		if(StringUtils.isEmpty(msg)) {
			return;
		}
		JSONObject j=JSONObject.parseObject(msg);
		String userName = j.getString("userName");
		String email = j.getString("email");
		System.out.println("����="+userName+";����="+email);
		try {
			sendSimpleMail(email, userName);
		} catch (Exception e) {
			System.err.println("�ʼ�����ʧ�ܣ�"+msg);
			e.printStackTrace();
		}

		System.out.println("��Ե������߳ɹ��յ���Ϣ��"+msg);
	}


	public void sendSimpleMail(String eamil, String userName) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		// �ʼ����� �Լ����Լ�
		message.setFrom(eamil);
		// ���͸�˭
	//	message.setTo("");
		message.setTo("xxx@qq.com");
		// �ʼ�����
		message.setSubject("1233211234567");
		// �ʼ�����
		message.setText("ף����,��Ϊ������" + userName + ",ѧԱ!");
		// �����ʼ�
		javaMailSender.send(message);
		System.out.println("�ʼ��������," + JSONObject.toJSONString(message));
	}

}
