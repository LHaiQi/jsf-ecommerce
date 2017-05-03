package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				bookID = resultSet.getInt("bookID") + 1;
			}			
		} 
		catch (Exception e) {
			bookID = 0;
		}
		
		return bookID;
	}
	
	public void setBook(BookBean book) {
		connection = ConnectionFactory.getConnection();
		String sql = "Insert Into Books(BookID, Name, Price, AuthorID, GenreID, PublisherID, ISBN, Synopsis) "
				+ "Values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, generateID());
			preparedStatement.setString(2, book.getName());
			preparedStatement.setDouble(3, book.getPrice());
			preparedStatement.setInt(4, book.getAuthor().getId());
			preparedStatement.setInt(5, book.getGenre().getId());
			preparedStatement.setInt(6, book.getPublisher().getId());
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
            + " Where B.BookID = ? ";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, book.getBookID());
			
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
		sql = "Select B.BookID, B.Name Book, B.Price Price, A.Name || A.lastName Author, G.Genre Genre, P.Publisher Publisher, B.Isbn ISBN, B.Synopsis Synopsis "
			    + " From Books B "
			    + " Inner Join Author A On B.Authorid = A.Authorid "
			    + " Inner Join Genre G On B.Genreid = G.Genreid "
	            + " Inner Join Publisher P On B.Publisherid = P.Publisherid "
	            + " Where B.Name like ? "
	            + " Order By Book";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + book.getName() + "%");
			
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
			System.out.println("Erro ao Buscar Lista Book: " + e);
		}
		
		return listBook;
	}

	public void deleteBook(BookBean book) {
		connection = ConnectionFactory.getConnection();
		sql = "Delete From Books Where BookID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, book.getBookID());
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			System.out.println("Erro ao deletar Book: " + e);
		}
	}

	public void alterBook(BookBean book) {
		connection = ConnectionFactory.getConnection();
		String sql = "Update Books Set Name = ?, Price = ?, AuthorID = ?, GenreID = ?, PublisherID = ?, ISBN = ?, Synopsis = ? "
				+ "Where BookId = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getName());
			preparedStatement.setDouble(2, book.getPrice());
			preparedStatement.setInt(3, book.getAuthor().getId());
			preparedStatement.setInt(4, book.getGenre().getId());
			preparedStatement.setInt(5, book.getPublisher().getId());
			preparedStatement.setInt(6, book.getISBN());
			preparedStatement.setString(7, book.getSynopsis());
			preparedStatement.setInt(8, book.getBookID());
			
			preparedStatement.execute();
		} 
		catch (Exception e) {
			System.out.println("Erro ao Alterar Book: " + e);
		}
	}
}
