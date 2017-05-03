package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.ecommerce.bean.LoginBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;

public class LoginDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
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
	
	public void inserirLogin(LoginBean login){
		connection = ConnectionFactory.getConnection();
		sql = "INSERT INTO LOGIN VALUES ((select max(LOGINID)+1 from LOGIN),?,?,?,?)";
		
		try {
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,login.getUser());
			preparedStatement.setString(2,login.getPassword());
			preparedStatement.setInt(3, login.getUserId());
			preparedStatement.setInt(4, login.getLoginType());
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			System.out.println("Erro ao inserir Login: " + e);
		}
		
	}
}
