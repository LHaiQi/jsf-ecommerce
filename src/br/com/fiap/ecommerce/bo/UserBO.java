package br.com.fiap.ecommerce.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.ecommerce.bean.LoginBean;
import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.dao.LoginDAO;
import br.com.fiap.ecommerce.dao.UserDAO;

public class UserBO {
	boolean loginExistente = true;
	
	public List<UserBean> pesquisarALLUser(UserBean userBean) throws SQLException{
		UserDAO userDAO = new UserDAO();
		return userDAO.pesquisarAllUsers(userBean);		
	}
	
	public UserBean pesquisarUser(UserBean userBean) throws SQLException{
		UserDAO userDAO = new UserDAO();
		return userDAO.pesquisarUser(userBean);
	}	
	
	public void inserirUser(UserBean userBean) throws SQLException{		
		LoginDAO loginDAO = new LoginDAO();
		
		UserDAO userDAO = new UserDAO();
		userDAO.inserirUser(userBean);		
	}
	
	public void deletarUser(UserBean userBean) throws SQLException{
		UserDAO userDAO = new UserDAO();
		userDAO.deletarUser(userBean);
	}
	
	public void alterarUser(UserBean userBean) throws SQLException {
		UserDAO userDAO = new UserDAO();
		userDAO.alterarUser(userBean);
	}
}
