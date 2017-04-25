package br.com.fiap.ecommerce.bean;

public class BookBean {
	private int bookID;
	private String name;
	private double price;
	private int autorID;
	private String authorName;
	private int genreID;
	private String genreName;
	private int publisherID;
	private String publisherName;
	private int ISBN;
	private String synopsis;
		
	public BookBean(int bookID, String name, double price, int autorID, 
			int genreID, int publisherID, int iSBN,	String synopsis) {
		this.bookID = bookID;
		this.name = name;
		this.price = price;
		this.autorID = autorID;
		this.genreID = genreID;
		this.publisherID = publisherID;
		ISBN = iSBN;
		this.synopsis = synopsis;
	}

	public BookBean(int bookID, String name, double price, String authorName, String genreName, String publisherName,
			int iSBN, String synopsis) {
		this.bookID = bookID;
		this.name = name;
		this.price = price;
		this.authorName = authorName;
		this.genreName = genreName;
		this.publisherName = publisherName;
		ISBN = iSBN;
		this.synopsis = synopsis;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public int getBookID() {
		return bookID;
	}
	
	public void setBookID(int bookID) {
		this.bookID = bookID;
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
	
	public int getAutorID() {
		return autorID;
	}
	
	public void setAutorID(int autorID) {
		this.autorID = autorID;
	}
	
	public int getGenreID() {
		return genreID;
	}
	
	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}
	
	public int getPublisherID() {
		return publisherID;
	}
	
	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}
	
	public int getISBN() {
		return ISBN;
	}
	
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}	
}
