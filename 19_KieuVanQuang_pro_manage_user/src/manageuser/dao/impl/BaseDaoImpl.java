/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [BaseDaoImpl.java], [Mar 31, 2020] [Kiều Văn Quang]
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import manageuser.dao.BaseDao;
import manageuser.utils.DatabaseProperties;

/**
 * Class BaseDaoImpl kế thừa từ BaseDao thực hiện các thao tác với cơ sở dữ liệu
 * 
 * @author Kiều Văn Quang
 *
 */
public class BaseDaoImpl implements BaseDao {
	// Khai báo biến Connection conn
	protected Connection conn;
	// Khởi tạo String url lấy giá trị từ file Database.Properties
	private String url = DatabaseProperties.getValueByKey("url");
	// Khởi tạo String userName lấy giá trị từ file Database.Properties
	private String userName = DatabaseProperties.getValueByKey("user");
	// Khởi tạo String password lấy giá trị từ file Database.Properties
	private String password = DatabaseProperties.getValueByKey("password");
	// Khởi tạo String driver lấy giá trị từ file Database.Properties
	private String driver = DatabaseProperties.getValueByKey("driver");

	/**
	 * Hàm mở kết nối tới Database
	 * 
	 * @return Connection 				Trả về một Connection
	 * @throws ClassNotFoundException 	Ném ra ngoại lệ
	 * @throws SQLException           	Ném ra ngoại lệ
	 */
	@Override
	public void open() throws ClassNotFoundException, SQLException {
		try {
			// Khai báo class for JDBC
			Class.forName(driver);
			// Khởi tạo kết nối gán vào biến conn
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println(conn);
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

	/**
	 * Phương thức đóng kết nối Connection
	 */
	@Override
	public void close() {
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

	/**
	 * Phương thức chặn autocomit
	 */
	@Override
	public void disableAutoCommit() {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
		}

	}

	/**
	 * Phương thức set commit
	 */
	@Override
	public void setCommit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());

		}

	}

	/**
	 * Phương thức rollback khi xảy ra lỗi
	 */
	@Override
	public void setRollBack() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
		}

	}

	/**
	 * Hàm lấy về một Connection
	 * 
	 * @return Trả về một Connection
	 */
	@Override
	public Connection getConn() {
		return conn;
	}

	/**
	 * Hàm set Connection
	 * 
	 * @param conn
	 */
	@Override
	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
