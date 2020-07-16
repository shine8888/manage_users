/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [Account.java], [Jul 15, 2020] [Kiều Văn Quang]
 */
package manager.model;

/**
 * @author Kiều Văn Quang
 *
 */
public class Account {
	private String accountName;
	private String email;
	private String address;
	private String phone;
	private String password;
	private int role;

	public Account(String accountName, String email, String address, String phone, String password, int role) {
		this.accountName = accountName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.password = password;
		this.role = role;
	}

	public Account() {
	}
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	
}
