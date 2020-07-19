/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [TblDetailUserJapanLogicImpl.java], [Apr 7, 2020] [Kiều Văn Quang]
 */
package manageuser.logics.impl;

import java.sql.SQLException;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import manageuser.entities.TblDetailUserJapanEntity;
import manageuser.logics.TblDetailUserJapanLogic;

/**
 * @author Kiều Văn Quang
 *
 */
public class TblDetailUserJapanLogicImpl implements TblDetailUserJapanLogic {

	/**
	 * @see manageuser.logics.TblDetailUserJapanLogic#checkTblDetailUserJapan(manageuser.entities.TblDetailUserJapanEntity,
	 *      manageuser.entities.TblDetailUserJapanEntity)
	 * 
	 */
	@Override
	public int checkTblDetailUserJapan(TblDetailUserJapanEntity inputUser, TblDetailUserJapanEntity dbUser) {
		// Khởi tạo biến kiểm tra
		int check = 0;
		// Kiểm tra các điều kiện cho trường hợp insert
		if (dbUser.getCodeLevel() == null && inputUser.getCodeLevel() != null) {
			return check = 1;
		}

		// Kiểm tra các điều kiện cho trường hợp update
		if (dbUser.getCodeLevel() != null && inputUser.getCodeLevel() != null) {
			return check = 2;
		}

		// Kiểm tra các điều kiện cho trường hợp delete
		if (dbUser.getCodeLevel() != null && inputUser.getCodeLevel() == null) {
			return check = 3;
		}

		// Kiểm tra các điều kiện cho trường hợp không thay đổi gì
		if (dbUser.getCodeLevel() == null && inputUser.getCodeLevel() == null) {
			return check = 4;
		}
		// Trả về giá trị cho phương thức
		return check;
	}

	/**
	 * @see manageuser.logics.TblDetailUserJapanLogic#checkExistTblDetailUserJapan(int)
	 * 
	 */
	@Override
	public boolean checkExistTblDetailUserJapan(int id) {
		// Mở try
		try {
			// Khởi tạo các đối tượng TblDetailUserJapanDao, TblDetailUserJapanEntity
			TblDetailUserJapanDao td = new TblDetailUserJapanDaoImpl();
			TblDetailUserJapanEntity user = td.getTblDetailUserJapanById(id);
			// Kiểm tra điều kiện TblDetailUserJapanEntity
			if (user != null) {
				// Trả về true nếu thỏa mãn điều kiện
				return true;
			}
			// Bắt lỗi
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
		}
		return false;
	}

}
