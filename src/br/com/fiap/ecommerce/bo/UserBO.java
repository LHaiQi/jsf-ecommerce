package br.com.fiap.ecommerce.bo;

import java.util.List;

import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.dao.UserDAO;



public class UserBO {
	public List<UserBean> pesquisarALLUser(UserBean userBean){
		UserDAO userDAO = new UserDAO();
		return userDAO.pesquisarAllUsers(userBean);		
	}
	
	public void inserirUser(UserBean userBean){
		UserDAO userDAO = new UserDAO();
		userDAO.inserirUser(userBean);
	}
	
	public void deletarUser(UserBean userBean){
		UserDAO userDAO = new UserDAO();
		
		userDAO.deletarUser(userBean);

	}
	
	public void alterarUser(UserBean userBean) {
		UserDAO userDAO = new UserDAO();
		userDAO.alterarUser(userBean);
	}
	
}
