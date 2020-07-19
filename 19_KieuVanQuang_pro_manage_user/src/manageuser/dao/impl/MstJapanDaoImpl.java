/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [MstJapanDaoImpl.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.dao.MstJapanDao;
import manageuser.entities.MstJapanEntity;

/**
 * Class MstJapanDaoImpl
 * 
 * @author Kiều Văn Quang
 *
 */
public class MstJapanDaoImpl extends BaseDaoImpl implements MstJapanDao {

	/**
	 * @see manageuser.dao.MstJapanDao#getAllMstJapan()
	 * 
	 */
	@Override
	public ArrayList<MstJapanEntity> getAllMstJapan() throws ClassNotFoundException, SQLException {
		// Khởi tạo list MstJapanEntity
		ArrayList<MstJapanEntity> listMstJapan = new ArrayList<MstJapanEntity>();
		try {
			// Mở kết nối
			open();
			// Khởi tạo câu truy vấn sql
			StringBuilder sql = new StringBuilder();
			// Thêm câu truy vấn
			sql.append("Select code_level, name_level ");
			sql.append("From Mst_Japan;");
			// Khởi tạo câu lệnh PreparedStatement
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				// Khởi tạo đối tượng mst japan
				MstJapanEntity mstJapan = new MstJapanEntity();
				int i = 1;
				// Lấy các giá trị cho đối tượng mstJapan
				String codeLevel = rs.getString(i++);
				String nameLevel = rs.getString(i++);
				// Set các giá trị lấy được vào trong đối tượng mstJapan
				mstJapan.setCodeLevel(codeLevel);
				mstJapan.setNameLevel(nameLevel);
				// Thêm đối tượng mstJapan vào trong listMstJapan
				listMstJapan.add(mstJapan);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		} finally {
			close();
		}
		// trả về listMstJapan
		return listMstJapan;
	}

}
