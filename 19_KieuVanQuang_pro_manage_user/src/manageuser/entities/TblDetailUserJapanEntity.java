/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [TblDetailUserJapanEntity.java], [Mar 17, 2020] [Kiều Văn Quang]
 */
package manageuser.entities;

import java.io.Serializable;
import java.sql.Date;
/**
 * Class TblDetailUserJapanEntity để tạo đối tượng TblDetailUserJapan
 * @author Kiều Văn Quang
 *
 */
public class TblDetailUserJapanEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	// Khởi tạo các thuộc tính cho đối tượng TblDetailUserJapan
	private int detailUserJapanId;
	private int userId;
	private String codeLevel;
	private Date startDate, endDate;
	private int total;
	
	// Khởi tạo Constructor 
	public TblDetailUserJapanEntity() {
		
	}
	
	// Khởi tạo các hàm getter, setter
	public int getDetailUserJapanId() {
		return detailUserJapanId;
	}
	public void setDetailUserJapanId(int detailUserJapanId) {
		this.detailUserJapanId = detailUserJapanId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCodeLevel() {
		return codeLevel;
	}
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
	
	
}
