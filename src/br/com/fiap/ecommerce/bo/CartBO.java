package br.com.fiap.ecommerce.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.util.SessionUtil;

public class CartBO {
	
	public void setCart(BookBean book) {
		List<BookBean> cartList = (List<BookBean>) SessionUtil.getParam("cartList");
		
		if (cartList == null) {
			cartList = new ArrayList<>();
		}
		
		cartList.add(book);
		
		SessionUtil.setParam("cartList", cartList);
	}
	
	public List<BookBean> getCart() {
		return (List<BookBean>) SessionUtil.getParam("cartList");
	}

	public void removeCart(BookBean book) {
		List<BookBean> cartList = (List<BookBean>) SessionUtil.getParam("cartList");
			
		cartList.remove(book);
		
		SessionUtil.setParam("cartList", cartList);
	}
	
}
