package cn.itcasr.shop.user.action;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcasr.shop.user.service.UserService;
import cn.itcasr.shop.user.vo.User;

public class UserAction extends ActionSupport implements ModelDriven<User>,Serializable{
	//模型驱动使用的对象
	private User user = new User();
	private UserService userService;
	//接收验证码
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Override
	public User getModel() {
		return user;
	}
	//跳转到注册页面
	public String registPage() {
		return "registPage";
	}
	//AJAX异步校验用户名
	public String findByName() throws IOException {
		//调用service查询
		System.out.println("执行findByName()");
		User existUser = userService.findByUserName(user.getUsername());
		//获得response对象，向页面输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if (existUser!=null) {
			response.getWriter().println("<font color='red'>用户名已存在</font>");
		}else {
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}
	//用户注册
	public String regist() {
		//判断验证码
		//从session中获得验证码
		String code1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(code1)) {
			System.out.println(checkcode+"    "+code1);
			this.addActionError("验证码错误");
			return "checkcodeFail";
		}
		userService.save(user);
		return "msg";
	}
	
	//用户激活
	public String active() {
		User existuser=userService.findByCode(user.getCode());
		if (existuser==null) {
			this.addActionMessage("激活失败");
		}else {
			existuser.setState(1);
			existuser.setCode(null);
			userService.update(existuser);
			this.addActionMessage("激活成功");
		}
		return "msg";
	}
	public String loginPage() {
		return "loginPage";
	}
	public String login() {
		User existuser=userService.login(user);
		if (existuser==null) {
			this.addActionError("登录失败");
			return LOGIN;
		}else {
			//登录成功
			//将用户信息存入到session中
			
			ActionContext.getContext().getSession().put("existuser", existuser);
			//页面跳转
			return "loginSuccess";
		}	
	}
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
