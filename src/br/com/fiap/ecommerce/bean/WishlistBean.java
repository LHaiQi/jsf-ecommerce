package br.com.fiap.ecommerce.bean;

public class WishlistBean {
	private BookBean book;
	private LoginBean login;
	
	public WishlistBean() {
		book = new BookBean();
		login = new LoginBean();
	}
	
	public WishlistBean(BookBean book, LoginBean login) {
		this.book = book;
		this.login = login;
	}
	
	public BookBean getBook() {
		return book;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}
}
