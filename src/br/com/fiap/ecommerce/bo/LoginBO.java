package br.com.fiap.ecommerce.bo;

import java.sql.SQLException;

import br.com.fiap.ecommerce.bean.LoginBean;
import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.dao.LoginDAO;

public class LoginBO {
	
	public void inserirLogin(LoginBean loginBean) throws Exception{
		LoginDAO loginDAO = new LoginDAO();
		loginDAO.inserirLogin(loginBean);
	}
	
	public LoginBean autenticarLogin(LoginBean loginBean) throws SQLException{
		LoginDAO loginDAO = new LoginDAO();		
		LoginBean login = loginDAO.autenticarLogin(loginBean);
		
		return login;
	}
	
	public void alterarLogin(LoginBean loginBean, String newPassword) throws SQLException{
		LoginDAO loginDAO = new LoginDAO();
		loginDAO.alterarLogin(loginBean, newPassword);
	}
	
	public boolean deletarLogin(UserBean userBean){
		boolean conseguiuDeletarLogin = false;
		LoginDAO loginDAO = new LoginDAO();
		
		conseguiuDeletarLogin = loginDAO.deletarLogin(userBean);
		return conseguiuDeletarLogin;
	}

	public boolean validateExistentUser(UserBean user) throws SQLException {
		LoginDAO loginDAO = new LoginDAO();
		return loginDAO.verificaLoginExistente(user.getLogin().getUser());
	}
}
