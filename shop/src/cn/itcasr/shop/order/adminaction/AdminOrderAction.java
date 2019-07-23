package cn.itcasr.shop.order.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcasr.shop.order.service.OrderService;
import cn.itcasr.shop.order.vo.Order;
import cn.itcasr.shop.order.vo.OrderItem;
import cn.itcasr.shop.utils.PageBean;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	private Order order=new Order();
	private OrderService orderService;
	private Integer page;
	@Override
	public Order getModel() {
		return order;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String findAll() {
		PageBean<Order> pageBean=orderService.findAll(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	public String findOrderItem() {
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	public String updateState() {
		Order currOrder=orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
