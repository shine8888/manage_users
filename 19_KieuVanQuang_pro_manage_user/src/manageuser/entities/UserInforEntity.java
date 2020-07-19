/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [UserInforEntity.java], [Mar 17, 2020] [Kiều Văn Quang]
 */
package manageuser.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
/**
 * Class UserInforEntity để tạo đối tượng UserInfor
 * @author Kiều Văn Quang
 *
 */
public class UserInforEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userId;
	private String fullName;
	private String email;
	private String tel;
	private Date birthday;
	private String groupName;
	private int groupId;
	private String nameLevel;
	private Date endDate;
	private int total;
	private int rule;
	private String password;
	private String salt;
	private String fullNameKana;
	private String loginName;
	private Date startDate;
	private String codeLevel;
	private List<String> listTime;
	private String confirmPassword;
	private List<Integer> listBirthday;
	private List<Integer> listStartDate;
	private List<Integer> listEndDate;
	private String score;
	private int detailUserJapanId;
	
	// Khởi tạo Constructor 
	public UserInforEntity() {
	}

	// Khởi tạo các phương thức getter, setter
	public int getDetailUserJapanId() {
		return detailUserJapanId;
	}

	public void setDetailUserJapanId(int detailUserJapanId) {
		this.detailUserJapanId = detailUserJapanId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public List<Integer> getListBirthday() {
		return listBirthday;
	}

	public void setListBirthday(List<Integer> listBirthday) {
		this.listBirthday = listBirthday;
	}

	public List<Integer> getListStartDate() {
		return listStartDate;
	}

	public void setListStartDate(List<Integer> listStartDate) {
		this.listStartDate = listStartDate;
	}

	public List<Integer> getListEndDate() {
		return listEndDate;
	}

	public void setListEndDate(List<Integer> listEndDate) {
		this.listEndDate = listEndDate;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public List<String> getListTime() {
		return listTime;
	}

	public void setListTime(List<String> listTime) {
		this.listTime = listTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getNameLevel() {
		return nameLevel;
	}

	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getFullNameKana() {
		return fullNameKana;
	}

	public void setFullNameKana(String fullNameKana) {
		this.fullNameKana = fullNameKana;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date beginDate) {
		this.startDate = beginDate;
	}

	public String getCodeLevel() {
		return codeLevel;
	}

	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
