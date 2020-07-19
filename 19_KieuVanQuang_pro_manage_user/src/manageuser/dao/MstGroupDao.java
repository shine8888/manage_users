/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [MstGroupDao.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.entities.MstGroupEntity;
/**
 * Interface khởi tạo các phương thức dùng để xử lý với database
 * @author Kiều Văn Quang
 *
 */
public interface MstGroupDao {
	/**
	 * Hàm dùng để lấy ra tất cả các nhóm
	 * @return ArrayList<MstGroupEntity>	Trả về một list đối tượng MstGroupEntity	
	 * @throws ClassNotFoundException		Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException					Ném ra ngoại lệ SQL
	 */
	public ArrayList<MstGroupEntity> getAllMstGroup() throws ClassNotFoundException, SQLException;
}
