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
		//获取当前session
		HttpSession session = event.getSession();
		//获取全局域
		ServletContext sc = session.getServletContext();
		//从全局域中获取map
		Map<String,HttpSession> map = (Map<String,HttpSession>)sc.getAttribute("map");
		//将当前用户名放入与session放入到map中
		map.put(name, session);
		//将map写回全局域
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
