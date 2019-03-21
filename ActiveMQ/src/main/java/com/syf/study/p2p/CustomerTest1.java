package com.syf.study.p2p;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class CustomerTest1 {

	private static String url="tcp://127.0.0.1:61616";
	private static String myQueue="my_queue";
	
	public static void main(String[] args) throws JMSException {
		System.out.println("我是消费者2");
		//1.创建连接工厂
		ActiveMQConnectionFactory fa=new ActiveMQConnectionFactory(url);
		//2.创建连接
		Connection connection = fa.createConnection();
		connection.start();//启动连接
		
		//3.创建会话
		//参数1:设置是否需要以实物方式提交
		//参数2：消息方式，默认采用自动签收
				//AUTO_ACKNOWLEDGE:自动签收，JMS默认是自动签收
				//CLIENT_ACKNOWLEDGE:手动签收
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		//4.创建目标（队列）
		Queue queue = session.createQueue(myQueue);

		//5.创建消费者
		MessageConsumer consumer = session.createConsumer(queue);
		//6.启动监听 监听消息
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage textMessage=(TextMessage)message;
				try {
					System.out.println("消费消息："+textMessage.getText());
					System.out.println(textMessage.toString());
					
					//手动进行确认签收，告诉消息中间件已经消费成功,
					//如果不进行手动签收，消息依旧存在中间件中未被消费
					textMessage.acknowledge();
				} catch (JMSException e) {
					System.err.println("消费消息报错："+textMessage);
					e.printStackTrace();
				}
			}
		});
		//不要关闭连接
	}
	
}
