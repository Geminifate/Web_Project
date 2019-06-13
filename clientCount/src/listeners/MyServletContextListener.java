package listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

public class MyServletContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//创建一个Map，key为ip，value为该ip上所发出的会话对象
		Map<String,List<HttpSession>> map = new HashMap();
		//获取ServletContext，即全局域对象
		ServletContext sc=sce.getServletContext();
		//将map放入到全局域中
		sc.setAttribute("map", map);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
}
