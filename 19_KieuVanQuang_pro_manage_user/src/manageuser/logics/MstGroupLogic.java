/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [MstGroupLogic.java.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.entities.MstGroupEntity;

/**
 * Interface MstGroupLogic khởi tạo các logic với bảng MstGroup
 * @author Kiều Văn Quang
 *
 */
public interface MstGroupLogic {
	
	/**
	 * Hàm dùng để lấy về tất cả group
	 * 
	 * @return ArrayList<MstGroupEntity>	Trả về một list là các đối tượng MstGroupEntity
	 * @throws ClassNotFoundException		Ném ra ngoại lệ ClassNotFoundException
	 * @throws SQLException					Ném ra ngoại lệ SQLException
	 */
	public ArrayList<MstGroupEntity> getAllMstGroup() throws ClassNotFoundException, SQLException;
}
