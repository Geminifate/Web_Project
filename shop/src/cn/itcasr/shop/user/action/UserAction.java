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
	//ģ������ʹ�õĶ���
	private User user = new User();
	private UserService userService;
	//������֤��
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
	//��ת��ע��ҳ��
	public String registPage() {
		return "registPage";
	}
	//AJAX�첽У���û���
	public String findByName() throws IOException {
		//����service��ѯ
		System.out.println("ִ��findByName()");
		User existUser = userService.findByUserName(user.getUsername());
		//���response������ҳ�����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if (existUser!=null) {
			response.getWriter().println("<font color='red'>�û����Ѵ���</font>");
		}else {
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
	}
	//�û�ע��
	public String regist() {
		//�ж���֤��
		//��session�л����֤��
		String code1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(code1)) {
			System.out.println(checkcode+"    "+code1);
			this.addActionError("��֤�����");
			return "checkcodeFail";
		}
		userService.save(user);
		return "msg";
	}
	
	//�û�����
	public String active() {
		User existuser=userService.findByCode(user.getCode());
		if (existuser==null) {
			this.addActionMessage("����ʧ��");
		}else {
			existuser.setState(1);
			existuser.setCode(null);
			userService.update(existuser);
			this.addActionMessage("����ɹ�");
		}
		return "msg";
	}
	public String loginPage() {
		return "loginPage";
	}
	public String login() {
		User existuser=userService.login(user);
		if (existuser==null) {
			this.addActionError("��¼ʧ��");
			return LOGIN;
		}else {
			//��¼�ɹ�
			//���û���Ϣ���뵽session��
			
			ActionContext.getContext().getSession().put("existuser", existuser);
			//ҳ����ת
			return "loginSuccess";
		}	
	}
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
