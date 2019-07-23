package cn.itcasr.shop.order.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import cn.itcasr.shop.order.dao.OrderDao;
import cn.itcasr.shop.order.vo.Order;
import cn.itcasr.shop.order.vo.OrderItem;
import cn.itcasr.shop.utils.PageBean;
@Transactional
public class OrderService {
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	//保存订单数据
	public void save(Order order) {
		orderDao.save(order);
	}
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		pageBean.setPage(page);
		//设置每页显示5条数据
		Integer limit=5;
		pageBean.setLimit(limit);
		//设置总记录数
		Integer totalCount=null;
		totalCount=orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=(int) Math.ceil(1.0*totalCount/limit);
		pageBean.setTotalPage(totalPage);
		//设置每页显示数据集合
		Integer begin=(page-1)*limit;
		List<Order> list =orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	public void update(Order currOrder) {
		orderDao.update(currOrder);		
	}
	public PageBean<Order> findAll(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		int limit=10;
		pageBean.setLimit(limit);
		int totalCount=orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		int totalPage=(int) Math.ceil(1.0*totalCount/limit);
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Order> list=orderDao.findAll(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
}
