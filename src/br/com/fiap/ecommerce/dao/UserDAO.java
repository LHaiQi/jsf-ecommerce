package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;

public class UserDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
	
	public UserBean pesquisarUser(UserBean user){
		UserBean newUser = null;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * from USUARIO Where USERID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user.getId());
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				  int id = resultSet.getInt("USERID");
				  String name = resultSet.getString("NAME");
				  int cpf = resultSet.getInt("CPF");
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
				  
				  newUser = new UserBean(id, name, cpf, lastname, email, gender, birthdate, phonenumber, zipcode, city, state, street, housenumber);
			}
			
		} catch (Exception e) {
			
		}
		
		return newUser;
	}
	
	public List<UserBean> pesquisarAllUsers(UserBean user){
		List<UserBean> listUsers = new ArrayList<UserBean>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From USUARIO Where NAME Like ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + user.getName() + "%");
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				 int id = resultSet.getInt("USERID");
				  String name = resultSet.getString("NAME");
				  int cpf = resultSet.getInt("CPF");
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
				 
				  listUsers.add(new UserBean(id, name, cpf, lastname, email, gender, birthdate, phonenumber, zipcode, city, state, street, housenumber));
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar lista Usuarios: " + e);
		}
		
		return listUsers;
	}
	
	public void inserirUser(UserBean user){
		connection = ConnectionFactory.getConnection();
		sql = "INSERT INTO USUARIO VALUES ((select max(USERID)+1 from USUARIO),?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,user.getName());
			preparedStatement.setInt(2,user.getCpf());
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
			preparedStatement.execute();
			
			
		} catch (Exception e) {
			System.out.println("Erro ao inserir User: " + e);
		}
		
	}
	
	public void deletarUser(UserBean user){
		connection = ConnectionFactory.getConnection();
		sql = "DELETE FROM USUARIO WHERE USERID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user.getId());
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			System.out.println("Erro ao apagar User(a): " + e);
		}
	}
	
	public void alterarUser(UserBean user) {
		connection = ConnectionFactory.getConnection();
		sql = "Update USUARIO set NAME = ?, CPF = ?, LASTNAME = ? ,EMAIL = ?, GENDER = ?, BIRTHDATE = ?, PHONENUMBER = ?,ZIPCODE = ?, CITY = ?,CSTATE = ?,STREET = ?,HOUSENUMBER = ? Where USERID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,user.getName());
			preparedStatement.setInt(2,user.getCpf());
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
			
		} catch (Exception e) {
			System.out.println("Erro ao Editar User: " + e);
		}
	}
	
}
