package cn.itcasr.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cart {
	//key为商品id，value为购物项
	private Map<Integer,CartItem> map = new LinkedHashMap();
	//购物总计
	private double total;
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	public double getTotal() {
		return total;
	}
	//功能1：将购物项加入到购物车
	public void addCart(CartItem cartItem) {
		//如果已存在，增加数量与总计
		Integer pid=cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			CartItem _cartItem=map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		} else {
			map.put(pid, cartItem);
		}
		total+=cartItem.getSubtotal();
	}
	//功能2.删除购物项
	public void removeCart(Integer pid) {
		CartItem cartItem=map.remove(pid);
		total-=cartItem.getSubtotal();
	}
	//功能3.清空购物车
	public void clearCart() {
		map.clear();
		total=0;
	}
}
