package listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyRequestListener implements ServletRequestListener {
	//将当前session对象存放到List中
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		//获取当前request
		HttpServletRequest request=(HttpServletRequest)sre.getServletRequest();
		//获取当前ip
		String clientIp=request.getRemoteAddr();
		//获取当前session对象
		HttpSession currentSession=request.getSession();
		//获取全局域
		ServletContext sc=sre.getServletContext();
		//从全局域中获取map
		Map<String,List<HttpSession>> map = (Map<String,List<HttpSession>>)sc.getAttribute("map");
		//从map中获取当前ip所发出的所有session组成的List
		List<HttpSession> sessions=map.get(clientIp);
		//判断当前的list是否为null。若为null，则创建List。否则，将当前session放入list
		if (sessions==null) {
			sessions=new ArrayList<>();
		}
		//遍历List，若list中存在当前session对象，则不用操作list
		for (HttpSession session : sessions) {
			if (session==currentSession) {
				return;
			}
		}
		//将当前session放入list
		sessions.add(currentSession);
		//将变化后的list写入到map
		map.put(clientIp, sessions);
		//将变化后的map写回到全局域
		sc.setAttribute("map",map);
		
		//将当前ip放入到当前session
		currentSession.setAttribute("clientIp", clientIp);
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}
}
