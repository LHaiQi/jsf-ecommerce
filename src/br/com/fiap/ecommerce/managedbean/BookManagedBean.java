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
import br.com.fiap.ecommerce.bo.GenreBO;
import br.com.fiap.ecommerce.bo.PublisherBO;

@ManagedBean
@SessionScoped
public class BookManagedBean {
	private BookBean book = new BookBean();
	private List<BookBean> listBook = new ArrayList<BookBean>();	
	private List<AuthorBean> listAuthor = new ArrayList<AuthorBean>();
	private List<GenreBean> listGenre = new ArrayList<GenreBean>();
	private List<PublisherBean> ListPublisher = new ArrayList<PublisherBean>();
	
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

	public List<AuthorBean> getListAuthor() {
		AuthorBO authorBO = new AuthorBO();
		return listAuthor = authorBO.getListAuthor();
	}

	public void setListAuthor(List<AuthorBean> listAuthor) {
		this.listAuthor = listAuthor;
	}
	
	public List<GenreBean> getListGenre() {
		GenreBO genreBO = new GenreBO();
		return listGenre = genreBO.getListGenre();
	}

	public void setListGenre(List<GenreBean> listGenre) {
		this.listGenre = listGenre;
	}

	public List<PublisherBean> getListPublisher() {
		PublisherBO publisherBO = new PublisherBO();
		return ListPublisher = publisherBO.getListPubliser();
	}

	public void setListPublisher(List<PublisherBean> listPublisher) {
		ListPublisher = listPublisher;
	}

	public String insertBookController() {
		BookBO bookBO = new BookBO();		
		bookBO.setBook(book);		
		
		return "insert-book";
	}
		
	public String searchBookController(){
		BookBO bookBO = new BookBO();
		book = bookBO.getBook(book);
		
		return "search-book";
	}

	public String searchListBookController(){
		BookBO bookBO = new BookBO();
		listBook = bookBO.getListBooks(book);
		
		return "search-book";
	}
}
