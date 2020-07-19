/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [BaseDao.java], [Mar 31, 2020] [Kiều Văn Quang]
 */
package manageuser.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class BaseDao thực hiện khai báo các thao tác với database
 * @author Kiều Văn Quang
 *
 */
public interface BaseDao {
	/**
	 * Hàm mở kết nối Connection
	 * 
	 * @throws Ném ra ngoại lệ là ClassNotFoundException
	 * @throws Ném ra ngoại lệ là SQLException
	 */
	public  void open() throws ClassNotFoundException, SQLException;
	
	/**
	 * Hàm đóng kết nối
	 */
	public void close();
	/**
	 * Hàm dùng để chặn autocommit
	 */
	public void disableAutoCommit() ;
	/**
	 * Hàm dùng để commit
	 */
	public void setCommit();
	/**
	 * Hàm rollback khi xảy ra lỗi
	 */
	public void setRollBack();
	/**
	 * Hàm lấy về một Connection
	 * @return Trả về một Connection
	 */
	public Connection getConn();
	/**
	 * Hàm set Connection
	 * @param conn
	 */
	public void setConn(Connection conn);
}
