package br.com.fiap.ecommerce.bean;

public class PublisherBean {
	private int id;
	private String publisher;
	private long cnpj;
	private String email;
	private int phoneNumber;
	private String country;
	private String state;
	private String street;
	private int zipCode;
	private int addressNumber;
	
	public PublisherBean() { }
		
	public PublisherBean(int id, String publisher, long cnpj, String email, int phoneNumber, String country,
			String state, String street, int zipCode, int addressNumber) {
		this.id = id;
		this.publisher = publisher;
		this.cnpj = cnpj;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.country = country;
		this.state = state;
		this.street = street;
		this.zipCode = zipCode;
		this.addressNumber = addressNumber;
	}

	public int getId() {
		return id;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public long getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getState() {
		return state;
	}
	
	public String getStreet() {
		return street;
	}
	
	public int getZipCode() {
		return zipCode;
	}
	
	public int getAddressNumber() {
		return addressNumber;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public void setAddressNumber(int addressNumber) {
		this.addressNumber = addressNumber;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
