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
		//1.��ȡ������
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
		//2.����student����
		Student student=new Student(num, name, age, score);
		student.setPassword(password);
		//3.����service����
		IStudentService service =new StudentServiceImpl();
		//4.����service�����saveStudent����������д�뵽DB
		Integer id=service.saveStudent(student);
		System.out.println(id);
		//5.д��ʧ�ܣ�����ת��ע��ҳ�棬����ע��
		if (id==null) {
			response.sendRedirect(request.getContextPath()+"/register.jsp");
			return;
		}
		//6.д��ɹ�������ת����¼ҳ��
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}

}
