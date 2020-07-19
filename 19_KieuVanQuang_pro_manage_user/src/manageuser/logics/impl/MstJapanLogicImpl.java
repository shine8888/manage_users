/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [MstJapanLogicImpl.java], [Mar 24, 2020] [Kiều Văn Quang]
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.dao.MstJapanDao;
import manageuser.dao.impl.MstJapanDaoImpl;
import manageuser.entities.MstJapanEntity;
import manageuser.logics.MstJapanLogic;

/**
 * @author muath
 *
 */
public class MstJapanLogicImpl implements MstJapanLogic {

	/**
	 * @see manageuser.logics.MstJapanLogic#getAllMstJapan()
	 * 
	 */
	@Override
	public ArrayList<MstJapanEntity> getAllMstJapan() throws ClassNotFoundException, SQLException {
		// Khởi tạo đối tượng mstJapan
		MstJapanDao mstJapan = new MstJapanDaoImpl();
		// Khởi tạo và gán giá trị cho listAllMstJapan
		ArrayList<MstJapanEntity> listAllMstJapan = mstJapan.getAllMstJapan();
		// Trả về listAllMstJapan
		return listAllMstJapan;
	}
}
