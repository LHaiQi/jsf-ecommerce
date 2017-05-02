package br.com.fiap.ecommerce.bo;

import java.util.List;

import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.dao.AuthorDAO;
import br.com.fiap.ecommerce.dao.BookDAO;
import br.com.fiap.ecommerce.dao.GenreDAO;
import br.com.fiap.ecommerce.dao.PublisherDAO;

public class BookBO {
	public void setBook(BookBean book) {
		BookDAO bookDAO = new BookDAO();		
		bookDAO.setBook(book);
	}
	
	public BookBean getBook(BookBean book) {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.getBook(book);
	}
	
	public List<BookBean> getListBooks(BookBean book) {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.getListBooks(book);
	}
	
	public void deleteBook(BookBean book) {
		BookDAO bookDAO = new BookDAO();
		BookDAO.deleteBook(book);
	}
	
	public void alterBook(BookBean book) {
		BookDAO bookDAO = new BookDAO();
		bookDAO.alterBook(book);
	}
}
