package br.com.fiap.ecommerce.managedbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.ecommerce.bean.LoginBean;
import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.bo.LoginBO;
import br.com.fiap.ecommerce.bo.UserBO;

@ManagedBean
@SessionScoped
public class UserManagedBean {
    UserBean user = new UserBean();
    List<UserBean> listUsers = new ArrayList<UserBean>();
    String newPassword, repeatedNewPassword;
    
    
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRepeatedNewPassword() {
		return repeatedNewPassword;
	}
	public void setRepeatedNewPassword(String repeatedNewPassword) {
		this.repeatedNewPassword = repeatedNewPassword;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public List<UserBean> getListUsers() {
		return listUsers;
	}
	public void setListUsers(List<UserBean> listUsers) {
		this.listUsers = listUsers;
	}
	
	public String searchUserController(){
		UserBO userBO = new UserBO();
		try {
			listUsers = userBO.pesquisarALLUser(user);
		} catch (Exception e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao buscar", "Detalhes:  " + e));
		}
		
		return "search-user";
	}
	
	public String insertUserController(){
		UserBO userBO = new UserBO();
		
		boolean conseguiuCriarUser = false;
		
		try {
			conseguiuCriarUser = userBO.inserirUser(user);
		} 
		catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Inserir Usuario", "Detalhes:  " + e));
		}
		
		if(conseguiuCriarUser){
			LoginBO loginBO = new LoginBO();
			
			try {
				loginBO.inserirLogin(user.getLogin());
			} 
			catch (Exception e) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Inserir Login", "Detalhes:  " + e));	
			}
			return "login";
		}
		
		return "insert-user";
	}
	
	public String deletarUserController(){
		LoginBO loginBO = new LoginBO();
		boolean conseguiuDeletarLogin = loginBO.deletarLogin(user) ;
		if(conseguiuDeletarLogin){
			UserBO userBO = new UserBO();
			try {
				userBO.deletarUser(user);
			} 
			catch (SQLException e) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Deletar", "Detalhes:  " + e));	
			}
		}
		
		return searchUserController();
	}
	
	public String editUserController(){
		UserBO userBO = new UserBO();
	    try {
			userBO.alterarUser(user);
		} 
	    catch (Exception e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Alterar", "Detalhes:  " + e));	
		}
		
		return searchUserController();
	}
	
	public String preencherUserController(){
		UserBO userBO = new UserBO();
		try {
			user = userBO.pesquisarUser(user);
		} 
		catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Preencher", "Detalhes:  " + e));
		}
		
		return "edit-user";
	}
	
	public String loginUserController(){
		LoginBO loginBO = new LoginBO();
		boolean podeLogar = loginBO.autenticarLogin(user.getLogin());
		if(podeLogar){
			return "search-user";
		}else{
			System.out.println("Usuário e/ou senha inválido(s)");
		}
		return "login";
	}
	
	public String mudarSenha(){
		return "edit-login";
	}
	
	public String criarConta(){
		return "insert-user";
	}
	
	public String editLoginUserController(){
		LoginBO loginBO = new LoginBO();
		boolean conseguiuAlterar = loginBO.alterarLogin(user.getLogin(), newPassword, repeatedNewPassword);
		if(conseguiuAlterar){
			return "search-user";
		}else{
			return "edit-login";
		}
	}
	
	public String logoutLoginUserController(){
		return "login";
	}

}
