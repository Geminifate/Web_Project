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
		//1.接受请求参数
		String num = request.getParameter("num");
		String password =request.getParameter("password");
		HttpSession session=request.getSession();
		if (num==null||"".equals(num.trim())) {
			session.setAttribute("message", "学号输入有误");
//			request.getRequestDispatcher("/login.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		if (password==null||"".equals(password.trim())) {
			session.setAttribute("message", "密码输入有误");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		//2.创建Service对象
		IStudentService service=new StudentServiceImpl();
		//3.调用Service对象的checkUser（）方法对用户进行验证
		Student student=service.checkUser(num,password);
		//4.验证未通过，则跳转到登录页面，让用户再次输入登录信息。此时页面需要给用户一些提示
		if (student==null) {
			session.setAttribute("message", "用户名或密码输入有误");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		//5.验证通过则跳转到系统主页indedex.jsp
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	

	

}
