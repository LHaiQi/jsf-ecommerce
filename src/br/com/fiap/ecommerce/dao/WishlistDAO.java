package br.com.fiap.ecommerce.dao;

import java.math.BigDecimal;
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

	public List<BookBean> getAllWishes(WishlistBean wishlist) throws SQLException {				
		List<BookBean> listWishes = new ArrayList<>();
		
		connection = ConnectionFactory.getConnection();
		sql = "Select b.* From Wishlist w "
			  + " Inner Join Books b On b.bookID = w.bookID "
			  + " Where userID = ? Order By w.BookID";
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, wishlist.getLogin().getLoginId());
		
		resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			int bookID  = resultSet.getInt("BookID");
			String name = resultSet.getString("Name");
			double price = resultSet.getDouble("Price");
			Double bprice = price;
			String bookImage= resultSet.getString("BookImage");
			int discount = resultSet.getInt("Discount");
			double desconto = discount;
			desconto = desconto /100;
			desconto = 1-desconto;
			bprice = bprice * desconto;
			BigDecimal bd = new BigDecimal(bprice);
			bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
            Double preco  = bd.doubleValue();
			int quantity = resultSet.getInt("Quantity");
			
			listWishes.add(new BookBean(bookID, name, price, bookImage, discount, quantity,preco));
		}
		
		return listWishes;
	}

	public void deleteWishItem(WishlistBean wishlist) throws SQLException {
		connection = ConnectionFactory.getConnection();
		sql = "Delete From Wishlist Where UserID= ? And BookID = ?";
				
		preparedStatement = connection.prepareStatement(sql);		
		preparedStatement.setInt(1, wishlist.getLogin().getLoginId());
		preparedStatement.setInt(2,  wishlist.getBook().getBookID());
		
		preparedStatement.execute();
	}
}
