package br.com.fiap.ecommerce.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.ecommerce.bean.AuthorBean;
import br.com.fiap.ecommerce.dao.AuthorDAO;

public class AuthorBO {

	public List<AuthorBean> getListAuthor() throws SQLException {
		AuthorDAO authorDAO = new AuthorDAO();
		return authorDAO.getAllAuthors();
	}
	
	public List<AuthorBean> getListAuthor(AuthorBean authorBean) throws SQLException{
		AuthorDAO authorDAO = new AuthorDAO();
		return authorDAO.getAllAuthors(authorBean);		
	}
	
	public void setAuthor(AuthorBean authorBean) throws SQLException{
		AuthorDAO authorDAO = new AuthorDAO();
		
		authorDAO.setAuthor(authorBean);
	}
	
	public void deleteAuthor(AuthorBean authorBean) throws SQLException{
		AuthorDAO authorDAO = new AuthorDAO();
		
		authorDAO.deleteAuthor(authorBean);
	}
	
	public AuthorBean getAuthor(AuthorBean authorBean) throws SQLException{
		AuthorDAO authorDAO = new AuthorDAO();
		return authorDAO.getAuthor(authorBean);
	}

	public void alterAuthor(AuthorBean authorBean) throws SQLException {
		AuthorDAO authorDAO = new AuthorDAO();
		authorDAO.alterAuthor(authorBean);
	}	
}
