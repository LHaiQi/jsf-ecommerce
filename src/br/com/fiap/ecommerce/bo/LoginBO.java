package br.com.fiap.ecommerce.bo;

import br.com.fiap.ecommerce.bean.LoginBean;
import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.dao.LoginDAO;

public class LoginBO {
	
	public void inserirLogin(LoginBean loginBean){
		LoginDAO loginDAO = new LoginDAO();
		
		boolean exists = loginDAO.verificaLoginExistente(loginBean.getUser());
		
		if (!exists) {
			loginDAO.inserirLogin(loginBean);
		}else{
			//Mensagem de login repetido
		}	
	}
	
	public boolean autenticarLogin(LoginBean loginBean){
		boolean podeLogar = false;
		
		LoginDAO loginDAO = new LoginDAO();
		
		podeLogar = loginDAO.autenticarLogin(loginBean);
		return podeLogar;
	}
	
	public void alterarLogin(LoginBean login, String newPassword, String repeatedNewPassword){
		boolean podeAlterar = false;
		if(newPassword.equalsIgnoreCase(repeatedNewPassword)){
			podeAlterar = autenticarLogin(login);
			if(podeAlterar){
				LoginDAO loginDAO = new LoginDAO();
				loginDAO.alterarLogin(login, newPassword);
			}else{
				System.out.println("Usuário não existe ou senha inválida");
			}
		}else{
			System.out.println("As senhas são diferentes");
		}
		LoginDAO loginDAO = new LoginDAO();
	}
}
