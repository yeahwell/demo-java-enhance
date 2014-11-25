package com.yeahwell.demo.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	private static final int SENDER_NUMBER = 5;
	
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = null;
		
		Connection connection = null;
		
		Session session = null;
		
		Destination destination = null;
		
		MessageProducer producer = null;
		
		//构建ConnectionFactory对象
		connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,
				"tcp://localhost:61616");
		try{
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		    destination = session.createQueue("FirstQueue");
		    producer = session.createProducer(destination);
		    //设置不持久化
		    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		    sendMessage(session, producer);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(null != connection){
					connection.close();
				}
			}catch(Throwable ignore){
				
			}
		}
		
	}
	
	public static void sendMessage(Session session, MessageProducer producer) throws JMSException{
		for(int i = 1; i <= SENDER_NUMBER; i++){
			TextMessage message = session.createTextMessage("ActiveMQ 发送的消息"  + i);
			System.out.println("发送消息  ActiveMQ发送的消息" + i);
			producer.send(message);
		}
	}
	
}
