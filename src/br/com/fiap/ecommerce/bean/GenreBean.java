package br.com.fiap.ecommerce.bean;

public class GenreBean {
	private int id;
	private String genre;
	
	public GenreBean() {}

	public GenreBean(int id, String genre) {
		super();
		this.id = id;
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
