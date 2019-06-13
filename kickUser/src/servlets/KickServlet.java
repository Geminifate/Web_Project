package servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class KickServlet
 */
@WebServlet("/kickServlet")
public class KickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡȫ����
		ServletContext sc = request.getSession().getServletContext();
		//��ȫ�����л�ȡmap
		Map<String, HttpSession> map =(Map<String, HttpSession>)sc.getAttribute("map");
		//��ȡ���������Ҫ�޳����û�����
		String name = request.getParameter("name");
		//��map���л�ȡ��ǰ�û�����Ӧ��Session
		HttpSession session = map.get(name);
		//ʹsessionʧЧ
		session.invalidate();
		//�����û���Ӧ��entry��map��ɾ��
		map.remove(name);
		//����indexҳ��
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

}
