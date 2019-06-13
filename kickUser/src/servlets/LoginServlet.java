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
		//获取用户提交的请求参数
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String ageStr = request.getParameter("age");
		Integer age=Integer.valueOf(ageStr);
		
		//创建User对象
		User user = new User(name,age);
		
		//获取当前请求对应的Session
		HttpSession session = request.getSession();
		
		//将User和Session绑定
		session.setAttribute("user", user);
		//跳转到欢迎页面
		response.sendRedirect(request.getContextPath()+"/welcome.jsp");
	}

}
