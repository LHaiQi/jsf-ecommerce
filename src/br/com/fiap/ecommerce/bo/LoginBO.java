package br.com.fiap.ecommerce.bo;

import br.com.fiap.ecommerce.bean.LoginBean;
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
}
