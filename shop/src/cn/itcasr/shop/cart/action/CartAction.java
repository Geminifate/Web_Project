package cn.itcasr.shop.cart.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcasr.shop.cart.vo.Cart;
import cn.itcasr.shop.cart.vo.CartItem;
import cn.itcasr.shop.product.service.ProductService;
import cn.itcasr.shop.product.vo.Product;

public class CartAction extends ActionSupport{
	//接收pid
	private Integer pid;
	//接收数量
	private Integer count;
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	public String clearCart() {
		//获得购物车对象
		System.out.println("qingkong");
		Cart cart=getcart();
		//调用购物车清空方法
		cart.clearCart();
		return "clearCart";
	}
	public String addCart() {
		//封装CartItem对象
		CartItem cartItem=new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//添加到购物车
		//购物车应该存在session中
		Cart cart=getcart();
		cart.addCart(cartItem);
		return "addCart";
	}
	public String removeCart() {
		Cart cart=getcart();
		cart.removeCart(pid);
		return "removeCart";
	}
	
	public String myCart() {
		return "myCart";
	}
	//从session中获得购物车
	public Cart getcart() {
		Cart cart=(Cart) ActionContext.getContext().getSession().get("cart");
		if (cart==null) {
			cart=new Cart();
			ActionContext.getContext().getSession().put("cart", cart);
		} else {

		}
		return cart;
	}
}
