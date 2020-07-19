/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [MstJapanLogic.java], [Mar 24, 2020] [Kiều Văn Quang]
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.entities.MstJapanEntity;

/**
 * Interface MstJapanLogic khởi tạo các logic với bảng MstJapan
 * @author Kiều Văn Quang
 *
 */
public interface MstJapanLogic {
	/**
	 * Hàm dùng để lấy về tất cả group
	 * 
	 * @return ArrayList<MstJapanEntity>	Trả về một list là các đối tượng MstJapan
	 * @throws ClassNotFoundException		Ném ra ngoại lệ ClassNotFoundException
	 * @throws SQLException					Ném ra ngoại lệ SQLException
	 */
	public ArrayList<MstJapanEntity> getAllMstJapan() throws ClassNotFoundException, SQLException;
}
