package br.com.fiap.ecommerce.dao;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.bean.AuthorBean;
import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;

public class BookDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
	
	public int generateID(){
		int bookID = 0;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select Max(bookID) as bookID From Books";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				bookID = resultSet.getInt("bookID");
			}			
		} 
		catch (Exception e) {
			bookID = 0;
		}
		
		return bookID;
	}
	
	public void setBook(BookBean book, AuthorBean author, PublisherBean publisher, GenreBean genre) {
		connection = ConnectionFactory.getConnection();
		sql = "Insert Into Books Values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, generateID());
			preparedStatement.setString(2, book.getName());
			preparedStatement.setDouble(3, book.getPrice());
			preparedStatement.setInt(4, author.getId());
			preparedStatement.setInt(5, genre.getId());
			preparedStatement.setInt(6, publisher.getId());
			preparedStatement.setInt(7, book.getISBN());
			preparedStatement.setString(8, book.getSynopsis());
			
			preparedStatement.execute();
		} 
		catch (Exception e) {
			System.out.println("Erro ao Inserir Book: " + e);
		}
	}

	public BookBean getBook(BookBean book) {
		BookBean bookBean = null;
		AuthorBean authorBean = new AuthorBean();
		PublisherBean publisherBean = new PublisherBean();
		GenreBean genreBean = new GenreBean();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select B.BookID, B.Name Book, B.Price Price, A.Name Author, G.Genre Genre, P.Publisher Publisher, B.Isbn ISBN, B.Synopsis Synopsis "
		    + " From Books B "
		    + " Inner Join Author A On B.Authorid = A.Authorid "
		    + " Inner Join Genre G On B.Genreid = G.Genreid "
            + " Inner Join Publisher P On B.Publisherid = P.Publisherid "
            + " Where B.Name = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getName());
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int bookID  = resultSet.getInt("BookID");
				String name = resultSet.getString("Book");
				double price = resultSet.getDouble("Price");
				authorBean.setName(resultSet.getString("Author"));
				genreBean.setGenre(resultSet.getString("Genre"));
				publisherBean.setPublisher(resultSet.getString("Publisher"));
				int ISBN = resultSet.getInt("ISBN");
				String synopsis = resultSet.getString("Synopsis");
				
				bookBean = new BookBean(bookID, ISBN, name, synopsis, price, authorBean, publisherBean, genreBean);
			}
		} 
		catch (Exception e) {
			System.out.println("Erro ao Buscar Book: " + e);
		}
		
		return bookBean;
	}

	public List<BookBean> getListBooks(BookBean book) {
		List<BookBean> listBook = new ArrayList<BookBean>();
		AuthorBean authorBean = new AuthorBean();
		PublisherBean publisherBean = new PublisherBean();
		GenreBean genreBean = new GenreBean();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select B.BookID, B.Name Book, B.Price Price, A.Name Author, G.Genre Genre, P.Publisher Publisher, B.Isbn ISBN, B.Synopsis Synopsis "
			    + " From Books B "
			    + " Inner Join Author A On B.Authorid = A.Authorid "
			    + " Inner Join Genre G On B.Genreid = G.Genreid "
	            + " Inner Join Publisher P On B.Publisherid = P.Publisherid "
	            + " Where B.Name like ? ";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getName());
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int bookID  = resultSet.getInt("BookID");
				String name = resultSet.getString("Book");
				double price = resultSet.getDouble("Price");
				authorBean.setName(resultSet.getString("Author"));
				genreBean.setGenre(resultSet.getString("Genre"));
				publisherBean.setPublisher(resultSet.getString("Publisher"));
				int ISBN = resultSet.getInt("ISBN");
				String synopsis = resultSet.getString("Synopsis");
				
				listBook.add(new BookBean(bookID, ISBN, name, synopsis, price, authorBean, publisherBean, genreBean));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listBook;
	}

	public static void deleteBook(BookBean book) {
		
	}

	public void alterBook(BookBean book) {
		
	}
}
