package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Student;
import service.IStudentService;
import service.StudentServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.�����������
		String num = request.getParameter("num");
		String password =request.getParameter("password");
		HttpSession session=request.getSession();
		if (num==null||"".equals(num.trim())) {
			session.setAttribute("message", "ѧ����������");
//			request.getRequestDispatcher("/login.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		if (password==null||"".equals(password.trim())) {
			session.setAttribute("message", "������������");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		//2.����Service����
		IStudentService service=new StudentServiceImpl();
		//3.����Service�����checkUser�����������û�������֤
		Student student=service.checkUser(num,password);
		//4.��֤δͨ��������ת����¼ҳ�棬���û��ٴ������¼��Ϣ����ʱҳ����Ҫ���û�һЩ��ʾ
		if (student==null) {
			session.setAttribute("message", "�û�����������������");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		//5.��֤ͨ������ת��ϵͳ��ҳindedex.jsp
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	

	

}
