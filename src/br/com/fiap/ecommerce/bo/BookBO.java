package br.com.fiap.ecommerce.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.dao.BookDAO;

public class BookBO {
	public void setBook(BookBean book) throws SQLException {
		BookDAO bookDAO = new BookDAO();		
		bookDAO.setBook(book);
	}
	
	public BookBean getBook(BookBean book) throws SQLException {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.getBook(book);
	}
	
	public List<BookBean> getListBooks(BookBean book) throws SQLException {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.getListBooks(book);
	}
	
	public List<BookBean> getListBooksDiscount(BookBean book) throws SQLException {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.getListBooksDiscount(book);
	}
	
	public List<BookBean> getListBooksAdvanced(BookBean book) throws SQLException {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.getListBooksAdvanced(book);
	}
	
	public void deleteBook(BookBean book) throws SQLException {
		BookDAO bookDAO = new BookDAO();
		bookDAO.deleteBook(book);
	}
	
	public void alterBook(BookBean book) throws SQLException {
		BookDAO bookDAO = new BookDAO();
		bookDAO.alterBook(book);
	}
}
