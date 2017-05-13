package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;

public class PublisherDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
	
	public int generateID(){
		int publisherID = 1;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select Max(publisherID) as publisherID From publisher";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				publisherID = resultSet.getInt("publisherID") + 1;
			}			
		} 
		catch (Exception e) {
			publisherID = 1;
		}
		
		return publisherID;
	}
	
	public PublisherBean getPublisher(PublisherBean publisher) throws SQLException{
		PublisherBean newPublisher = null;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Publisher Where PublisherID = ?";
			
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, publisher.getId());
		
		resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next()) {
			int id = resultSet.getInt("PublisherID");
			String publisherName = resultSet.getString("Publisher");
			long cnpj = resultSet.getLong("cnpj");
			String email = resultSet.getString("Email");;
			int phoneNumber = resultSet.getInt("PhoneNumber");
			String country = resultSet.getString("Country");;
			String state = resultSet.getString("cState");;
			String street = resultSet.getString("Street");;
			int zipCode = resultSet.getInt("ZipCode");
			int addressNumber = resultSet.getInt("AddressNumber");
			
			newPublisher = new PublisherBean(id, publisherName, cnpj, email, phoneNumber, country, state, street, zipCode, addressNumber);
		}
	
		return newPublisher;
	}
	
	public List<PublisherBean> getAllPublishers() throws SQLException {
		List<PublisherBean> listPublishers = new ArrayList<PublisherBean>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * From Publisher";
		
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("PublisherID");
			String publisherName = resultSet.getString("Publisher");
			long cnpj = resultSet.getLong("cnpj");
			String email = resultSet.getString("Email");;
			int phoneNumber = resultSet.getInt("PhoneNumber");
			String country = resultSet.getString("Country");;
			String state = resultSet.getString("cState");;
			String street = resultSet.getString("Street");;
			int zipCode = resultSet.getInt("ZipCode");
			int addressNumber = resultSet.getInt("AddressNumber");
			
			listPublishers.add(new PublisherBean(id, publisherName, cnpj, email, phoneNumber, country, state, street, zipCode, addressNumber));							
		}
		
		return listPublishers;
	}
	
	public List<PublisherBean> getAllPublishers(PublisherBean publisher) {
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
				long cnpj = resultSet.getLong("cnpj");
				String email = resultSet.getString("Email");;
				int phoneNumber = resultSet.getInt("PhoneNumber");
				String country = resultSet.getString("Country");;
				String state = resultSet.getString("cState");;
				String street = resultSet.getString("Street");;
				int zipCode = resultSet.getInt("ZipCode");
				int addressNumber = resultSet.getInt("AddressNumber");
				
				listPublishers.add(new PublisherBean(id, publisherName, cnpj, email, phoneNumber, country, state, street, zipCode, addressNumber));							
			}
		} 
		catch (Exception e) {
			System.out.println("pesquisar todos publishers " + e);
		}	
		
		return listPublishers;
	}

	public void setPublisher(PublisherBean publisherBean) throws SQLException {
		connection = ConnectionFactory.getConnection();
		sql = "Insert Into Publisher(PublisherID, Publisher, CNPJ, Email, PhoneNumber, Country, CState, Street, ZipCode, AddressNumber) "
				+ "Values (?,?,?,?,?,?,?,?,?,?)";
						
		PreparedStatement preparedStatement = connection.prepareStatement(sql);		
		preparedStatement.setInt(1, generateID());
		preparedStatement.setString(2, publisherBean.getPublisher());
		preparedStatement.setLong(3, publisherBean.getCnpj());
		preparedStatement.setString(4, publisherBean.getEmail());
		preparedStatement.setInt(5, publisherBean.getPhoneNumber());
		preparedStatement.setString(6, publisherBean.getCountry());
		preparedStatement.setString(7, publisherBean.getState());
		preparedStatement.setString(8, publisherBean.getStreet());
		preparedStatement.setInt(9, publisherBean.getZipCode());
		preparedStatement.setInt(10, publisherBean.getAddressNumber());
		
		preparedStatement.execute();	
	}
	
	public void deletePublisher(PublisherBean publisherBean) throws SQLException{
		connection = ConnectionFactory.getConnection();
		sql = "DELETE FROM PUBLISHER WHERE PUBLISHERID = ?";
				
		preparedStatement = connection.prepareStatement(sql);		
		preparedStatement.setInt(1, publisherBean.getId());
		
		preparedStatement.execute();
	}

	public void alterPublisher(PublisherBean publisher) throws SQLException {
		connection = ConnectionFactory.getConnection();
		sql = "Update Publisher set Publisher = ?, Cnpj = ?, Email = ?,"
			+ " PhoneNumber = ?, Country = ?, cState = ?, Street = ?,"
			+ " ZipCode = ?, AddressNumber = ? Where PublisherID = ?";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, publisher.getPublisher());
		ps.setLong(2, publisher.getCnpj());
		ps.setString(3, publisher.getEmail());
		ps.setInt(4, publisher.getPhoneNumber());
		ps.setString(5, publisher.getCountry());
		ps.setString(6, publisher.getState());
		ps.setString(7, publisher.getStreet());
		ps.setInt(8, publisher.getZipCode());
		ps.setInt(9, publisher.getAddressNumber());
		ps.setInt(10, publisher.getId());
		
		ps.execute();
	}	
}
