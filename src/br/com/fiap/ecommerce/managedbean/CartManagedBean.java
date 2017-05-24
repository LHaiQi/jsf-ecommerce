package br.com.fiap.ecommerce.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.bo.CartBO;

@ManagedBean
@SessionScoped
public class CartManagedBean {
	private BookBean book = new BookBean();
	private List<BookBean> cartlistBook = new ArrayList<>();
	
	public BookBean getBook() {
		return book;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}

	public List<BookBean> getCartlistBook() {
		CartBO cartBO = new CartBO();
		cartlistBook = cartBO.getCart();
		
		return cartlistBook;
	}

	public void setListBook(List<BookBean> listBook) {
		this.cartlistBook = listBook;
	}
	
	public String setCartController(){
		CartBO cartBO = new CartBO();
		cartBO.setCart(book);
		
		return "cart";
	}	
}
