package br.com.fiap.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.ecommerce.bean.BookBean;
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
		List<WishlistBean> listWishes = new ArrayList<>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select b.name Book From Wishlist w "
			  + " Inner Join Books b On b.bookID = w.bookID "
			  + " Where userID = ?";
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, wishlist.getLogin().getUserId());
		
		resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			bookBean.setName(resultSet.getString("Book"));
			
			listWishes.add(new WishlistBean(bookBean, wishlist.getLogin()));
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
