package br.com.fiap.ecommerce.bean;

import java.util.Date;

public class UserBean {
	private int id;
	private String name;
	private long cpf;
	private String lastname;
	private String email;
	private String gender;
	private Date birthdate;
	private int phonenumber;
	private int zipcode;
	private String city;
	private String state;
	private String street;
	private int housenumber;
	private LoginBean login;

	public UserBean() {
		login = new LoginBean();
	}

	public UserBean(int id, String name, long cpf, String lastname, String email, String gender, Date birthdate,
			int phonenumber, int zipcode, String city, String state, String street, int housenumber) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.lastname = lastname;
		this.email = email;
		this.gender = gender;
		this.birthdate = birthdate;
		this.phonenumber = phonenumber;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
		this.street = street;
		this.housenumber = housenumber;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(int housenumber) {
		this.housenumber = housenumber;
	}
}
