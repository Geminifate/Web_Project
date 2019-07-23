package cn.itcasr.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUitls {
	public static void sendMail(String to,String code) {
		//1.���һ��session����
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@shop.com","ccc");
			}
		});
		//2.����һ�������ʼ��Ķ���Message
		Message message =new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("service@shop.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject("�˺ż���");
			message.setContent("<h1>������Ӽ����˺�</h1><h3><a href='http://192.168.192.1:8080/shop/user_active.action?code="+code+"'>http://192.168.192.1:8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			//3.�����ʼ�Transport
			Transport.send(message);
			System.out.println("�������");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
