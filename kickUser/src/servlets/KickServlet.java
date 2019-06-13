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
		//获取全局域
		ServletContext sc = request.getSession().getServletContext();
		//从全局域中获取map
		Map<String, HttpSession> map =(Map<String, HttpSession>)sc.getAttribute("map");
		//获取请求参数（要剔除的用户名）
		String name = request.getParameter("name");
		//从map当中获取当前用户所对应的Session
		HttpSession session = map.get(name);
		//使session失效
		session.invalidate();
		//将该用户对应的entry从map中删除
		map.remove(name);
		//返回index页面
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

}
