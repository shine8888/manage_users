/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [MstGroupDaoImpl.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import manageuser.dao.MstGroupDao;
import manageuser.entities.MstGroupEntity;

/**
 * MstGroupDaoImpl kế thừa từ MstGroupDao
 * @author Kiều Văn Quang
 *
 */
public class MstGroupDaoImpl extends BaseDaoImpl implements MstGroupDao {
	private MstGroupEntity mstGroup;

	/**
	 * @see manageuser.dao.MstGroupDao#getAllMstGroup()
	 * 
	 */
	@Override
	public ArrayList<MstGroupEntity> getAllMstGroup() throws ClassNotFoundException, SQLException {
		// Khởi tạo listMstGroup
		ArrayList<MstGroupEntity> listMstGroup = new ArrayList<MstGroupEntity>();
		// Khởi tạo StringBuilder sql
		try {
			// Mở kết nối
			open();
			// Thêm các dòng lệnh SQL
			StringBuilder sql = new StringBuilder();
			sql.append("Select group_id, group_name ");
			sql.append("From Mst_Group;");
			String sql1 = sql.toString();
			// Khởi tạo câu lệnh PreparedStatement
			PreparedStatement pre = conn.prepareStatement(sql1);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				mstGroup = new MstGroupEntity();
				int i = 1;
				int groupId = rs.getInt(i++);
				String groupName = rs.getString(i++);
				mstGroup.setGroupId(groupId);
				mstGroup.setGroupName(groupName);
				listMstGroup.add(mstGroup);
			}
		} catch (ClassNotFoundException| SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối
			close();
		}
		// Trả về giá trị cho listMstGroup
		return listMstGroup;
	}



	

}
