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
	//����ǰsession�����list��ɾ��
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//��ȡ��ǰsession����
		HttpSession currentSession=se.getSession();
		//�ӵ�ǰsession�л�ȡip
		String clientIp=(String)currentSession.getAttribute("clientIp");
		//��ȡȫ����
		ServletContext sc=currentSession.getServletContext();
		//��ȫ�����л�ȡmap
		Map<String,List<HttpSession>> map=(Map<String,List<HttpSession>>)sc.getAttribute("map");
		//��map�л�ȡlist
		List<HttpSession> sessions=map.get(clientIp);
		//��list��ɾ����ǰsession����
		sessions.remove(currentSession);
		//��list��û��Ԫ�أ���ǰip�������ĻỰȫ���رգ����Դ�map��ɾ��
		//��list����Ԫ�أ���ǰip�������ĻỰ�����ڣ��򽫱仯���listд�ص�map
		if (sessions.size()==0) {
			map.remove(clientIp);
		}else {
			map.put(clientIp, sessions);
		}
		//��mapд�ص�ȫ����
		sc.setAttribute("map", map);
	}

}
