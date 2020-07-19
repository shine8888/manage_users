/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [MstGroupImpl.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.dao.impl.MstGroupDaoImpl;
import manageuser.entities.MstGroupEntity;
import manageuser.logics.MstGroupLogic;

/**
 * Class MstGroupLogicImpl xử lý logic với bảng MstGroup
 * @author Kiều Văn Quang
 *
 */
public class MstGroupLogicImpl implements MstGroupLogic {
	// Khai báo thuộc tính MstGroupDaoImpl
	private MstGroupDaoImpl daoImpl;
	
	// Khởi tạo Constructor và đối tượng MstGroupDaoImpl
	public MstGroupLogicImpl() {
		daoImpl = new MstGroupDaoImpl();
	}

	/**
	 * @see manageuser.logics.MstGroupLogic#getAllMstGroup()
	 * 
	 */
	@Override
	public ArrayList<MstGroupEntity> getAllMstGroup() throws ClassNotFoundException, SQLException {
		// Khởi tạo list đối tượng MstGroupEntity
		ArrayList<MstGroupEntity> listMstGroup = new ArrayList<MstGroupEntity>();
		// Gán giá trị cho listMstGroup
		listMstGroup = daoImpl.getAllMstGroup();
		// Trả về list đối tượng cho phương thức
		return listMstGroup;
	}

	
}
