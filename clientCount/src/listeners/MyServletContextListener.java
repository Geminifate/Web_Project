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
		//����һ��Map��keyΪip��valueΪ��ip���������ĻỰ����
		Map<String,List<HttpSession>> map = new HashMap();
		//��ȡServletContext����ȫ�������
		ServletContext sc=sce.getServletContext();
		//��map���뵽ȫ������
		sc.setAttribute("map", map);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
}
