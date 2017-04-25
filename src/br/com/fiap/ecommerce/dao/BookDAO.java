package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;

public class BookDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
	
	public void setBook(BookBean book) {
		connection = ConnectionFactory.getConnection();
		sql = "Insert Into Books Values((select max(bookID)+1 from Books), ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getName());
			preparedStatement.setDouble(2, book.getPrice());
			preparedStatement.setInt(3, book.getAutorID());
			preparedStatement.setInt(4, book.getGenreID());
			preparedStatement.setInt(5, book.getPublisherID());
			preparedStatement.setInt(6, book.getISBN());
			preparedStatement.setString(7, book.getSynopsis());
			
			preparedStatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public BookBean getBook(BookBean book) {
		BookBean bookBean = null;
		
		connection = ConnectionFactory.getConnection();
		sql = "Select B.BookID B.Name Book, B.Price Price, A.Name Author, G.Genre Genre, P.Publisher Publisher, B.Isbn ISBN, B.Synopsis Synopsis "
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
				String authorName = resultSet.getString("Author");
				String genreName = resultSet.getString("Genre");
				String publisherName = resultSet.getString("Publisher");
				int ISBN = resultSet.getInt("ISBN");
				String synopsis = resultSet.getString("Synopsis");
				
				bookBean = new BookBean(bookID, name, price, authorName, genreName, publisherName, ISBN, synopsis);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return bookBean;
	}

	public List<BookBean> getListBooks(BookBean book) {
		return null;
	}

	public static void deleteBook(BookBean book) {
		
	}

	public void alterBook(BookBean book) {
		
	}
}
