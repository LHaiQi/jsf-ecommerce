package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;

public class GenreDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
	
	public GenreBean pesquisarGenre(GenreBean genre) throws SQLException{
		GenreBean newGenre = null;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From GENRE Where GENREID = ?";
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, genre.getId());
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int id = resultSet.getInt("GenreID");
				String genreName = resultSet.getString("Genre");
				
				newGenre = new GenreBean(id, genreName);
			}
			
		
		return newGenre;
	}
	
	public List<GenreBean> getListGenre() throws SQLException {
		List<GenreBean> listGenres = new ArrayList<GenreBean>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Genre";
		
			preparedStatement = connection.prepareStatement(sql);			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("GenreID");
				String genreName = resultSet.getString("Genre");
				
				listGenres.add(new GenreBean(id, genreName));							
			}
		
		return listGenres;
	}
	
	public List<GenreBean> pesquisarAllGenres(GenreBean genre) throws SQLException{
		List<GenreBean> listGenres = new ArrayList<GenreBean>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Genre Where Genre Like ?";
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + genre.getGenre() + "%");
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("GenreID");
				String genreName = resultSet.getString("Genre");
				
				listGenres.add(new GenreBean(id, genreName));							
			}
		
		return listGenres;
	}
	
	public int generateGenreID(){
		int genreID = 1;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select Max(genreID) as genreID From genre";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
 			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				genreID = resultSet.getInt("genreID");
				genreID++;
			}			
		} 
		catch (Exception e) {
			genreID = 1;
		}
		
		return genreID;
	}
	
	public void inserirGenre(GenreBean genre) throws SQLException{
		connection = ConnectionFactory.getConnection();
		sql = "INSERT INTO GENRE VALUES (?,?)";
		
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,generateGenreID());
			preparedStatement.setString(2,genre.getGenre());
			preparedStatement.execute();
					
	}
	
	public void deletarGenre(GenreBean genre) throws SQLException{
		connection = ConnectionFactory.getConnection();
		sql = "DELETE FROM GENRE WHERE GENREID = ?";
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, genre.getId());
			
			preparedStatement.execute();
			
	}
	
	public void alterarGenre(GenreBean genre) throws SQLException {
		connection = ConnectionFactory.getConnection();
		sql = "Update GENRE set GENRE = ? Where GENREID = ?";
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,genre.getGenre());
			preparedStatement.setInt(2, genre.getId());
			
			preparedStatement.execute();
			
	}
}
