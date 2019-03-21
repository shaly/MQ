package com.syf.study.one_to_more;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class CustomerTest2 {

	private static String url="tcp://127.0.0.1:61616";
	private static String myTopic="my_topic";
	
	public static void main(String[] args) throws JMSException {
		System.out.println("����������2");
		//1.�������ӹ���
		ActiveMQConnectionFactory fa=new ActiveMQConnectionFactory(url);
		//2.��������
		Connection connection = fa.createConnection();
		connection.start();//��������
		
		//3.�����Ự
		//����1:�����Ƿ���Ҫ��ʵ�﷽ʽ�ύ
		//����2����Ϣ��ʽ��Ĭ�ϲ����Զ�ǩ��
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//4.����Ŀ�꣨���⣩
		Topic createTopic = session.createTopic(myTopic);

		//5.����������
		MessageConsumer consumer = session.createConsumer(createTopic);
		//6.�������� ������Ϣ
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage textMessage=(TextMessage)message;
				try {
					System.out.println("������Ϣ��"+textMessage.getText());
					System.out.println(textMessage.toString());
				} catch (JMSException e) {
					System.err.println("������Ϣ������"+textMessage);
					e.printStackTrace();
				}
			}
		});
		//��Ҫ�ر�����
	}
	
}