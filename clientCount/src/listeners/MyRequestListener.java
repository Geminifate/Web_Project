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
	//����ǰsession�����ŵ�List��
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		//��ȡ��ǰrequest
		HttpServletRequest request=(HttpServletRequest)sre.getServletRequest();
		//��ȡ��ǰip
		String clientIp=request.getRemoteAddr();
		//��ȡ��ǰsession����
		HttpSession currentSession=request.getSession();
		//��ȡȫ����
		ServletContext sc=sre.getServletContext();
		//��ȫ�����л�ȡmap
		Map<String,List<HttpSession>> map = (Map<String,List<HttpSession>>)sc.getAttribute("map");
		//��map�л�ȡ��ǰip������������session��ɵ�List
		List<HttpSession> sessions=map.get(clientIp);
		//�жϵ�ǰ��list�Ƿ�Ϊnull����Ϊnull���򴴽�List�����򣬽���ǰsession����list
		if (sessions==null) {
			sessions=new ArrayList<>();
		}
		//����List����list�д��ڵ�ǰsession�������ò���list
		for (HttpSession session : sessions) {
			if (session==currentSession) {
				return;
			}
		}
		//����ǰsession����list
		sessions.add(currentSession);
		//���仯���listд�뵽map
		map.put(clientIp, sessions);
		//���仯���mapд�ص�ȫ����
		sc.setAttribute("map",map);
		
		//����ǰip���뵽��ǰsession
		currentSession.setAttribute("clientIp", clientIp);
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}
}
