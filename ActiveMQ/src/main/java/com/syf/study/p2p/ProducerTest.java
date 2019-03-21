package com.syf.study.p2p;

import java.util.Random;

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
	private static String myQueue="my_queue";
	
	public static void main(String[] args) throws JMSException, InterruptedException {
		//1.创建连接工厂
		ActiveMQConnectionFactory fa=new ActiveMQConnectionFactory(url);
		//2.创建连接
		Connection connection = fa.createConnection();
		connection.start();//启动连接
		
		//3.创建会话
		//参数1:设置是否需要以事务方式提交
				//true:需要提交事务才会将session中的队列提交到消息队列容器中
				//false:不需要提交事务，producer.send的时候会自动提交到队列容器中
		//参数2：消息方式，默认采用自动签收(如果发送者设置手动签收，不会影响消费者的签收方式)
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		//4.创建目标（主题）
		Queue queue = session.createQueue(myQueue);

		//5.创建生产者
		MessageProducer producer = session.createProducer(queue);
		//设置消息是否持久化，默认是持续化
		//PERSISTENT（持续化）
		//NON_PERSISTENT（非持续化）
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		for (int i = 201; i < 220; i++) {
			//6.创建消息
			TextMessage text=session.createTextMessage("主题内容："+i);
			System.out.println(text);
			//Thread.currentThread().sleep(10000);
			//7.发送消息
			producer.send(text);
			session.commit();
		}
		//8.消息发送完毕
		connection.close();
	}
	
}
