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

import br.com.fiap.ecommerce.bean.LoginBean;
import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.bo.LoginBO;
import br.com.fiap.ecommerce.bo.UserBO;
import br.com.fiap.ecommerce.util.SessionUtil;

@ManagedBean
@SessionScoped
public class UserManagedBean {
    UserBean user = new UserBean();
    List<UserBean> listUsers = new ArrayList<UserBean>();
    String newPassword;
    boolean apareceGerenciarUsuarios = false;
    boolean estaLogado = false;
    boolean permissaoAdm = false;  
		
	public boolean getPermissaoAdm() {
		return permissaoAdm;
	}
	public void setPermissaoAdm(boolean permissaoAdm) {
		this.permissaoAdm = permissaoAdm;
	}
	public boolean getEstaLogado() {
		return estaLogado;
	}
	public void setEstaLogado(boolean estaLogado) {
		this.estaLogado = estaLogado;
	}
	public boolean getApareceGerenciarUsuarios() {
		return apareceGerenciarUsuarios;
	}
	public void setApareceGerenciarUsuarios(boolean apareceMinhaConta) {
		this.apareceGerenciarUsuarios = apareceMinhaConta;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
			estaLogado = true;
		} 
		catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Inserir", "Detalhes:  " + e));	
		}
		catch (Exception e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao Inserir", "Detalhes:  " + e));	
		}
		
		return "show-books";
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
			if(permissaoAdm){
				user.getLogin().setLoginType(1);
			}
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
	    LoginBean login = null;	
		
		try {
			login = loginBO.autenticarLogin(user.getLogin());
			
			if(login != null){
				SessionUtil.setParam("login", login);

				if(login.getLoginType() == 1){
					apareceGerenciarUsuarios = true;
				}else{
					apareceGerenciarUsuarios = false;
				}
				estaLogado = true;

				return "show-books";
			}
			else {
				throw new Exception("Usuário e/ou senha inválidos");
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
		user = new UserBean();
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
		
		return "show-books";
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
	
	public String sairUserController(){
		estaLogado = false;
		apareceGerenciarUsuarios = false;
		
		user = new UserBean();
		LoginBO login = null;
		
		SessionUtil.setParam("login", login);
		
		return "show-books.jsf";
	}
}
