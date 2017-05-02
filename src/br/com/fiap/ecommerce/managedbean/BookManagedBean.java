package br.com.fiap.ecommerce.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fiap.ecommerce.bean.AuthorBean;
import br.com.fiap.ecommerce.bean.BookBean;
import br.com.fiap.ecommerce.bean.GenreBean;
import br.com.fiap.ecommerce.bean.PublisherBean;
import br.com.fiap.ecommerce.bo.AuthorBO;
import br.com.fiap.ecommerce.bo.BookBO;

@ManagedBean
@SessionScoped
public class BookManagedBean {
	private BookBean book = new BookBean();
	private List<BookBean> listBook = new ArrayList<BookBean>();	
	private AuthorBean author = new AuthorBean();	
	private PublisherBean publisher = new PublisherBean();
	private GenreBean genre = new GenreBean();
	
	public BookBean getBook() {
		return book;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}

	public List<BookBean> getListBook() {
		return listBook;
	}

	public void setListBook(List<BookBean> listBook) {
		this.listBook = listBook;
	}

	public AuthorBean getAuthor() {
		return author;
	}

	public void setAuthor(AuthorBean author) {
		this.author = author;
	}

	public PublisherBean getPublisher() {
		return publisher;
	}

	public void setPublisher(PublisherBean publisher) {
		this.publisher = publisher;
	}

	public GenreBean getGenre() {
		return genre;
	}

	public void setGenre(GenreBean genre) {
		this.genre = genre;
	}

	public String insertBookController() {
		BookBO bookBO = new BookBO();
		bookBO.setBook(book, author, publisher, genre);
		
		return "insert-book";
	}
		
	public String searchBookController(){
		BookBO bookBO = new BookBO();
		book = bookBO.getBook(book);
		
		author = book.getAuthor();
		publisher = book.getPublisher();
		genre = book.getGenre();
		
		return "search-book";
	}

	public String searchListBookController(){
		BookBO bookBO = new BookBO();
		listBook = bookBO.getListBooks(book);
		
		return "search-book";
	}

	public List<AuthorBean> getAuthorItem(){
		AuthorBO authorBO = new AuthorBO();
		return authorBO.getListAuthor();
	}
}
