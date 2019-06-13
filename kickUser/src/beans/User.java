package beans;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {
	private String name;
	private int age;
	public User() {
	}
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		//��ȡ��ǰsession
		HttpSession session = event.getSession();
		//��ȡȫ����
		ServletContext sc = session.getServletContext();
		//��ȫ�����л�ȡmap
		Map<String,HttpSession> map = (Map<String,HttpSession>)sc.getAttribute("map");
		//����ǰ�û���������session���뵽map��
		map.put(name, session);
		//��mapд��ȫ����
		sc.setAttribute("map", map);
	}
	@Override
	public String toString() {
		return "User [name=" + name + ",age=" + age +"]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
	}
	
}
