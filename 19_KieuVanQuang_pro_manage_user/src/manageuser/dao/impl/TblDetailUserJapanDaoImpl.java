/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [TblDetailUserJapanDaoImpl.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.entities.TblDetailUserJapanEntity;

/**
 * Class TblDetailUserJapanDaoImpl
 * 
 * @author Kiều Văn Quang
 *
 */
public class TblDetailUserJapanDaoImpl extends BaseDaoImpl implements TblDetailUserJapanDao {

	/**
	 * @see manageuser.dao.TblDetailUserJapanDao#insertDetailUserJapan(manageuser.entities.TblDetailUserJapanEntity)
	 * 
	 */
	@Override
	public boolean insertDetailUserJapan(TblDetailUserJapanEntity user) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến check
		boolean check = false;
		// Mở try
		try {
			// Kiểm tra xem conn có tồn tại hay không
			if (conn != null) {
				// Khởi tạo câu truy vấn
				StringBuilder sql = new StringBuilder();
				// Gán thêm giá trị cho câu truy vấn
				sql.append("Insert IGNORE Into tbl_detail_user_japan(user_id,code_level,start_date, end_date,total)");
				sql.append(" values(?,?,?,?,?);");
				// Khởi tạo câu lệnh preparedStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// Khởi tạo index
				int index = 1;
				// Truyền các giá trị vào câu preparedStatement để thực hiện câu truy vấn
				pre.setInt(index++, user.getUserId());
				pre.setString(index++, user.getCodeLevel());
				pre.setDate(index++, user.getStartDate());
				pre.setDate(index++, user.getEndDate());
				pre.setInt(index++, user.getTotal());
				pre.executeUpdate();
			}
			check = true;
			// Mở bắt lỗi
		} catch (SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		}
		return check;
	}

	/**
	 * @see manageuser.dao.TblDetailUserJapanDao#getTblDetailUserJapanById(int)
	 * 
	 */
	@SuppressWarnings("finally")
	@Override
	public TblDetailUserJapanEntity getTblDetailUserJapanById(int id) throws ClassNotFoundException, SQLException {
		TblDetailUserJapanEntity user = null;
		// Mở try
		try {
			// Mở kết nối
			open();
			// Kiểm tra xem conn có tồn tại hay không
			if (conn != null) {
				user = new TblDetailUserJapanEntity();
				// Khởi tạo câu truy vấn
				StringBuilder sql = new StringBuilder();
				// Gán thêm giá trị cho câu truy vấn
				sql.append(
						"Select td.detail_user_japan_id, td.user_id, td.code_level, td.start_date, td.end_date, td.total ");
				sql.append("From Tbl_Detail_User_Japan td Where td.user_id = ?;");
				// Khởi tạo câu lệnh preparedStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// Truyền các giá trị vào câu preparedStatement để thực hiện câu truy vấn
				int index = 1;
				pre.setInt(index++, id);
				// Khởi tạo hàm ResultSet để lấy về giá trị khi query
				ResultSet rs = pre.executeQuery();
				// Dùng câu lệnh while để gán giá trị cho các thuộc tính của user
				while (rs.next()) {
					user.setDetailUserJapanId(rs.getInt("detail_user_japan_id"));
					user.setUserId(rs.getInt("user_id"));
					user.setCodeLevel(rs.getString("code_level"));
					user.setStartDate(rs.getDate("start_date"));
					user.setEndDate(rs.getDate("end_date"));
					user.setTotal(rs.getInt("total"));
				}
			}
			// Mở bắt lỗi
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối
			close();
			// Trả về đối tượng cho phương thức
			return user;
		}

	}

	/**
	 * @see manageuser.dao.TblDetailUserJapanDao#updateTblDetailUserJapan(manageuser.entities.TblDetailUserJapanEntity)
	 * 
	 */
	@Override
	public boolean updateTblDetailUserJapan(TblDetailUserJapanEntity user) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến check
		boolean check = false;
		// Mở try
		try {
			// Kiểm tra xem conn có tồn tại hay không
			if (conn != null) {
				// Khởi tạo câu truy vấn
				StringBuilder sql = new StringBuilder();
				// Gán thêm giá trị cho câu truy vấn
				sql.append("Update Tbl_detail_user_japan Set ");
				sql.append("code_level = ? ,start_date = ? , end_date = ? ,total = ? ");
				sql.append("Where user_id = ?;");
				// Khởi tạo câu lệnh preparedStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// Khởi tạo index
				int index = 1;
				// Truyền các giá trị vào câu preparedStatement để thực hiện câu truy vấn
				pre.setString(index++, user.getCodeLevel());
				pre.setDate(index++, user.getStartDate());
				pre.setDate(index++, user.getEndDate());
				pre.setInt(index++, user.getTotal());
				pre.setInt(index++, user.getUserId());
				pre.executeUpdate();
			}
			check = true;
			// Mở bắt lỗi
		} catch (SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		}
		return check;
	}

	/**
	 * @see manageuser.dao.TblDetailUserJapanDao#deleteTblDetailUserJapan(int)
	 * 
	 */
	@Override
	public boolean deleteTblDetailUserJapan(int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến check
		boolean check = false;
		// Mở try
		try {
			// Kiểm tra xem conn có tồn tại hay không
			if (conn != null) {
				// Khởi tạo câu truy vấn
				StringBuilder sql = new StringBuilder();
				// Gán thêm giá trị cho câu truy vấn
				sql.append("Delete From Tbl_detail_user_japan Where user_id = ?;");
				// Khởi tạo câu lệnh preparedStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// Khởi tạo index
				int index = 1;
				// Truyền các giá trị vào câu preparedStatement để thực hiện câu truy vấn
				pre.setInt(index++, id);
				pre.executeUpdate();
			}
			check = true;
			// Mở bắt lỗi
		} catch (SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		}
		return check;
	}

}
