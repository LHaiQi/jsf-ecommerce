package br.com.fiap.ecommerce.bo;

import java.util.List;

import br.com.fiap.ecommerce.bean.AuthorBean;
import br.com.fiap.ecommerce.dao.AuthorDAO;

public class AuthorBO {

	public List<AuthorBean> getListAuthor() {
		AuthorDAO authorDAO = new AuthorDAO();
		return authorDAO.getAllAuthors();
	}
	
	public List<AuthorBean> getListAuthor(AuthorBean authorBean){
		AuthorDAO authorDAO = new AuthorDAO();
		return authorDAO.getAllAuthors(authorBean);		
	}
	
	public void setAuthor(AuthorBean authorBean){
		AuthorDAO authorDAO = new AuthorDAO();
		
		authorDAO.setAuthor(authorBean);
	}
	
	public void deleteAuthor(AuthorBean authorBean){
		AuthorDAO authorDAO = new AuthorDAO();
		
		authorDAO.deleteAuthor(authorBean);
	}
	
	public AuthorBean getAuthor(AuthorBean authorBean){
		AuthorDAO authorDAO = new AuthorDAO();
		return authorDAO.getAuthor(authorBean);
	}

	public void alterAuthor(AuthorBean authorBean) {
		AuthorDAO authorDAO = new AuthorDAO();
		authorDAO.alterAuthor(authorBean);
	}	
}
