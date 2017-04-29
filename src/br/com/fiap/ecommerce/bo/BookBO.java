package br.com.fiap.ecommerce.bo;

import java.util.List;

import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.dao.BookDAO;

public class BookBO {
	private void setBook(BookBean book) {
		BookDAO bookDAO = new BookDAO();
//		bookDAO.setBook(book);
	}
	
	private BookBean getBook(BookBean book) {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.getBook(book);
	}
	
	private List<BookBean> getListBooks(BookBean book) {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.getListBooks(book);
	}
	
	private void deleteBook(BookBean book) {
		BookDAO bookDAO = new BookDAO();
		BookDAO.deleteBook(book);
	}
	
	private void alterBook(BookBean book) {
		BookDAO bookDAO = new BookDAO();
		bookDAO.alterBook(book);
	}
}
