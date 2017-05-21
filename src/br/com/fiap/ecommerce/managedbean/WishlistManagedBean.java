package br.com.fiap.ecommerce.managedbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.ecommerce.bean.WishlistBean;
import br.com.fiap.ecommerce.bo.WishlistBO;

@ManagedBean
@SessionScoped
public class WishlistManagedBean {
	private WishlistBean wishlist = new WishlistBean();
	List<WishlistBean> listWishes = new ArrayList<>();	
	
	public WishlistBean getWishlist() {
		return wishlist;
	}

	public void setWishlist(WishlistBean wishlist) {
		this.wishlist = wishlist;
	}

	public List<WishlistBean> getListWishes() {
		return listWishes;
	}

	public void setListWishes(List<WishlistBean> listWishes) {
		this.listWishes = listWishes;
	}

	public String setWishlist(){
		WishlistBO wishlistBO = new WishlistBO();		
		
		try {
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
			listWishes = wishlistBO.getAllWishes(wishlist);
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
			wishlistBO.deleteWishItem(wishlist);
		} 
		catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Deletar Item", "Detalhes:  " + e));
		}
		
		return getAllWishes();
	}	
}
