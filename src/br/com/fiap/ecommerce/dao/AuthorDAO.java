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
	
	public AuthorBean getAuthor(AuthorBean author){
		AuthorBean newAuthor = null;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Author Where AuthorID = ?";
		
		try {
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
		} catch (Exception e) {
			System.out.println("Erro ao buscar autor: " + e);
		}
	
		return newAuthor;
	}

	public List<AuthorBean> getAllAuthors(AuthorBean authorBean) {
		List<AuthorBean> listAuthor = new ArrayList<AuthorBean>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Author Where Name Like ?";
		
		try {
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
		} catch (Exception e) {
			System.out.println("Erro ao buscar lista author: " + e);
		}
		
		return listAuthor;
	}

	public void setAuthor(AuthorBean authorBean) {
		connection = ConnectionFactory.getConnection();
		sql = "Insert Into Author Values ((select max(authorId)+1 from Author),?,?,?,?)";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, authorBean.getName());
			preparedStatement.setString(2, authorBean.getLastName());
			preparedStatement.setString(3, authorBean.getGender());
			preparedStatement.setString(4, authorBean.getNationality());
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir autor(a): " + e);

		}
		
	}
	
	public void deleteAuthor(AuthorBean authorBean) {
		connection = ConnectionFactory.getConnection();
		sql = "DELETE FROM AUTHOR WHERE AUTHORID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, authorBean.getId());
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			System.out.println("Erro ao apagar autor(a): " + e);
		}
	}

	public void alterAuthor(AuthorBean authorBean) {
		connection = ConnectionFactory.getConnection();
		sql = "Update Author set Name = ?, LastName = ?, Gender = ?, Nationality = ? Where PublisherID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, authorBean.getName());
			preparedStatement.setString(2, authorBean.getLastName());
			preparedStatement.setString(3, authorBean.getGender());
			preparedStatement.setString(4, authorBean.getNationality());
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			System.out.println("Erro ao Editar autor(a): " + e);
		}
	}


	
	
}
