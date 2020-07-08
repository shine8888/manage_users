/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [BaseDAO.java], [Jul 9, 2020] [Kiều Văn Quang]
 */
package manager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import manager.common.Constant;

/**
 * @author Kiều Văn Quang
 *
 */
public class BaseDAO {
	// Khai báo biến Connection conn
	protected Connection conn;

	protected void openConnection() throws Exception {
		try {
			// Khai báo class for JDBC
			Class.forName(Constant.DRIVER);
			// Khởi tạo kết nối gán vào biến conn
			conn = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
			// In ra màn hình console khi sinh ra ngoại lệ

		}
	}

	protected void closeConnection() {
		try {
			// Kiểm tra xem connection đã được đóng hay chưa
			if (!conn.isClosed()) {
				// Đóng connection
				conn.close();
			}
			// Bắt ngoại lệ
		} catch (SQLException e) {
			// In ra màn hình console khi sinh ra ngoại lệ
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
		}
	}

}
