package com.syf.study.p2p;

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
		Queue queue = session.createQueue(myQueue);

		//5.����������
		MessageProducer producer = session.createProducer(queue);
		for (int i = 0; i < 10; i++) {
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