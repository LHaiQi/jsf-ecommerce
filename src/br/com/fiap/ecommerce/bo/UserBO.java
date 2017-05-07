package br.com.fiap.ecommerce.bo;

import java.util.List;

import br.com.fiap.ecommerce.bean.LoginBean;
import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.dao.LoginDAO;
import br.com.fiap.ecommerce.dao.UserDAO;



public class UserBO {
	boolean loginExistente = true;
	
	public List<UserBean> pesquisarALLUser(UserBean userBean){
		UserDAO userDAO = new UserDAO();
		return userDAO.pesquisarAllUsers(userBean);		
	}
	
	

	public UserBean pesquisarUser(UserBean userBean){
		UserDAO userDAO = new UserDAO();
		return userDAO.pesquisarUser(userBean);
	}
	
	
	public boolean inserirUser(UserBean userBean){
		boolean conseguiuCriarUser = false;
		LoginDAO loginDAO = new LoginDAO();
		loginExistente = loginDAO.verificaLoginExistente(userBean.getLogin().getUser());
		if(!loginExistente) {
			UserDAO userDAO = new UserDAO();
			userDAO.inserirUser(userBean);
			conseguiuCriarUser = true;
		}else{
			System.out.println("Username já existe, crie outro");
		}
		return conseguiuCriarUser;
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
