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
	
	public boolean autenticarLogin(LoginBean loginBean) throws SQLException{
		boolean podeLogar = false;
		
		LoginDAO loginDAO = new LoginDAO();		
		podeLogar = loginDAO.autenticarLogin(loginBean);
		
		return podeLogar;
	}
	
	public boolean alterarLogin(LoginBean loginBean, String newPassword, String repeatedNewPassword) throws SQLException{
		boolean podeAlterar = false;
		if(newPassword.equalsIgnoreCase(repeatedNewPassword)){
			podeAlterar = autenticarLogin(loginBean);
			if(podeAlterar){
				LoginDAO loginDAO = new LoginDAO();
				loginDAO.alterarLogin(loginBean, newPassword);
			}else{
				System.out.println("Usuário não existe ou senha inválida");
			}
		}else{
			System.out.println("As senhas são diferentes");
		}
		return podeAlterar;
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
