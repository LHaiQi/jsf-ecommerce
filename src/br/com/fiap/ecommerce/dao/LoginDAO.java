package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.ecommerce.bean.LoginBean;
import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;

public class LoginDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
	
//	public LoginBean pesquisarLogin(LoginBean login){
//		LoginBean newLogin = null;
//		
//		connection = ConnectionFactory.getConnection();
//		sql = "Select * from LOGIN Where LOGINID = ?";
//		
//		try {
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, login.getLoginId());
//			
//			resultSet = preparedStatement.executeQuery();
//			
//			if(resultSet.next()){
//				  int loginId = resultSet.getInt("LOGINID");
//				  String user = resultSet.getString("USER");
//				  String password = resultSet.getString("PASSWORD");
//				  int userId = resultSet.getInt("USERID");
//				  int loginType = resultSet.getInt("LOGINTYPE");
//				  
//				  newLogin = new LoginBean(loginId, loginType, userId, user, password);
//			}
//			
//		} catch (Exception e) {
//			
//		}
//		
//		return newLogin;
//	}
	
	public int getUserId(){
		int id = 1;
		connection = ConnectionFactory.getConnection();
		sql = "select max(USERID) as ultimoUsuario from usuario";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				id = resultSet.getInt("ultimoUsuario");
			}
		} catch (Exception e) {
			id = 1;
		}
		
		return id;
	}

	public int generateLoginID(){
		int loginID = 1;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select Max(loginID) as loginID From Login";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
 			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				loginID = resultSet.getInt("LOGINID");
				loginID++;
			}			
		} 
		catch (Exception e) {
			loginID = 1;
		}
		
		return loginID;
	}
	
	public void inserirLogin(LoginBean login){
		connection = ConnectionFactory.getConnection();
		sql = "INSERT INTO LOGIN VALUES (?,?,?,?,?)";
		int loginId = generateLoginID();
		int userId = getUserId();
		
//		sql = "INSERT INTO LOGIN VALUES ("+4+",'user','senha',"+5+","+0+")";
		
//		sql = "INSERT INTO LOGIN VALUES (?,'user','senha',"+7+","+0+")";
		
		sql = "INSERT INTO LOGIN VALUES (?,?,?,?,?)";
		
		try {
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, loginId);
			preparedStatement.setString(2,login.getUser());
			preparedStatement.setString(3,login.getPassword());
			preparedStatement.setInt(4, userId);
			preparedStatement.setInt(5, 0);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			System.out.println("Erro ao inserir Login: " + e);
		}
		
	}
	
	public boolean verificaLoginExistente(String username){
		boolean existe = false;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * from LOGIN Where USERNAME = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				existe = true;
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao verificar Login: " + e);
		}
		
		return existe;
	}
	
	public boolean autenticarLogin(LoginBean login){
		boolean podeLogar = false;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * from LOGIN Where USERNAME = ? and PASSWORD = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login.getUser());
			preparedStatement.setString(2, login.getPassword());
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				podeLogar = true;
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao autenticar Login: " + e);
		}
		
		return podeLogar;
	}
	
	public void alterarLogin(LoginBean login, String newPassword){
		connection = ConnectionFactory.getConnection();
		sql = "Update LOGIN set PASSWORD = ? WHERE USERNAME = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, login.getUser());
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			System.out.println("Erro ao Editar User: " + e);
		}
	}
}
