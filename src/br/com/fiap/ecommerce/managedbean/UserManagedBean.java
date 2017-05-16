package br.com.fiap.ecommerce.managedbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.xml.bind.ValidationException;

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
		LoginBO loginBO = new LoginBO();
		
		try {
			userBO.inserirUser(user);
			loginBO.inserirLogin(user.getLogin());
		} 
		catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Inserir", "Detalhes:  " + e));	
		}
		catch (Exception e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Inserir", "Detalhes:  " + e));	
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
			
			return "edit-user";
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
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Preencher Dados", "Detalhes:  " + e));
		}
		
		return "edit-user";
	}
	
	public String loginUserController(){
		LoginBO loginBO = new LoginBO();
		LoginBean loginAutenticado = null;
		
		try {
			loginAutenticado = loginBO.autenticarLogin(user.getLogin());
			
			if(loginAutenticado != null){
				return "search-user";
			}
			else {
				throw new Exception("Usu�rio e/ou senha inv�lidos");
			}
		} catch (Exception e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Logar", "Detalhes:  " + e));
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
		
		try {
			loginBO.alterarLogin(user.getLogin(), newPassword);
		} 
		catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Editar", "Detalhes:  " + e));
		}
		
		return "search-user";
	}
	
	public String logoutLoginUserController(){
		user = null;
		return "login";
	}
	
	public void validateExistentUser(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		LoginBO loginBO = new LoginBO();
		boolean exists = false;
		
		try {
			exists = loginBO.validateExistentUser(user);
		} 
		catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Validar", "Detalhes:  " + e));
		}
		
		if (exists) {
			FacesMessage message = new FacesMessage("O nome de usuario ja esta em uso");
			throw new ValidatorException(message);
		}
	}
	
	public void validateNewPassword(FacesContext context, UIComponent component, Object value) {
		if (!newPassword.equals(repeatedNewPassword)) {
			FacesMessage message = new FacesMessage("As senhas n�o conferem");
			throw new ValidatorException(message);
		}
	}
}
