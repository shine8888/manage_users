/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [TblDetailUserJapanDao.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.dao;

import java.sql.SQLException;

import manageuser.entities.TblDetailUserJapanEntity;

/**
 * Interface TblDetailUserJapanDao khởi tạo các phương thức dùng để xử lý với database
 * 
 * @author Kiều Văn Quang
 *
 */
public interface TblDetailUserJapanDao extends BaseDao {
	/**
	 * Hàm lấy ra trình độ tiếng Nhật bằng id của user
	 * 
	 * @param id 							Truyền vào id của người dùng
	 * @return TblDetailUserJapanEntity 	Trả về một đối tượng TblDetailUserJapanEntity
	 * @throws ClassNotFoundException 		Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException          		Ném ra ngoại lệ SQL
	 */
	public TblDetailUserJapanEntity getTblDetailUserJapanById(int id) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm thêm mới trình độ tiếng Nhật cho user
	 * 
	 * @param user 						Truyền vào đối tượng TblDetailUserJapanEntity
	 * @return boolean				 	Trả về kết quả true/false
	 * @throws ClassNotFoundException 	Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException           	Ném ra ngoại lệ SQL
	 */
	public boolean insertDetailUserJapan(TblDetailUserJapanEntity user) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm cập nhật trình độ tiếng Nhật cho user
	 * 
	 * @param user 						Truyền vào đối tượng TblDetailUserJapanEntity
	 * @return boolean				 	Trả về kết quả true/false
	 * @throws ClassNotFoundException 	Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException           	Ném ra ngoại lệ SQL
	 */
	public boolean updateTblDetailUserJapan(TblDetailUserJapanEntity user) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm xóa trình độ tiếng Nhật cho user
	 * 
	 * @param id 						Truyền vào id của người dùng
	 * @return boolean				 	Trả về kết quả true/false
	 * @throws ClassNotFoundException 	Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException           	Ném ra ngoại lệ SQL
	 */
	public boolean deleteTblDetailUserJapan(int id) throws ClassNotFoundException, SQLException;

}
