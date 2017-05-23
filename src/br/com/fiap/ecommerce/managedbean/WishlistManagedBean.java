package br.com.fiap.ecommerce.managedbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.bean.LoginBean;
import br.com.fiap.ecommerce.bean.WishlistBean;
import br.com.fiap.ecommerce.bo.WishlistBO;
import br.com.fiap.ecommerce.util.SessionUtil;

@ManagedBean
@SessionScoped
public class WishlistManagedBean {
	private WishlistBean wishlist = new WishlistBean();
	List<WishlistBean> listWishes = new ArrayList<>();
	List<BookBean> listWishesItem = new ArrayList<>();
	
	public WishlistBean getWishlist() {
		return wishlist;
	}

	public void setWishlist(WishlistBean wishlist) {
		this.wishlist = wishlist;
	}

	public List<BookBean> getListWishes() {
		WishlistBO wishlistBO = new WishlistBO();
		
		try {
			wishlist.setLogin((LoginBean) SessionUtil.getParam("login"));
			listWishesItem = wishlistBO.getAllWishes(wishlist);
		} 
		catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Buscar Todos", "Detalhes:  " + e));
		}
		
		return listWishesItem;
	}

	public void setListWishes(List<WishlistBean> listWishes) {
		this.listWishes = listWishes;
	}

	public String setWishlist(){
		WishlistBO wishlistBO = new WishlistBO();		
		
		try {
			wishlist.setLogin((LoginBean) SessionUtil.getParam("login"));
			wishlistBO.setWishlist(wishlist);
		} 
		catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Inserir", "Detalhes:  " + e));
		}
		
		return "show-books";
	}
	
	public String getAllWishes(){
		WishlistBO wishlistBO = new WishlistBO();
		
		try {
			wishlist.setLogin((LoginBean) SessionUtil.getParam("login"));
			listWishesItem = wishlistBO.getAllWishes(wishlist);
		} 
		catch (Exception e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Buscar Todos", "Detalhes:  " + e));
		}	
		
		return "wishlist";
	}
	
	public String deleteWishItem(){
		WishlistBO wishlistBO = new WishlistBO();
		
		try {
			wishlist.setLogin((LoginBean) SessionUtil.getParam("login"));
			wishlistBO.deleteWishItem(wishlist);
			listWishesItem = getListWishes();
		} 
		catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Deletar Item", "Detalhes:  " + e));
		}
		
		return "wishlist";
	}	
}
