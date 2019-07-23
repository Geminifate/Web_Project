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
		//1.获得一个session对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@shop.com","ccc");
			}
		});
		//2.创建一个代表邮件的对象Message
		Message message =new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("service@shop.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject("账号激活");
			message.setContent("<h1>点击链接激活账号</h1><h3><a href='http://192.168.192.1:8080/shop/user_active.action?code="+code+"'>http://192.168.192.1:8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			//3.发送邮件Transport
			Transport.send(message);
			System.out.println("发送完成");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
