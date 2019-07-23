package cn.itcasr.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcasr.shop.cart.vo.Cart;
import cn.itcasr.shop.cart.vo.CartItem;
import cn.itcasr.shop.order.service.OrderService;
import cn.itcasr.shop.order.vo.Order;
import cn.itcasr.shop.order.vo.OrderItem;
import cn.itcasr.shop.user.vo.User;
import cn.itcasr.shop.utils.PageBean;
import cn.itcasr.shop.utils.PaymentUtil;

public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	private Order order = new Order();
	private OrderService orderService;
	private Integer page;
	// 接收支付通道编码
	private String pd_FrpId;
	// 付款成功后的检验数据
	private String r3_Amt;
	private String r6_Order;

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public Order getModel() {
		return order;
	}

	// 生成订单
	public String save() {
		order.setOrdertime(new Date());
		order.setState(1);// 1:未付款,2:已付款，但未发货,3:已发货，但未确认收货,4:交易完成
		// 总计的数据是购物车中的信息
		Cart cart = (Cart) ActionContext.getContext().getSession().get("cart");
		if (cart == null) {
			this.addActionError("亲！请先选购商品");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		// 设置订单所属用户
		User user = (User) ActionContext.getContext().getSession().get("existuser");
		if (user == null) {
			this.addActionError("亲！您还没有登录，请先登录");
			return "login";
		}
		order.setUser(user);
		// 1.保存数据到数据库
		orderService.save(order);
		// 2.将订单对象显示到页面
		// 通过值栈的方式将数据显示：因为order显示的对象就是模型驱动的使用对象
		// 清空购物车
		cart.clearCart();
		return "saveSuccess";
	}

	// 我的订单查询
	public String findByUid() {
		// 根据用户id查询订单
		User user = (User) ActionContext.getContext().getSession().get("existuser");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(), page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}

	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}

	// 为订单付款
	public String payOrder() throws IOException {
		// 1.修改订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		// 2.为订单付款
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://192.168.36.69:8080/shop/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		// 重定向:向易宝出发:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}

	public String callBack() {
		// 修改订单的状态:
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("支付成功!订单编号为: " + r6_Order + " 付款金额为: " + r3_Amt);
		return "msg";
	}
	//确认收款
	public String updateState() {
		// 修改订单的状态:
		Order currOrder = orderService.findByOid(order.getOid());
		// 修改订单状态
		currOrder.setState(4);
		orderService.update(currOrder);
		this.addActionMessage("确认成功!订单编号为: 完成交易");
		return "updateSateSuccess";
	}
}
