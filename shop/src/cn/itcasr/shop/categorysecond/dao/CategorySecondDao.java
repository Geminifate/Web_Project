package cn.itcasr.shop.categorysecond.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import cn.itcasr.shop.categorysecond.vo.CategorySecond;

public class CategorySecondDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql="from CategorySecond order by csid desc";
		List<CategorySecond> list=sessionFactory.getCurrentSession().getSession().createQuery(hql).setFirstResult(begin).setMaxResults(limit).list();
		return list;
	}

	public int findCount() {
		String hql="select count(*) from CategorySecond";
		Long count=(Long) sessionFactory.getCurrentSession().getSession().createQuery(hql).uniqueResult();
		return count.intValue();
	}

	public void save(CategorySecond categorySecond) {
		sessionFactory.getCurrentSession().save(categorySecond);
	}

	public void delete(CategorySecond categorySecond) {
		sessionFactory.getCurrentSession().delete(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {
		String hql="from CategorySecond cs where cs.csid=?0";
		CategorySecond categorySecond=(CategorySecond) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, csid).uniqueResult();
		return categorySecond;
	}

	public void update(CategorySecond categorySecond) {
		sessionFactory.getCurrentSession().update(categorySecond);
	}

	public List<CategorySecond> findAll() {
		String hql="from CategorySecond";
		List<CategorySecond> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}
}
