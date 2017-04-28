package br.com.fiap.ecommerce.bean;

public class BookBean {
	private int bookID;
	private int ISBN;
	private String name;
	private String synopsis;
	private double price;
	private int authorID;
	private int publisherID;
	private int genreID;
	
	public BookBean(int bookID, int iSBN, String name, String synopsis, double price, int authorID, int publisherID, int genreID) {
		this.bookID = bookID;
		ISBN = iSBN;
		this.name = name;
		this.synopsis = synopsis;
		this.price = price;
		this.authorID = authorID;
		this.publisherID = publisherID;
		this.genreID = genreID;
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

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public int getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}

	public int getGenreID() {
		return genreID;
	}

	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}	
}
