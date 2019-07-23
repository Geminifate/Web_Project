package cn.itcasr.shop.category.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import cn.itcasr.shop.category.vo.Category;

public class CategoryDao{
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public List<Category> findAll() {
		String hql ="from Category";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		return list;
	}
	public void save(Category category) {
		sessionFactory.getCurrentSession().save(category);
	}
	public Category findByCid(Integer cid) {
		String hql="from Category c where c.cid=?0";
		Category category = (Category) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, cid).uniqueResult();
		return category;
	}
	public void delete(Category category) {
		sessionFactory.getCurrentSession().delete(category);
	}
	public void update(Category category) {
		sessionFactory.getCurrentSession().update(category);
	}
	
}
