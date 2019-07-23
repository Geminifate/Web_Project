package cn.itcasr.shop.product.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import cn.itcasr.shop.product.vo.Product;

public class ProductDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Product> findHot() {
		String hql="from Product where is_hot=1 order by is_hot desc";
		List<Product> list = sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(0).setMaxResults(10).list();
		return list;
	}

	public List<Product> findNew() {
		String hql="from Product where is_hot=1 order by pdate desc";
		List<Product> list = sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(0).setMaxResults(10).list();
		return list;
	}

	public Product findByPid(Integer pid) {
		String hql="from Product where pid=?0";
		return (Product) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, pid).uniqueResult();
	}

	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
//		String sql="select p.* from category c.categorySecond cs.product where c.cid=cs.cid and cs.csid=p.csid and c.cid=?0";
//		String hql="select p from Category c,CategorySecond cs,Product p where c.cid=cs.category.cid and cs.csid=p.categorySecond.csid and c.cid=?0";
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid=?0";
		List<Product> list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, cid).setFirstResult(begin).setMaxResults(limit).list();
		if (list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}

	public int findCountCid(Integer cid) {
		String hql="select count(*) from Product p where p.categorySecond.category.cid=?0";
		Long list = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, cid).uniqueResult();
		return list.intValue();
	}

	public int findCountCsid(int csid) {
		String hql="select count(*) from Product p where p.categorySecond.csid=?0";
		Long list = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, csid).uniqueResult();
		return list.intValue();
	}

	public List<Product> findByPageCsid(int csid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs where cs.csid=?0";
		List<Product> list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, csid).setFirstResult(begin).setMaxResults(limit).list();
		if (list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}

	public int findCount() {
		String hql="select count(*) from Product p";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		return count.intValue();
	}

	public List<Product> findAll(int begin, int limit) {
		String hql="from Product p order by p.pdate desc";
		List<Product> list = sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(begin).setMaxResults(limit).list();
		return list;
	}

	public void save(Product product) {
		sessionFactory.getCurrentSession().save(product);
	}

	public void delete(Product product) {
		sessionFactory.getCurrentSession().delete(product);
	}

	public void update(Product product) {
		sessionFactory.getCurrentSession().update(product);
	}
}
