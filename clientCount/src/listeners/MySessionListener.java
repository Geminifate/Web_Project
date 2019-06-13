package listeners;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext sc=se.getSession().getServletContext();
		Integer count=(Integer)sc.getAttribute("count");
		count++;
		sc.setAttribute("count", count);
	}
	//将当前session对象从list中删除
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//获取当前session对象
		HttpSession currentSession=se.getSession();
		//从当前session中获取ip
		String clientIp=(String)currentSession.getAttribute("clientIp");
		//获取全局域
		ServletContext sc=currentSession.getServletContext();
		//从全局域中获取map
		Map<String,List<HttpSession>> map=(Map<String,List<HttpSession>>)sc.getAttribute("map");
		//从map中获取list
		List<HttpSession> sessions=map.get(clientIp);
		//从list中删除当前session对象
		sessions.remove(currentSession);
		//若list中没有元素，则当前ip所发出的会话全部关闭，可以从map中删除
		//若list仍有元素，则当前ip所发出的会话还存在，则将变化后的list写回到map
		if (sessions.size()==0) {
			map.remove(clientIp);
		}else {
			map.put(clientIp, sessions);
		}
		//将map写回到全局域
		sc.setAttribute("map", map);
	}

}
