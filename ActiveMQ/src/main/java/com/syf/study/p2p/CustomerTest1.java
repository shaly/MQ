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
		System.out.println("����������2");
		//1.�������ӹ���
		ActiveMQConnectionFactory fa=new ActiveMQConnectionFactory(url);
		//2.��������
		Connection connection = fa.createConnection();
		connection.start();//��������
		
		//3.�����Ự
		//����1:�����Ƿ���Ҫ��ʵ�﷽ʽ�ύ
		//����2����Ϣ��ʽ��Ĭ�ϲ����Զ�ǩ��
				//AUTO_ACKNOWLEDGE:�Զ�ǩ�գ�JMSĬ�����Զ�ǩ��
				//CLIENT_ACKNOWLEDGE:�ֶ�ǩ��
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		//4.����Ŀ�꣨���У�
		Queue queue = session.createQueue(myQueue);

		//5.����������
		MessageConsumer consumer = session.createConsumer(queue);
		//6.�������� ������Ϣ
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage textMessage=(TextMessage)message;
				try {
					System.out.println("������Ϣ��"+textMessage.getText());
					System.out.println(textMessage.toString());
					
					//�ֶ�����ȷ��ǩ�գ�������Ϣ�м���Ѿ����ѳɹ�,
					//����������ֶ�ǩ�գ���Ϣ���ɴ����м����δ������
					textMessage.acknowledge();
				} catch (JMSException e) {
					System.err.println("������Ϣ����"+textMessage);
					e.printStackTrace();
				}
			}
		});
		//��Ҫ�ر�����
	}
	
}
