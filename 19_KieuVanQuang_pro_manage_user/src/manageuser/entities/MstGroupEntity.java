/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [MstGroupEntity.java], [Mar 31, 2020] [Kiều Văn Quang]
 */
package manageuser.entities;

import java.io.Serializable;
/**
 * Class MstGroupEntity để tạo đối tượng MstGroup
 * @author Kiều Văn Quang
 *
 */
public class MstGroupEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	// Khai báo thuộc tính groupId
	private int groupId;
	// Khai báo thuộc tính groupName
	private String groupName;
	
	// Khởi tạo Constructor
	public MstGroupEntity() {
	}
	
	// Hàm lấy group id
	public int getGroupId() {
		return groupId;
	}
	// Hàm set group id
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	// Hàm lấy group name
	public String getGroupName() {
		return groupName;
	}
	// Hàm set group name
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
