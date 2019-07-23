package cn.itcasr.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cart {
	//keyΪ��Ʒid��valueΪ������
	private Map<Integer,CartItem> map = new LinkedHashMap();
	//�����ܼ�
	private double total;
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	public double getTotal() {
		return total;
	}
	//����1������������뵽���ﳵ
	public void addCart(CartItem cartItem) {
		//����Ѵ��ڣ������������ܼ�
		Integer pid=cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			CartItem _cartItem=map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		} else {
			map.put(pid, cartItem);
		}
		total+=cartItem.getSubtotal();
	}
	//����2.ɾ��������
	public void removeCart(Integer pid) {
		CartItem cartItem=map.remove(pid);
		total-=cartItem.getSubtotal();
	}
	//����3.��չ��ﳵ
	public void clearCart() {
		map.clear();
		total=0;
	}
}
