package br.com.fiap.ecommerce.managedbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.ecommerce.bean.AuthorBean;
import br.com.fiap.ecommerce.bo.AuthorBO;

@ManagedBean
@SessionScoped
public class AuthorManagedBean {
	AuthorBean author = new AuthorBean();
	List<AuthorBean> authorList = new ArrayList<AuthorBean>();
	
	
	
	public List<AuthorBean> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<AuthorBean> authorList) {
		this.authorList = authorList;
	}

	public AuthorBean getAuthor() {
		return author;
	}

	public void setAuthor(AuthorBean author) {
		this.author = author;
	}
	
	public String searchAuthorController(){
		AuthorBO authorBO = new AuthorBO();
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgValidaProcura");
		String message = text.getString("msgDetalhes");
		
		
		try {
			authorList = authorBO.getListAuthor(author);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}
		
		return "search-author";
	}
	
	public String insertAuthorController(){
		AuthorBO authorBO = new AuthorBO();
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgValidaProcura");
		String message = text.getString("msgDetalhes");
		
		
		
		try {
			authorBO.setAuthor(author);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}
		
		return "insert-author";
	}
	
	public String deleteAuthorController(){
		AuthorBO authorBO = new AuthorBO();
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgValidaDeletar");
		String message = text.getString("msgDetalhes");
		try {
			authorBO.deleteAuthor(author);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}
		
		return searchAuthorController();
	}
	
	public String fillEditAuthorController(){
		AuthorBO authorBO = new AuthorBO();
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgValidaPreencher");
		String message = text.getString("msgDetalhes");
		
		try {
			author = authorBO.getAuthor(author);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}
		
		return "edit-author";
	}
	
	public String editAuthorController(){
		AuthorBO authorBO = new AuthorBO();
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgValidaEdit");
		String message = text.getString("msgDetalhes");
		
		try {
			authorBO.alterAuthor(author);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}
		
		return searchAuthorController();
	}
}
