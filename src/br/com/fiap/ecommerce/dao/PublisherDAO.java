package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.bean.UserBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;
import oracle.net.aso.p;

public class PublisherDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
	
	public PublisherBean getPublisher(PublisherBean publisher){
		PublisherBean newPublisher = null;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Publisher Where PublisherID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, publisher.getId());
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int id = resultSet.getInt("PublisherID");
				String publisherName = resultSet.getString("Publisher");
				int cnpj = resultSet.getInt("cnpj");
				String email = resultSet.getString("Email");;
				int phoneNumber = resultSet.getInt("PhoneNumber");
				String country = resultSet.getString("Country");;
				String state = resultSet.getString("cState");;
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
				String state = resultSet.getString("cState");;
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

	public void setPublisher(PublisherBean publisherBean) {
		connection = ConnectionFactory.getConnection();
		sql = "Insert Into Publisher Values ((select max(publisherId)+1 from publisher),?,?,?,?,?,?,?,?,?)";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, publisherBean.getPublisher());
			preparedStatement.setInt(2, publisherBean.getCnpj());
			preparedStatement.setString(3, publisherBean.getEmail());
			preparedStatement.setInt(4, publisherBean.getPhoneNumber());
			preparedStatement.setString(5, publisherBean.getCountry());
			preparedStatement.setString(6, publisherBean.getState());
			preparedStatement.setString(7, publisherBean.getStreet());
			preparedStatement.setInt(8, publisherBean.getZipCode());
			preparedStatement.setInt(9, publisherBean.getAddressNumber());
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir editor(a): " + e);

		}
		
	}
	
	public void deletePublisher(PublisherBean publisherBean){
		connection = ConnectionFactory.getConnection();
		sql = "DELETE FROM PUBLISHER WHERE PUBLISHERID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, publisherBean.getId());
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			System.out.println("Erro ao apagar editor(a): " + e);
		}
	}

	public void alterPublisher(PublisherBean publisher) {
		connection = ConnectionFactory.getConnection();
		sql = "Update Publisher set Publisher = ?, Cnpj = ?, Email = ?,"
			+ " PhoneNumber = ?, Country = ?, cState = ?, Street = ?,"
			+ " ZipCode = ?, AddressNumber = ? Where PublisherID = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, publisher.getPublisher());
			ps.setInt(2, publisher.getCnpj());
			ps.setString(3, publisher.getEmail());
			ps.setInt(4, publisher.getPhoneNumber());
			ps.setString(5, publisher.getCountry());
			ps.setString(6, publisher.getState());
			ps.setString(7, publisher.getStreet());
			ps.setInt(8, publisher.getZipCode());
			ps.setInt(9, publisher.getAddressNumber());
			ps.setInt(10, publisher.getId());
			
			ps.execute();
			
		} catch (Exception e) {
			System.out.println("Erro ao Editar editora: " + e);
		}
	}
}
