package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.bean.LoginBean;
import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;
import br.com.fiap.ecommerce.managedbean.GenreManagedBean;

public class UserDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
	
	public int generateID(){
		int UserID = 1;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select Max(UserID) as UserID From Usuario";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				UserID = resultSet.getInt("UserID") + 1;
			}			
		} 
		catch (Exception e) {
			UserID = 1;
		}
		
		return UserID;
	}
	
	public UserBean pesquisarUser(UserBean user) throws SQLException{
		UserBean newUser = null;
		
		connection = ConnectionFactory.getConnection();
//		sql = "Select * from USUARIO Where USERID = ?";
		sql = "Select u.*, l.* From usuario u join login l on u.userid = l.userid Where u.USERID = ?";
		
	    preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user.getId());
		
		resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()){
			  int id = resultSet.getInt("USERID");
			  String name = resultSet.getString("NAME");
			  long cpf = resultSet.getLong("CPF");
			  String lastname = resultSet.getString("LASTNAME");
			  String email = resultSet.getString("EMAIL");
			  String gender = resultSet.getString("GENDER");
			  Date birthdate = resultSet.getDate("BIRTHDATE");
			  int phonenumber = resultSet.getInt("PHONENUMBER");
			  int zipcode = resultSet.getInt("ZIPCODE");
			  String city = resultSet.getString("CITY");
			  String state = resultSet.getString("CSTATE");
			  String street = resultSet.getString("STREET");
			  int housenumber = resultSet.getInt("HOUSENUMBER");
			  
			  int loginId = resultSet.getInt("LOGINID");
			  String username = resultSet.getString("USERNAME");
			  String password = resultSet.getString("PASSWORD");
			  int loginType = resultSet.getInt("LOGINTYPE");
			  
			  newUser = new UserBean(id, name, cpf, lastname, email, gender, birthdate, phonenumber, zipcode, city, state, street, housenumber);
			  newUser.setLogin(new LoginBean(loginId,loginType,id,username,password));
		}
			
		return newUser;
	}
	
	public List<UserBean> pesquisarAllUsers(UserBean user) throws SQLException{
		List<UserBean> listUsers = new ArrayList<UserBean>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select u.*, l.* From usuario u join login l on u.userid = l.userid Where u.name Like ?";
				
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "%" + user.getName() + "%");
		
		resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
		  int id = resultSet.getInt("USERID");
		  String name = resultSet.getString("NAME");
		  long cpf = resultSet.getLong("CPF");
		  String lastname = resultSet.getString("LASTNAME");
		  String email = resultSet.getString("EMAIL");
		  String gender = resultSet.getString("GENDER");
		  Date birthdate = resultSet.getDate("BIRTHDATE");
		  int phonenumber = resultSet.getInt("PHONENUMBER");
		  int zipcode = resultSet.getInt("ZIPCODE");
		  String city = resultSet.getString("CITY");
		  String state = resultSet.getString("CSTATE");
		  String street = resultSet.getString("STREET");
		  int housenumber = resultSet.getInt("HOUSENUMBER");
		  
		  int loginId = resultSet.getInt("LOGINID");
		  String username = resultSet.getString("USERNAME");
		  String password = resultSet.getString("PASSWORD");
		  int loginType = resultSet.getInt("LOGINTYPE");
		 
		  UserBean us = new UserBean(id, name, cpf, lastname, email, gender, birthdate, phonenumber, zipcode, city, state, street, housenumber);
		  us.setLogin(new LoginBean(loginId,loginType,id,username,password));
		  listUsers.add(us);
		}
		
		return listUsers;
	}
	
	public void inserirUser(UserBean user) throws SQLException{
		connection = ConnectionFactory.getConnection();
		sql = "INSERT INTO USUARIO(UserID, Name, CPF, LastName, Email, Gender, BirthDate, PhoneNumber, ZipCode, City, Cstate, Street, HouseNumber) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, generateID());
		preparedStatement.setString(2,user.getName());
		preparedStatement.setLong(3,user.getCpf());
		preparedStatement.setString(4, user.getLastname());
		preparedStatement.setString(5, user.getEmail());
		preparedStatement.setString(6, user.getGender());
		java.sql.Date data = new java.sql.Date(user.getBirthdate().getTime());
		preparedStatement.setDate(7,data);
		preparedStatement.setInt(8, user.getPhonenumber());
		preparedStatement.setInt(9, user.getZipcode());
		preparedStatement.setString(10, user.getCity());
		preparedStatement.setString(11, user.getState());
		preparedStatement.setString(12, user.getStreet());
		preparedStatement.setInt(13, user.getHousenumber());
		
		preparedStatement.execute();	
	}
	
	public void deletarUser(UserBean user) throws SQLException{
		connection = ConnectionFactory.getConnection();
		sql = "DELETE FROM USUARIO WHERE USERID = ?";
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user.getId());
		
		preparedStatement.execute();		
	}
	
	public void alterarUser(UserBean user) throws SQLException {
		connection = ConnectionFactory.getConnection();
		sql = "Update USUARIO set NAME = ?, CPF = ?, LASTNAME = ? ,EMAIL = ?, GENDER = ?, BIRTHDATE = ?, PHONENUMBER = ?,ZIPCODE = ?, CITY = ?,CSTATE = ?,STREET = ?,HOUSENUMBER = ? Where USERID = ?";

		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,user.getName());
		preparedStatement.setLong(2,user.getCpf());
		preparedStatement.setString(3, user.getLastname());
		preparedStatement.setString(4, user.getEmail());
		preparedStatement.setString(5, user.getGender());
		java.sql.Date data = new java.sql.Date(user.getBirthdate().getTime());
		preparedStatement.setDate(6,data);
		preparedStatement.setInt(7, user.getPhonenumber());
		preparedStatement.setInt(8, user.getZipcode());
		preparedStatement.setString(9, user.getCity());
		preparedStatement.setString(10, user.getState());
		preparedStatement.setString(11, user.getStreet());
		preparedStatement.setInt(12, user.getHousenumber());
		preparedStatement.setInt(13, user.getId());
		
		preparedStatement.execute();
		
		sql = "Update Login set loginType = ? where userid = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user.getLogin().getLoginType());
		preparedStatement.setInt(2, user.getLogin().getUserId());
		
		preparedStatement.execute();
	}	
}
