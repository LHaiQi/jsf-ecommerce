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
		int bookID = 1;
		
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
			bookID = 1;
		}
		
		return bookID;
	}
	
	public void setBook(BookBean book) throws SQLException {
		connection = ConnectionFactory.getConnection();
		String sql = "Insert Into Books(BookID, Name, Price, AuthorID, GenreID, PublisherID, ISBN, Synopsis) "
				+ "Values(?, ?, ?, ?, ?, ?, ?, ?)";
		
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

	public BookBean getBook(BookBean book) throws SQLException {
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
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, book.getBookID());
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int bookID  = resultSet.getInt("BookID");
				String name = resultSet.getString("NAME");
				double price = resultSet.getDouble("Price");
				authorBean.setName(resultSet.getString("Author"));
				genreBean.setGenre(resultSet.getString("Genre"));
				publisherBean.setPublisher(resultSet.getString("Publisher"));
				int ISBN = resultSet.getInt("ISBN");
				String synopsis = resultSet.getString("Synopsis");
				String bookImage= resultSet.getString("BookImage");
				int discount = resultSet.getInt("Discount");
				int quantity = resultSet.getInt("Quantity");
				
				bookBean = new BookBean(bookID, ISBN, name, synopsis, price, authorBean, publisherBean, genreBean,bookImage,discount,quantity);
			}
		
		return bookBean;
	}

	public List<BookBean> getListBooks(BookBean book) throws SQLException {
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
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + book.getName() + "%");
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int bookID  = resultSet.getInt("BookID");
				String name = resultSet.getString("NAME");
				double price = resultSet.getDouble("Price");
				authorBean.setName(resultSet.getString("Author"));
				genreBean.setGenre(resultSet.getString("Genre"));
				publisherBean.setPublisher(resultSet.getString("Publisher"));
				int ISBN = resultSet.getInt("ISBN");
				String synopsis = resultSet.getString("Synopsis");
				String bookImage= resultSet.getString("BookImage");
				int discount = resultSet.getInt("Discount");
				int quantity = resultSet.getInt("Quantity");
				
				listBook.add(new BookBean(bookID, ISBN, name, synopsis, price, authorBean, publisherBean, genreBean,bookImage,discount,quantity));
			}
		
		return listBook;
	}
	
	
	public List<BookBean> getListBooksDiscount(BookBean book) throws SQLException {
		List<BookBean> listBook = new ArrayList<BookBean>();
		AuthorBean authorBean = new AuthorBean();
		PublisherBean publisherBean = new PublisherBean();
		GenreBean genreBean = new GenreBean();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select * from BOOKS where DISCOUNT > 0";
		
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int bookID  = resultSet.getInt("BookID");
				String name = resultSet.getString("NAME");
				double price = resultSet.getDouble("Price");
				authorBean.setId(resultSet.getInt("AuthorID"));
				genreBean.setId(resultSet.getInt("GenreID"));
				publisherBean.setId(resultSet.getInt("PublisherID"));
				int ISBN = resultSet.getInt("ISBN");
				String synopsis = resultSet.getString("Synopsis");
				String bookImage= resultSet.getString("BookImage");
				int discount = resultSet.getInt("Discount");
				int quantity = resultSet.getInt("Quantity");
				
				listBook.add(new BookBean(bookID, ISBN, name, synopsis, price, authorBean, publisherBean, genreBean,bookImage,discount,quantity));
			}
		
		return listBook;
	}
	
	
	public void deleteBook(BookBean book) throws SQLException {
		connection = ConnectionFactory.getConnection();
		sql = "Delete From Books Where BookID = ?";
		
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, book.getBookID());
			
			preparedStatement.execute();
			
	}

	public void alterBook(BookBean book) throws SQLException {
		connection = ConnectionFactory.getConnection();
		String sql = "Update Books Set Name = ?, Price = ?, AuthorID = ?, GenreID = ?, PublisherID = ?, ISBN = ?, Synopsis = ? "
				+ "Where BookId = ?";
		
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
}
