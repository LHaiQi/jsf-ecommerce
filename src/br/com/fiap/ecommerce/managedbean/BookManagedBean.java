package br.com.fiap.ecommerce.managedbean;

import br.com.fiap.ecommerce.bean.AuthorBean;
import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.bo.BookBO;

public class BookManagedBean {
	private BookBean book;
	private AuthorBean author;
	private PublisherBean publisher;
	private GenreBean genre;

	public BookBean getBook() {
		return book;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}
	
	public String insertBookCntroller() {
		BookBO bookBO = new BookBO();
		bookBO.setBook(book, author, publisher, genre);
		
		return "insert-book";
	}
}
