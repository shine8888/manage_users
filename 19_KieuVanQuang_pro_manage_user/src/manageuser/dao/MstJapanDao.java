/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [MstJapanDao.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.entities.MstJapanEntity;

/**
 * Interface MstJapanDao khởi tạo các phương thức dùng để xử lý với database
 * @author Kiều Văn Quang
 *
 */
public interface MstJapanDao {
	/**
	 * Hàm lấy ra tất cả thông tin từ bảng MstJapan
	 * @return ArrayList<MstJapanEntity>	Trả về một list đối tượng MstJapanEntity	
	 * @throws ClassNotFoundException		Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException					Ném ra ngoại lệ SQL
	 */
	public ArrayList<MstJapanEntity> getAllMstJapan() throws ClassNotFoundException, SQLException;
}
