package cn.itcasr.shop.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcasr.shop.adminuser.vo.AdminUser;
//没有登录的用户不可访问
public class PrivilegeTnterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//判断session是否保存了后台用户的信息
		AdminUser existAdminUser= (AdminUser) ActionContext.getContext().getSession().get("existAdminUser");
		if (existAdminUser==null) {
			//没登录
			ActionSupport actionSupport=(ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("请先登录");
			return "loginFail";
		}else {
			return actionInvocation.invoke();
		}
	}
	
}
