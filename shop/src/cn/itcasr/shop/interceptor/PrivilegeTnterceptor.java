package cn.itcasr.shop.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcasr.shop.adminuser.vo.AdminUser;
//û�е�¼���û����ɷ���
public class PrivilegeTnterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//�ж�session�Ƿ񱣴��˺�̨�û�����Ϣ
		AdminUser existAdminUser= (AdminUser) ActionContext.getContext().getSession().get("existAdminUser");
		if (existAdminUser==null) {
			//û��¼
			ActionSupport actionSupport=(ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("���ȵ�¼");
			return "loginFail";
		}else {
			return actionInvocation.invoke();
		}
	}
	
}
