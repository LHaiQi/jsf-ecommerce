package br.com.fiap.ecommerce.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.bo.UserBO;

@ManagedBean
@SessionScoped
public class UserManagedBean {
  UserBean user = new UserBean();
  List<UserBean> listUsers = new ArrayList<UserBean>();

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
	listUsers = userBO.pesquisarALLUser(user);
	
	return "search-user";
}


public String insertUserController(){
	UserBO userBO = new UserBO();
	userBO.inserirUser(user);
	
	return "insert-user";
}

public String deletarUserController(){
	UserBO userBO = new UserBO();
	userBO.deletarUser(user);;
	
	return "search-user";
}

public String editUserController(){
	UserBO userBO = new UserBO();
    userBO.alterarUser(user);
	
	return "search-publisher";
}


}
