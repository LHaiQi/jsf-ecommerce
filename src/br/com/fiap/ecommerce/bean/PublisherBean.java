package br.com.fiap.ecommerce.bean;

public class PublisherBean {
	private int publisherID;
	private String name;
	
	public PublisherBean(int publisherID, String name) {
		this.publisherID = publisherID;
		this.name = name;
	}

	public int getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
