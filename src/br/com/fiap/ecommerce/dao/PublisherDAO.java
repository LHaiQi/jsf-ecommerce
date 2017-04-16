package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;

public class PublisherDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
	
	public PublisherBean getPublisher(PublisherBean publisher){
		PublisherBean newPublisher = null;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Publisher Where Publisher = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, publisher.getPublisher());
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int id = resultSet.getInt("PublisherID");
				String publisherName = resultSet.getString("Publisher");
				int cnpj = resultSet.getInt("cnpj");
				String email = resultSet.getString("Email");;
				int phoneNumber = resultSet.getInt("PhoneNumber");
				String country = resultSet.getString("Country");;
				String state = resultSet.getString("State");;
				String street = resultSet.getString("Street");;
				int zipCode = resultSet.getInt("ZipCode");
				int addressNumber = resultSet.getInt("AddressNumber");
				
				newPublisher = new PublisherBean(id, publisherName, cnpj, email, phoneNumber, country, state, street, zipCode, addressNumber);
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar editora: " + e);
		}
	
		return newPublisher;
	}

	public List<PublisherBean> getAllPublishers(PublisherBean publisher){
		List<PublisherBean> listPublishers = new ArrayList<PublisherBean>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Publisher Where Publisher Like ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + publisher.getPublisher() + "%");
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("PublisherID");
				String publisherName = resultSet.getString("Publisher");
				int cnpj = resultSet.getInt("cnpj");
				String email = resultSet.getString("Email");;
				int phoneNumber = resultSet.getInt("PhoneNumber");
				String country = resultSet.getString("Country");;
				String state = resultSet.getString("State");;
				String street = resultSet.getString("Street");;
				int zipCode = resultSet.getInt("ZipCode");
				int addressNumber = resultSet.getInt("AddressNumber");
				
				listPublishers.add(new PublisherBean(id, publisherName, cnpj, email, phoneNumber, country, state, street, zipCode, addressNumber));							
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar lista editora: " + e);
		}
		
		return listPublishers;
	}
}