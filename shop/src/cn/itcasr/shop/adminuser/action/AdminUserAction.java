package cn.itcasr.shop.adminuser.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcasr.shop.adminuser.service.AdminUserService;
import cn.itcasr.shop.adminuser.vo.AdminUser;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	private AdminUser adminUser =new AdminUser();
	private AdminUserService adminUserService;
	@Override
	public AdminUser getModel() {
		return adminUser;
	}
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	public String login() {
		AdminUser existAdminUser= adminUserService.login(adminUser);
		if (existAdminUser==null) {
			addActionError("µÇÂ¼Ê§°Ü");
			return "loginFail";
		} else {
			ActionContext.getContext().getSession().put("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
		
	}
}
