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
	
	public GenreBean pesquisarGenre(GenreBean genre){
		GenreBean newGenre = null;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From GENRE Where GENREID = ?";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, genre.getId());
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int id = resultSet.getInt("GenreID");
				String genreName = resultSet.getString("Genre");
				
				newGenre = new GenreBean(id, genreName);
			}
			
		}catch(Exception e){
			System.out.println("Erro ao buscar genero: " + e);
		}
		
		return newGenre;
	}
	
	public List<GenreBean> getListGenre() {
		List<GenreBean> listGenres = new ArrayList<GenreBean>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Genre";
		
		try {
			preparedStatement = connection.prepareStatement(sql);			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("GenreID");
				String genreName = resultSet.getString("Genre");
				
				listGenres.add(new GenreBean(id, genreName));							
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar lista genero: " + e);
		}
		
		return listGenres;
	}
	
	public List<GenreBean> pesquisarAllGenres(GenreBean genre){
		List<GenreBean> listGenres = new ArrayList<GenreBean>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Genre Where Genre Like ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + genre.getGenre() + "%");
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("GenreID");
				String genreName = resultSet.getString("Genre");
				
				listGenres.add(new GenreBean(id, genreName));							
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar lista genero: " + e);
		}
		
		return listGenres;
	}
	
	public void inserirGenre(GenreBean genre){
		connection = ConnectionFactory.getConnection();
		sql = "INSERT INTO GENRE VALUES ((select max(GENREID)+1 from GENRE),?)";
		
		try {
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,genre.getGenre());
			preparedStatement.execute();
			
		} catch (Exception e) {
			System.out.println("Erro ao inserir Genre: " + e);
		}
		
	}
	
	public void deletarGenre(GenreBean genre){
		connection = ConnectionFactory.getConnection();
		sql = "DELETE FROM GENRE WHERE GENREID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, genre.getId());
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			System.out.println("Erro ao apagar Genre(a): " + e);
		}
	}
	
	public void alterarGenre(GenreBean genre) {
		connection = ConnectionFactory.getConnection();
		sql = "Update GENRE set GENRE = ? Where GENREID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,genre.getGenre());
			preparedStatement.setInt(2, genre.getId());
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			System.out.println("Erro ao Editar Genre: " + e);
		}
	}
}
