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
import br.com.fiap.ecommerce.bean.WishlistBean;
import br.com.fiap.ecommerce.connection.ConnectionFactory;

public class WishlistDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
		
	public void setWishlist(WishlistBean wishlist) throws SQLException {
		connection = ConnectionFactory.getConnection();
		sql = "Insert Into Wishlist Values(?,?)";
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, wishlist.getBook().getBookID());
		preparedStatement.setInt(2, wishlist.getLogin().getLoginId());
		
		preparedStatement.execute();
	}

	public List<WishlistBean> getAllWishes(WishlistBean wishlist) throws SQLException {
		BookBean bookBean = null;
		AuthorBean authorBean = new AuthorBean();
		PublisherBean publisherBean = new PublisherBean();
		GenreBean genreBean = new GenreBean();
		List<WishlistBean> listWishes = new ArrayList<>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select b.* From Wishlist w "
			  + " Inner Join Books b On b.bookID = w.bookID "
			  + " Where userID = ?";
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, wishlist.getLogin().getUserId());
		
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
			
			bookBean = new BookBean(bookID, ISBN, name, synopsis, price, authorBean, publisherBean, genreBean,bookImage,discount,quantity);
		}
		
		return listWishes;
	}

	public void deleteWishItem(WishlistBean wishlist) throws SQLException {
		connection = ConnectionFactory.getConnection();
		sql = "Delete From Wishlist Where BookId = ? and UserID = ?";
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, wishlist.getBook().getBookID());
		preparedStatement.setInt(2, wishlist.getLogin().getUserId());
		
		preparedStatement.execute();
	}
}
