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
	//幂等性
	@JmsListener(destination="${my_queue}")
	public void receive(String msg) {
		if(StringUtils.isEmpty(msg)) {
			return;
		}
		JSONObject j=JSONObject.parseObject(msg);
		String userName = j.getString("userName");
		String email = j.getString("email");
		System.out.println("名字="+userName+";邮箱="+email);
		try {
			sendSimpleMail(email, userName);
		} catch (Exception e) {
			System.err.println("邮件发送失败："+msg);
			e.printStackTrace();
		}

		System.out.println("点对点消费者成功收到消息："+msg);
	}


	public void sendSimpleMail(String eamil, String userName) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		// 邮件来自 自己发自己
		message.setFrom(eamil);
		// 发送给谁
	//	message.setTo("");
		message.setTo("xxx@qq.com");
		// 邮件标题
		message.setSubject("1233211234567");
		// 邮件内容
		message.setText("祝贺您,成为了我们" + userName + ",学员!");
		// 发送邮件
		javaMailSender.send(message);
		System.out.println("邮件发送完成," + JSONObject.toJSONString(message));
	}

}
