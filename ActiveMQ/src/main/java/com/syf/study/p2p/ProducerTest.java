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
		//1.�������ӹ���
		ActiveMQConnectionFactory fa=new ActiveMQConnectionFactory(url);
		//2.��������
		Connection connection = fa.createConnection();
		connection.start();//��������
		
		//3.�����Ự
		//����1:�����Ƿ���Ҫ������ʽ�ύ
				//true:��Ҫ�ύ����ŻὫsession�еĶ����ύ����Ϣ����������
				//false:����Ҫ�ύ����producer.send��ʱ����Զ��ύ������������
		//����2����Ϣ��ʽ��Ĭ�ϲ����Զ�ǩ��(��������������ֶ�ǩ�գ�����Ӱ�������ߵ�ǩ�շ�ʽ)
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		//4.����Ŀ�꣨���⣩
		Queue queue = session.createQueue(myQueue);

		//5.����������
		MessageProducer producer = session.createProducer(queue);
		//������Ϣ�Ƿ�־û���Ĭ���ǳ�����
		//PERSISTENT����������
		//NON_PERSISTENT���ǳ�������
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		for (int i = 201; i < 220; i++) {
			//6.������Ϣ
			TextMessage text=session.createTextMessage("�������ݣ�"+i);
			System.out.println(text);
			//Thread.currentThread().sleep(10000);
			//7.������Ϣ
			producer.send(text);
			session.commit();
		}
		//8.��Ϣ�������
		connection.close();
	}
	
}
