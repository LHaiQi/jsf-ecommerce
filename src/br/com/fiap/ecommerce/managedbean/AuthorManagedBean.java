package br.com.fiap.ecommerce.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
		authorList = authorBO.getListAuthor(author);
		
		return "search-author";
	}
	
	public String insertAuthorController(){
		AuthorBO authorBO = new AuthorBO();
		authorBO.setAuthor(author);
		
		return "insert-author";
	}
	
	public String deleteAuthorController(){
		AuthorBO authorBO = new AuthorBO();
		authorBO.deleteAuthor(author);
		
		return searchAuthorController();
	}
	
	public String fillEditAuthorController(){
		AuthorBO authorBO = new AuthorBO();
		author = authorBO.getAuthor(author);
		
		return "edit-author";
	}
	
	public String editAuthorController(){
		AuthorBO authorBO = new AuthorBO();
		authorBO.alterAuthor(author);
		
		return searchAuthorController();
	}
}
