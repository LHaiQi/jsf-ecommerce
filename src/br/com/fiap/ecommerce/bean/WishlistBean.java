package br.com.fiap.ecommerce.bean;

public class WishlistBean {
	private BookBean book;
	private UserBean user;
	
	public WishlistBean() {
		book = new BookBean();
		user = new UserBean();
	}

	public WishlistBean(BookBean book, UserBean user) {
		this.book = book;
		this.user = user;
	}
	
	public BookBean getBook() {
		return book;
	}
	public void setBook(BookBean book) {
		this.book = book;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
}
