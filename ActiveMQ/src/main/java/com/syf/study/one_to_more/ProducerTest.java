package com.syf.study.one_to_more;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;import javax.swing.text.AbstractDocument.DefaultDocumentEvent;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerTest {

	private static String url="tcp://127.0.0.1:61616";
	private static String myTopic="my_topic";
	
	public static void main(String[] args) throws JMSException {
		//1.创建连接工厂
		ActiveMQConnectionFactory fa=new ActiveMQConnectionFactory(url);
		//2.创建连接
		Connection connection = fa.createConnection();
		connection.start();//启动连接
		
		//3.创建会话
		//参数1:设置是否需要以实物方式提交
		//参数2：消息方式，默认采用自动签收
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//4.创建目标（主题）
		Topic topic = session.createTopic(myTopic);

		//5.创建生产者
		MessageProducer producer = session.createProducer(topic);
		//producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		for (int i = 90; i < 100; i++) {
			//6.创建消息
			TextMessage text=session.createTextMessage("主题内容："+i);
			System.out.println(text);
			//7.发送消息
			producer.send(text);
		}
		//8.消息发送完毕
		connection.close();
	}
	
}
