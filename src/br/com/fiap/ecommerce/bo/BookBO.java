package br.com.fiap.ecommerce.bo;

import java.util.List;

import br.com.fiap.ecommerce.bean.AuthorBean;
import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.dao.BookDAO;

public class BookBO {
	public void setBook(BookBean book, AuthorBean author, PublisherBean publisher, GenreBean genre) {
		BookDAO bookDAO = new BookDAO();
		bookDAO.setBook(book, author, publisher, genre);
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
