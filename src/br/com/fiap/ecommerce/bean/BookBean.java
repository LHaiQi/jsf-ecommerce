package br.com.fiap.ecommerce.bean;

public class BookBean {
	private int bookID;
	private int ISBN;
	private String name;
	private String synopsis;
	private double price;
	private AuthorBean author;
	private PublisherBean publisher;
	private GenreBean genre;
	
	public BookBean () { }
	
	public BookBean(int bookID, int iSBN, String name, String synopsis, double price, AuthorBean author,
			PublisherBean publisher, GenreBean genre) {
		this.bookID = bookID;
		ISBN = iSBN;
		this.name = name;
		this.synopsis = synopsis;
		this.price = price;
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
	}

	public int getBookID() {
		return bookID;
	}
	
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	
	public int getISBN() {
		return ISBN;
	}
	
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
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

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}	
}
