package br.com.fiap.ecommerce.bean;

public class LoginBean {
	private int loginId;
	private int loginType;
	private int userId;
	private String user;
	private String password;
	
	public LoginBean() {}

	public LoginBean(int loginId, int loginType, int userId, String user, String password) {
		super();
		this.loginId = loginId;
		this.loginType = loginType;
		this.userId = userId;
		this.user = user;
		this.password = password;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int logiId) {
		this.loginId = logiId;
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
