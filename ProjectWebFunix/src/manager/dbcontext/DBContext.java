/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [DBContext.java], [Jun 13, 2020] [Kiều Văn Quang]
 */
package manager.dbcontext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import manager.common.Constant;

/**
 * @author Kiều Văn Quang
 *
 */
public class DBContext {
	private String driver = Constant.DRIVER;
	private String url = Constant.URL;
	private String userName = Constant.USER;
	private String password = Constant.PASSWORD;
	protected Connection conn;

	public void getConnection() throws ClassNotFoundException, SQLException {
		try {
			// Khai báo class for JDBC
			Class.forName(driver);
			// Khởi tạo kết nối gán vào biến conn
			conn = DriverManager.getConnection(url, userName, password);
		} catch (SQLException | ClassNotFoundException ex) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + ex.getMessage());
			throw ex;
			// In ra màn hình console khi sinh ra ngoại lệ

		}
	}

	public void closeConnection() {
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
