package cn.itcasr.shop.adminuser.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import cn.itcasr.shop.adminuser.vo.AdminUser;

public class AdminUserDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public AdminUser login(AdminUser adminUser) {
		String hql="from AdminUser where username=?0 and password=?1";
		List<AdminUser> list=sessionFactory.getCurrentSession().getSession().createQuery(hql).setParameter(0, adminUser.getUsername()).setParameter(1,adminUser.getPassword()).list();
		if (list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
