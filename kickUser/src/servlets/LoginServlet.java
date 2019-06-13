package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û��ύ���������
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String ageStr = request.getParameter("age");
		Integer age=Integer.valueOf(ageStr);
		
		//����User����
		User user = new User(name,age);
		
		//��ȡ��ǰ�����Ӧ��Session
		HttpSession session = request.getSession();
		
		//��User��Session��
		session.setAttribute("user", user);
		//��ת����ӭҳ��
		response.sendRedirect(request.getContextPath()+"/welcome.jsp");
	}

}
