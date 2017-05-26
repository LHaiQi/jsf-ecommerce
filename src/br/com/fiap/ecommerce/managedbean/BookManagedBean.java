package br.com.fiap.ecommerce.managedbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
	private List<BookBean> listBook2 = new ArrayList<BookBean>();	
	private List<BookBean> listBook3 = new ArrayList<BookBean>();
	
	public BookBean getBook() {
		return book;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}

	public List<BookBean> getListBook2() {
		return listBook2;
	}

	public void setListBook2(List<BookBean> listBook2) {
		this.listBook2 = listBook2;
	}

	public List<BookBean> getListBook3() {
		return listBook3;
	}

	public void setListBook3(List<BookBean> listBook3) {
		this.listBook3 = listBook3;
	}

	public List<BookBean> getListBook() {
		return listBook;
	}

	public void setListBook(List<BookBean> listBook) {
		this.listBook = listBook;
	}

	public List<AuthorBean> getListAuthor() {
		AuthorBO authorBO = new AuthorBO();
		

		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgRetornaAutor");
		String message = text.getString("msgDetalhes");
		
		
		
		try {
			listAuthor = authorBO.getListAuthor();
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+ " " + e));
		}
		
		return listAuthor;
	}

	public void setListAuthor(List<AuthorBean> listAuthor) {
		this.listAuthor = listAuthor;
	}
	
	public List<GenreBean> getListGenre() {
		GenreBO genreBO = new GenreBO();
		
		

		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgRetornaCombo");
		String message = text.getString("msgDetalhes");
		
		
		try {
			listGenre = genreBO.getListGenre();
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}
		
		return listGenre;
	}

	public void setListGenre(List<GenreBean> listGenre) {
		this.listGenre = listGenre;
	}

	public List<PublisherBean> getListPublisher() {
		PublisherBO publisherBO = new PublisherBO();
		

		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgRetornaCombo");
		String message = text.getString("msgDetalhes");
		
		try {
			ListPublisher = publisherBO.getListPubliser();
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,message, v+" " + e));
		}
		
		return ListPublisher;
	}

	public void setListPublisher(List<PublisherBean> listPublisher) {
		ListPublisher = listPublisher;
	}

	public String insertBookController() {
		BookBO bookBO = new BookBO();

		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgRetornaCombo");
		String message = text.getString("msgDetalhes");
		
		try {
			bookBO.setBook(book);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}		
		
		book = new BookBean();
		
		return "insert-book";
	}
		
	public String searchBookController(){
		BookBO bookBO = new BookBO();
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgValidaProcura");
		String message = text.getString("msgDetalhes");
		
		
		try {
			book = bookBO.getBook(book);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}
		
		return "search-book";
	}

	public String searchListBookController(){
		BookBO bookBO = new BookBO();
		

		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgValidaProcura");
		String message = text.getString("msgDetalhes");
		
		
		try {
			listBook = bookBO.getListBooks(book);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}
		
		return "search-book";
	}
	
	public String searchListBookIndexController(){
		BookBO bookBO = new BookBO();
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgValidaProcura");
		String message = text.getString("msgDetalhes");
		
		try {
			setListBook3(null);
			setListBook3(bookBO.getListBooks(book));
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}
		
		return "show-bookB";
	}
	
	public String searchAdvancedController(){
		BookBO bookBO = new BookBO();
		try {
			setListBook3(null);
			setListBook3(bookBO.getListBooksAdvanced(book));
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao procurar", "Detalhes:  " + e));
		}
		
		return "show-bookB";
	}
	
	public String searchListBookDiscountController(){
		BookBO bookBO = new BookBO();
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgValidaProcura");
		String message = text.getString("msgDetalhes");
		
		try {
			setListBook2(null);
			setListBook2(bookBO.getListBooksDiscount(book));
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}
		
		return "show-books";
	}
	
	public String deleteBookController(){
		BookBO bookBO = new BookBO();
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgValidaProcura");
		String message = text.getString("msgDetalhes");
		
		
		try {
			bookBO.deleteBook(book);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}
		
		return searchListBookController();
	}
	
	public String fillEditBookController(){
		BookBO bookBO = new BookBO();
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgValidaProcura");
		String message = text.getString("msgDetalhes");
		
		
		try {
			book = bookBO.getBook(book);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+" " + e));
		}
		
		return "edit-book";
	}
	
	public String editBookController(){
		BookBO bookBO = new BookBO();
		

		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle text = ResourceBundle.getBundle("language", context.getViewRoot().getLocale());
		String v = text.getString("msgValidaProcura");
		String message = text.getString("msgDetalhes");
		
		try {
			bookBO.alterBook(book);
		} catch (SQLException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, v+ " " + e));
		}
		
		return "edit-book";
	}
}
