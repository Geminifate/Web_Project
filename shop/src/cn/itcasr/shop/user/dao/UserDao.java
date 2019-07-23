package cn.itcasr.shop.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import cn.itcasr.shop.user.vo.User;

public class UserDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public User findByUserName(String username) {
		String hql ="from User where username =?0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<User> list = query.setParameter(0,username).list();
		if (list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	public User findByCode(String code) {
		String hql ="from User where code =?0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<User> list = query.setParameter(0,code).list();
		if (list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	public void update(User existuser) {
		sessionFactory.getCurrentSession().update(existuser);
	}
	public User login(User user) {
		String hql ="from User where username =?0 and password=?1 and state=?2";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<User> list = query.setParameter(0,user.getUsername()).setParameter(1,user.getPassword()).setParameter(2,1).list();
		if (list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
