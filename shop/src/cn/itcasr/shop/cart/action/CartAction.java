package cn.itcasr.shop.cart.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcasr.shop.cart.vo.Cart;
import cn.itcasr.shop.cart.vo.CartItem;
import cn.itcasr.shop.product.service.ProductService;
import cn.itcasr.shop.product.vo.Product;

public class CartAction extends ActionSupport{
	//����pid
	private Integer pid;
	//��������
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
		//��ù��ﳵ����
		System.out.println("qingkong");
		Cart cart=getcart();
		//���ù��ﳵ��շ���
		cart.clearCart();
		return "clearCart";
	}
	public String addCart() {
		//��װCartItem����
		CartItem cartItem=new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//��ӵ����ﳵ
		//���ﳵӦ�ô���session��
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
	//��session�л�ù��ﳵ
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
