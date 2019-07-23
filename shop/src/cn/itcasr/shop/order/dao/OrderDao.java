package cn.itcasr.shop.order.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import cn.itcasr.shop.order.vo.Order;
import cn.itcasr.shop.order.vo.OrderItem;

public class OrderDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void save(Order order) {
		sessionFactory.getCurrentSession().save(order);
	}
	public Integer findByCountUid(Integer uid) {
		String hql="select count(*) from Order o where o.user.uid=?0";
		Long count=(Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, uid).uniqueResult();
		return count.intValue();
	}
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql="from Order o where o.user.uid=?0 order by ordertime desc";
		List<Order> list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, uid).setFirstResult(begin).setMaxResults(limit).list();
		return list;
	}
	public Order findByOid(Integer oid) {
		String hql="from Order o where o.oid=?0";
		Order order = (Order) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, oid).uniqueResult();
		return order;
	}
	public void update(Order currOrder) {
		sessionFactory.getCurrentSession().getSession().update(currOrder);
	}
	public int findByCount() {
		String hql="select count(*) from Order";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		return count.intValue();
	}
	public List<Order> findAll(int begin, int limit) {
		String hql="from Order order by ordertime desc";
		List<Order> list=sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(begin).setMaxResults(limit).list();
		return list;
	}
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql="from OrderItem oi where oi.order.oid=?0";
		List<OrderItem> list=sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, oid).list();
		return list;
	}
}
