package br.com.fiap.ecommerce.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.bean.WishlistBean;
import br.com.fiap.ecommerce.dao.WishlistDAO;

public class WishlistBO {

	public void setWishlist(WishlistBean wishlist) throws SQLException {
		WishlistDAO wishlistDAO = new WishlistDAO();
		wishlistDAO.setWishlist(wishlist);
	}

	public List<BookBean> getAllWishes(WishlistBean wishlist) throws SQLException {
		WishlistDAO wishlistDAO = new WishlistDAO();
		return wishlistDAO.getAllWishes(wishlist);
	}

	public void deleteWishItem(WishlistBean wishlist) throws SQLException {
		WishlistDAO wishlistDAO = new WishlistDAO();
		wishlistDAO.deleteWishItem(wishlist);
	}
}
