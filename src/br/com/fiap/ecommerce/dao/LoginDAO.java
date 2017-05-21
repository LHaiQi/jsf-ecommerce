package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.ecommerce.bean.LoginBean;
import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;

public class LoginDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
	
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

	public void inserirLogin(LoginBean login) throws Exception{
		connection = ConnectionFactory.getConnection();
		sql = "INSERT INTO LOGIN VALUES (?,?,?,?,?)";
			
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, getUserId());
		preparedStatement.setString(2,login.getUser());
		preparedStatement.setString(3,login.getPassword());
		preparedStatement.setInt(4, getUserId());
		preparedStatement.setInt(5, 0);
		
		preparedStatement.execute();
	}
	
	public boolean verificaLoginExistente(String username) throws SQLException{
		boolean existe = false;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * from LOGIN Where USERNAME = ?";
			
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		
		resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()){
			existe = true;
		}
		
		return existe;
	}
	
	public LoginBean autenticarLogin(LoginBean login) throws SQLException {		
		connection = ConnectionFactory.getConnection();
		sql = "Select * from LOGIN Where USERNAME = ? and PASSWORD = ?";
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, login.getUser());
		preparedStatement.setString(2, login.getPassword());
		
		resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()){
			login.setLoginId(resultSet.getInt("userID"));
			login.setLoginType(resultSet.getInt("LoginType"));
		}
		else {
			login = null;
		}
		
		return login;
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
	
	public boolean deletarLogin(UserBean user){
		boolean conseguiuDeletar = false;
		
		connection = ConnectionFactory.getConnection();
		sql = "DELETE FROM LOGIN WHERE LOGINID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user.getId());
			
			preparedStatement.execute();
			
			conseguiuDeletar = true;
		} catch (SQLException e) {
			System.out.println("Erro ao apagar Login(a): " + e);
		}
		
		return conseguiuDeletar;
	}
}
