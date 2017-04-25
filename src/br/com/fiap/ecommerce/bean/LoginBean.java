package br.com.fiap.ecommerce.bean;

public class LoginBean {
	private int logiId;
	private int loginType;
	private int userId;
	private String user;
	private String password;
	
	public LoginBean() {}

	public LoginBean(int logiId, int loginType, int userId, String user, String password) {
		super();
		this.logiId = logiId;
		this.loginType = loginType;
		this.userId = userId;
		this.user = user;
		this.password = password;
	}

	public int getLogiId() {
		return logiId;
	}

	public void setLogiId(int logiId) {
		this.logiId = logiId;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
