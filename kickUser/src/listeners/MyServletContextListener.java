package listeners;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//����һ��Map��keyΪ�û�����valueΪ�뵱ǰ�û��󶨵�Session����
		Map<String, HttpSession> map =new HashMap();
		//��ȡ��ȫ����
		ServletContext sc =sce.getServletContext();
		//��Map���뵽ȫ����
		sc.setAttribute("map", map);
	}
	
}
