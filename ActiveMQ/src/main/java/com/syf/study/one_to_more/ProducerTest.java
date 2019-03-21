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
		Topic topic = session.createTopic(myTopic);

		//5.����������
		MessageProducer producer = session.createProducer(topic);
		//producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		for (int i = 90; i < 100; i++) {
			//6.������Ϣ
			TextMessage text=session.createTextMessage("�������ݣ�"+i);
			System.out.println(text);
			//7.������Ϣ
			producer.send(text);
		}
		//8.��Ϣ�������
		connection.close();
	}
	
}
