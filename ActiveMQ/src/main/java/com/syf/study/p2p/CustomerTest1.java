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
		//����1:�����Ƿ���Ҫ������ʽ�ύ
		//����2����Ϣ��ʽ��Ĭ�ϲ����Զ�ǩ��
				//AUTO_ACKNOWLEDGE:�Զ�ǩ�գ�JMSĬ�����Զ�ǩ��
				//CLIENT_ACKNOWLEDGE:�ֶ�ǩ��
		final Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
		//4.����Ŀ�꣨���У�
		Queue queue = session.createQueue(myQueue);

		//5.����������
		MessageConsumer consumer = session.createConsumer(queue);
		final int i=1;
		//6.�������� ������Ϣ
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage textMessage=(TextMessage)message;
				try {
					System.out.println("������Ϣ��"+textMessage.getText());
					System.out.println(textMessage.toString());
					
					//�ֶ�����ȷ��ǩ�գ�������Ϣ�м���Ѿ����ѳɹ�,
					//����������ֶ�ǩ�գ���Ϣ���ɴ����м����δ������
//					if("�������ݣ�2".equals(textMessage.getText())||"9".equals(textMessage.getText())) {
//						System.out.println("*�����ˣ�"+textMessage.getText());
//						textMessage.acknowledge();
//					}else {
//						System.out.println("***"+(textMessage.getText().equals("1"))+"û������"+textMessage.getText());
//					}
//					textMessage.acknowledge();
					
					
					
					
					if("�������ݣ�1".equals(textMessage.getText())||"9".equals(textMessage.getText())) {
						System.out.println("*�����ˣ�"+textMessage.getText());
						session.commit();
					}else {
						System.out.println("û������"+textMessage.getText());
					}
//					session.commit();
				} catch (JMSException e) {
					System.err.println("������Ϣ����"+textMessage);
					e.printStackTrace();
				}
			}
		});
		//��Ҫ�ر�����
	}
	
}
