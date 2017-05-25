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
	private String bookImage;
	private int discount;
	private int quantity;
	private double discountprice;
	
	public BookBean () { 
		author = new AuthorBean();
		publisher = new PublisherBean();
		genre = new GenreBean();
	}
	
	public BookBean(int bookID, String name, double price, String bookImage, int discount, int quantity,double discountprice) {
		this.bookID = bookID;
		this.name = name;
		this.price = price;
		this.bookImage = bookImage;
		this.discount = discount;
		this.quantity = quantity;
		this.discountprice = discountprice;
	}
	
	public BookBean(int bookID, int iSBN, String name, String synopsis, double price, AuthorBean author,
			PublisherBean publisher, GenreBean genre,String bookImage,int discount,int quantity, double discountprice) {
		this.bookID = bookID;
		ISBN = iSBN;
		this.name = name;
		this.synopsis = synopsis;
		this.price = price;
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
		this.bookImage = bookImage;
		this.discount = discount;
		this.quantity = quantity;
		this.discountprice = discountprice;
	}
	
	public double getDiscountprice() {
		return discountprice;
	}

	public void setDiscountprice(double discountprice) {
		this.discountprice = discountprice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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
