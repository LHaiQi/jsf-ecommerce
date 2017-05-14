package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.bean.AuthorBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;

public class AuthorDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
	
	public AuthorBean getAuthor(AuthorBean author) throws SQLException{
		AuthorBean newAuthor = null;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Author Where AuthorID = ?";
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, author.getId());
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int id = resultSet.getInt("AuthorID");
				String name = resultSet.getString("Name");
				String lastName = resultSet.getString("LastName");
				String gender = resultSet.getString("Gender");
				String nationality = resultSet.getString("Nationality");
				
				newAuthor = new AuthorBean(id, name, lastName, gender, nationality);
			}
	
		return newAuthor;
	}

	public List<AuthorBean> getAllAuthors() throws SQLException {
		List<AuthorBean> listAuthor = new ArrayList<AuthorBean>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Author";
		
			preparedStatement = connection.prepareStatement(sql);			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("AuthorID");
				String name = resultSet.getString("Name");
				String lastName = resultSet.getString("LastName");
				String gender = resultSet.getString("Gender");
				String nationality = resultSet.getString("Nationality");
				
				name = name + " " + lastName;
				
				listAuthor.add(new AuthorBean(id, name, lastName, gender, nationality));
			}
		
		return listAuthor;
	}
	
	public List<AuthorBean> getAllAuthors(AuthorBean authorBean) throws SQLException {
		List<AuthorBean> listAuthor = new ArrayList<AuthorBean>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Author Where Name Like ?";
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + authorBean.getName() + "%");
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("AuthorID");
				String name = resultSet.getString("Name");
				String lastName = resultSet.getString("LastName");
				String gender = resultSet.getString("Gender");
				String nationality = resultSet.getString("Nationality");
				
				listAuthor.add(new AuthorBean(id, name, lastName, gender, nationality));
			}
		
		return listAuthor;
	}
	
	public int generateAuthorID(){
		int authorID = 1;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select Max(authorID) as authorID From author";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
 			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				authorID = resultSet.getInt("authorID");
				authorID++;
			}			
		} 
		catch (Exception e) {
			authorID = 1;
		}
		
		return authorID;
	}

	public void setAuthor(AuthorBean authorBean) throws SQLException {
		connection = ConnectionFactory.getConnection();
		sql = "Insert Into Author Values (?,?,?,?,?)";
		
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, generateAuthorID());
			preparedStatement.setString(2, authorBean.getName());
			preparedStatement.setString(3, authorBean.getGender());
			preparedStatement.setString(4, authorBean.getLastName());
			preparedStatement.setString(5, authorBean.getNationality());
			
			preparedStatement.execute();
	}
	
	public void deleteAuthor(AuthorBean authorBean) throws SQLException {
		connection = ConnectionFactory.getConnection();
		sql = "DELETE FROM AUTHOR WHERE AUTHORID = ?";
		
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, authorBean.getId());
			
			preparedStatement.execute();
			
	}

	public void alterAuthor(AuthorBean authorBean) throws SQLException {
		connection = ConnectionFactory.getConnection();
		sql = "Update Author set Name = ?, Gender = ? , LastName = ?, Nationality = ? Where AuthorID = ?";
		
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, authorBean.getName());
			preparedStatement.setString(2, authorBean.getGender());
			preparedStatement.setString(3, authorBean.getLastName());
			preparedStatement.setString(4, authorBean.getNationality());
			preparedStatement.setInt(5, authorBean.getId());
			preparedStatement.execute();
			
	}	
}
