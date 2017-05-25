package br.com.fiap.ecommerce.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.facelets.FaceletContext;

import br.com.fiap.ecommerce.bean.AuthorBean;
import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.bean.LoginBean;
import br.com.fiap.ecommerce.bo.CartBO;
import br.com.fiap.ecommerce.bo.LoginBO;
import br.com.fiap.ecommerce.util.SessionUtil;

@ManagedBean
@SessionScoped
public class CartManagedBean {
	private BookBean book = new BookBean();
	private List<BookBean> cartlistBook = new ArrayList<>();
	private int payment;
	private int shipping;	
	private double total = 0;
			
	public double getTotal() {
		CartBO cartBO = new CartBO();	
		return cartBO.getTotal(shipping);
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public int getShipping() {
		return shipping;
	}

	public void setShipping(int shipping) {
		this.shipping = shipping;
	}

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
	
	public String removeCartController() {
		CartBO cartBO = new CartBO();
		cartBO.removeCart(book);
		return "cart";
	}
	
	public String finishCartController() {
		LoginBean loginBean = (LoginBean) SessionUtil.getParam("login");
		
		if(loginBean != null){
			FacesMessage message = new FacesMessage("Compra Finalizada");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
		}
		else {
			FacesMessage message = new FacesMessage("Você deve estar logado para finalizar uma compra");
			message.setSeverity(FacesMessage.SEVERITY_FATAL);
			FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
		}
		
		return "cart";
	}
}
