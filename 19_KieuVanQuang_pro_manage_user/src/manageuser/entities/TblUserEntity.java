/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [TblUserJapanEntity.java], [Mar 17, 2020] [Kiều Văn Quang]
 */
package manageuser.entities;

import java.io.Serializable;
import java.sql.Date;

/**
 * Class TblUserEntity để tạo đối tượng TblUser
 * @author Kiều Văn Quang
 *
 */
public class TblUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Khai báo các thuộc tính của đối tượng
	private int userId, groupId, rule;
	private String loginName, password, fullName, fullNameKana, email, tel, salt;
	private Date birthday;

	// Khởi tạo Constructor
	public TblUserEntity() {
	}
	
	
	// Khởi tạo các hàm getter, setter
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullNameKana() {
		return fullNameKana;
	}

	public void setFullNameKana(String fullNameKana) {
		this.fullNameKana = fullNameKana;
	}

	public int getRule() {
		return rule;
	}

	public void setRule(int rule) {
		this.rule = rule;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
