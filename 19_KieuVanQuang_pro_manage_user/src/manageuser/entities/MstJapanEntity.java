/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [MstJapanEntity.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.entities;

import java.io.Serializable;
/**
 * Class MstJapanEntity để tạo đối tượng MstJapan
 * @author Kiều Văn Quang
 *
 */
public class MstJapanEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	// Khai báo thuộc tính code level
	private String codeLevel;
	// Khai báo thuộc tính name level
	private String nameLevel;
	
	// Khởi tạo Constructor không tham số
	public MstJapanEntity() {
	}
	
	// Các phương thức Getter và Setter
	public String getCodeLevel() {
		return codeLevel;
	}
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}
	public String getNameLevel() {
		return nameLevel;
	}
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}
}
