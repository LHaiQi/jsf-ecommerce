package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;

public class PublisherDAO {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String sqlCommand = "";
	
	public List<PublisherBean> getListPublisher(String name){
		List<PublisherBean> listPublishers = new ArrayList<PublisherBean>();
		
		connection = ConnectionFactory.getConnection();
		sqlCommand = "Select * From Publisher Where Publisher like ?";
		
		try {
			preparedStatement = connection.prepareStatement(sqlCommand);
			preparedStatement.setString(1, '%' + name + '%');
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int rsID = resultSet.getInt("publisherID");
				String rsName = resultSet.getString("name");
				
				listPublishers.add(new PublisherBean(rsID, rsName));
				
			}
		} catch (Exception e) {
			System.out.println("Erro ao Buscar Lista de Editoras: " + e);
		}
		
		return listPublishers;
	}
	
	public PublisherBean getPublisher(String name){
		PublisherBean publisher = null;
		
		connection = ConnectionFactory.getConnection();
		sqlCommand = "Select * From Publisher Where Publisher = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sqlCommand);
			preparedStatement.setString(1, name);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int rsID = resultSet.getInt("publisherID");
				String rsName = resultSet.getString("publisher");
				
				publisher = new PublisherBean(rsID, rsName);
				
			}
		} catch (Exception e) {
			System.out.println("Erro ao Buscar Editora: " + e);
		}
		
		return publisher;
	}
}
