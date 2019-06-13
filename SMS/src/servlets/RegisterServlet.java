package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Student;
import service.IStudentService;
import service.StudentServiceImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取表单参数
		request.setCharacterEncoding("UTF-8");
		String num=request.getParameter("num");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String ageStr=request.getParameter("age");
		String scoreStr=request.getParameter("score");
//		if (num==null||"".equals(num.trim())) {
//			
//		}
		Integer age=Integer.valueOf(ageStr);
		Double score=Double.valueOf(scoreStr);
		//2.创建student对象
		Student student=new Student(num, name, age, score);
		student.setPassword(password);
		//3.创建service对象
		IStudentService service =new StudentServiceImpl();
		//4.调用service对象的saveStudent（）将对象写入到DB
		Integer id=service.saveStudent(student);
		System.out.println(id);
		//5.写入失败，则跳转到注册页面，重新注册
		if (id==null) {
			response.sendRedirect(request.getContextPath()+"/register.jsp");
			return;
		}
		//6.写入成功，则跳转到登录页面
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}

}
