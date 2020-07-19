/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [TblUserLogicImpl.java], [Mar 25, 2020] [Kiều Văn Quang]
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.dao.TblUserDao;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblDetailUserJapanEntity;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInforEntity;
import manageuser.logics.TblDetailUserJapanLogic;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * 
 * @author Kiều Văn Quang
 *
 */
public class TblUserLogicImpl implements TblUserLogic {
	// Khai báo đối tượng TblUserEntity
	private TblUserDaoImpl dao;
	private Connection conn;

	// Khởi tạo constructor
	public TblUserLogicImpl() {
		dao = new TblUserDaoImpl();
	}

	/**
	 * @see manageuser.logics.TblUserLogic#existLoginId(java.lang.String,
	 *      java.lang.String)
	 * 
	 */
	@Override
	public boolean checkExistLoginId(String userName, String password) {
		// Khởi tạo đối tượng TblUserEntity
		TblUserEntity user = null;
		// Tạo biến kiểm tra check
		boolean check = false;
		// Mở try
		try {
			// Gán giá trị cho user
			user = dao.getTblUserByUserName(userName);
			// Kiểm tra nếu user có giá trị null
			if (user == null) {
				return check;
			}
			// Lấy về giá trị password
			String pass = user.getPassword();
			String salt = user.getSalt();
			// Mã hóa mật khẩu
			String passswordEncrypt = Common.encryptPassword(password, salt);
			// So sánh mật khẩu
			if (Common.compareString(pass, passswordEncrypt)) {
				check = true;
			}
			// Bắt lỗi
		} catch (Exception e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
		}
		return check;
	}

	/**
	 * @see manageuser.logics.TblUserLogic#getListUsers(int, int, int,
	 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 * 
	 */
	@Override
	public ArrayList<UserInforEntity> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException {
		// Khởi tạo list UserInforEntity
		ArrayList<UserInforEntity> listUser = new ArrayList<UserInforEntity>();
		// Khởi tạo đối tượng TblUserDaoImpl
		TblUserDao userDaoImpl = new TblUserDaoImpl();
		// Thay thế các toán tử wildcard
		fullName = Common.replaceWildCard(fullName);
		// Khởi tạo listAllColumn
		List<String> listALlColumn = userDaoImpl.getListColumn();
		if (listALlColumn.contains(Constant.COLUMN_FULL_NAME) && listALlColumn.contains(Constant.COLUMN_END_DATE)
				&& listALlColumn.contains(Constant.COLUMN_LEVEL)) {
			// Gán giá trị cho listUser
			System.out.println(sortByCodeLevel);
			listUser = userDaoImpl.getListUsers(offset, limit, groupId, fullName, sortType, sortByFullName,
					sortByCodeLevel, sortByEndDate);
			// Nếu sort trường không tồn tại trong DB thì sort theo mặc định
		} else {
			listUser = userDaoImpl.getListUsers(offset, limit, groupId, fullName, Constant.DEFAULT_SORT_TYPE,
					Constant.SORT_NAME_DEFAULT, Constant.SORT_LEVEL_DEFAULT, Constant.SORT_ENDDATE_DEFAULT);
		}

		// Trả về giá trị cho phương thức
		return listUser;
	}

	/**
	 * Hàm lấy ra tổng số bản ghi
	 * 
	 * @param listUser
	 * @return
	 */
	public int getTotalRecords(ArrayList<UserInforEntity> listUser) {
		// Gán giá trị cho biến count
		int count = listUser.size();
		// Trả về giá trị cho phương thức
		return count;
	}

	/**
	 * @see manageuser.logics.TblUserLogic#getTotalUser(int, java.lang.String)
	 * 
	 */
	@Override
	public int getTotalUser(int groupId, String fullName) throws ClassNotFoundException, SQLException {
		fullName = Common.replaceWildCard(fullName);
		// Trả về giá trị
		return dao.getTotalUser(groupId, fullName);
	}

	/**
	 * @see manageuser.logics.TblUserLogic#checkExistLoginName(java.lang.String)
	 * 
	 */
	@Override
	public boolean checkExistLoginName(String loginName, int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến check
		boolean check = false;
		// Khởi tạo đối tượng TblUserDao
		TblUserDao userDao = new TblUserDaoImpl();
		// Gán lại giá trị cho count
		TblUserEntity user = userDao.getTblUserByLoginName(loginName, id);
		// Kiểm tra xem user có tồn tại hay không
		if (user != null) {
			// Trả về giá trị true
			return check = true;
		}
		// Trả về giá trị cho phương thức
		return check;
	}

	/**
	 * @see manageuser.logics.TblUserLogic#checkExistEmail(java.lang.String, int)
	 * 
	 */
	@Override
	public boolean checkExistEmail(String email, int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến check
		boolean check = false;
		// Khởi tạo đối tượng TblUserdao
		TblUserDao userDao = new TblUserDaoImpl();
		// Gán giá trị cho count
		TblUserEntity user = userDao.getExistEmail(email, id);
		// Kiểm tra điều kiện nếu count khác 0
		if (user != null) {
			// Trả về giá trị true
			return check = true;
		}
		// Trả về giá trị cho phương thức
		return check;
	}

	/**
	 * @see manageuser.logics.TblUserLogic#createUser(manageuser.entities.UserInforEntity)
	 * 
	 */
	@SuppressWarnings("finally")
	@Override
	public boolean createUser(UserInforEntity userInfor) {

		// Khởi tạo các đối tượng và biến check
		TblUserEntity tblUser = Common.getTblUserByUserInfor(userInfor);
		TblUserDao tblUserDao = new TblUserDaoImpl();
		TblDetailUserJapanDao tblDetailDao = new TblDetailUserJapanDaoImpl();
		boolean check = true;

		// Mở try
		try {
			// Mở kết nối
			tblUserDao.open();
			// Ngắt autocommit
			tblUserDao.disableAutoCommit();
			// Lấy về TblUserId
			int tblUserId = tblUserDao.insertUser(tblUser);
			// Kiểm tra tồn tại trình độ tiếng Nhật
			if (!Constant.N0.equals(userInfor.getCodeLevel()) && tblUserId > 0) {
				TblDetailUserJapanEntity tblDetail = Common.getTblDetailByUserInfor(userInfor);
				// Set TblUser id cho đối tượng tblDetail
				tblDetail.setUserId(tblUserId);
				// Lấy kết nối
				Connection con = tblUserDao.getConn();
				// Set kết nối cho đối tượng TblDetailUserJapan
				tblDetailDao.setConn(con);
				// Chèn đối tượng TblDetailUserJapan vào trong DB
				tblDetailDao.insertDetailUserJapan(tblDetail);
			}
			// Set Commit
			tblUserDao.setCommit();

			// Bắt lỗi
		} catch (ClassNotFoundException | SQLException e) {
			// Set Rollback
			tblUserDao.setRollBack();
			check = false;
		} finally {
			// Đóng kết nối
			tblUserDao.close();
			// Trả về kết quả cho phương thức
			return check;
		}

	}

	/**
	 * @see manageuser.logics.TblUserLogic#checkExistTblUserId(int)
	 * 
	 */
	@Override
	public boolean checkExistTblUserId(int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo và lấy giá trị cho userId từ hàm checkExistTblUserById
		TblUserEntity user = dao.getTblUserById(id);
		// Kiểm tra điều kiện userId có khác 0 không
		if (user != null) {
			// Đúng thì trả về true
			return true;
		}
		// Sai trả về false
		return false;
	}

	/**
	 * @see manageuser.logics.TblUserLogic#getUserInforById(int)
	 * 
	 */
	@Override
	public UserInforEntity getUserInforById(int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo là lấy giá trị cho đối tượng UserInforEntity
		UserInforEntity user = dao.getUserInforById(id);
		// Trả về đối tượng user
		return user;
	}

	/**
	 * @see manageuser.logics.TblUserLogic#updateUserInfor(manageuser.entities.UserInforEntity)
	 * 
	 */
	@SuppressWarnings("finally")
	@Override
	public boolean updateUserInfor(UserInforEntity user) {
		// Khởi tạo đối tượng TblDetailUserJapanLogic, TblDetailUserJapanDao
		TblDetailUserJapanLogic tdl = new TblDetailUserJapanLogicImpl();
		TblUserDao tblUserDao = new TblUserDaoImpl();
		TblDetailUserJapanDao tblDetailDao = new TblDetailUserJapanDaoImpl();
		// Khởi tạo và gán giá trị tblUser
		TblUserEntity tblUser = Common.getTblUserByUserInfor(user);
		// Khởi tạo giá trị kiểm tra
		boolean checkResult = false;

		// Mở try
		try {
			// Khởi tạo và gán giá trị cho các đối tượng TblDetailUserJapanEntity
			TblDetailUserJapanEntity dbUser = tblDetailDao.getTblDetailUserJapanById(user.getUserId());
			TblDetailUserJapanEntity inputUser = Common.getTblDetailByUserInfor(user);

			// Kiểm tra xem kiểu update detailUserJapan
			int type = tdl.checkTblDetailUserJapan(inputUser, dbUser);
			// Mở kết nối
			tblUserDao.open();
			// Ngắt auto commit
			tblUserDao.disableAutoCommit();
			// Kiểm tra xem việc updateUserInfor có thành công hay không
			boolean check = tblUserDao.updateUserInfor(tblUser);
			// Kiểm tra điều kiện
			if (check) {
				boolean checkCase = false;
				// Dùng switch case để xét các trường hợp update, insert, delete.
				switch (type) {

				// Trường hợp Insert
				case Constant.CASE_1:
					conn = (Connection) tblUserDao.getConn();
					tblDetailDao.setConn(conn);
					checkCase = tblDetailDao.insertDetailUserJapan(inputUser);
					break;

				// Trường hợp Update
				case Constant.CASE_2:
					conn = (Connection) tblUserDao.getConn();
					tblDetailDao.setConn(conn);
					checkCase = tblDetailDao.updateTblDetailUserJapan(inputUser);
					break;

				// Trường hợp Delete
				case Constant.CASE_3:
					conn = (Connection) tblUserDao.getConn();
					tblDetailDao.setConn(conn);
					checkCase = tblDetailDao.deleteTblDetailUserJapan(inputUser.getUserId());
					break;

				// Trường hợp không thực hiện gì
				case Constant.CASE_4:
					checkCase = true;
					break;
				}

				// Kiểm tra điều kiện cho từng case
				if (checkCase == true) {
					tblUserDao.setCommit();
					checkResult = true;
				}

				// Khi không đúng điều kiện
			} else {
				// Set RollBack
				tblUserDao.setRollBack();
			}

			// Bắt exception
		} catch (Exception e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			// Set RollBack
			tblUserDao.setRollBack();
		} finally {
			// Đóng kết nối
			tblUserDao.close();
			// Trả về giá trị cho phương thức
			return checkResult;
		}

	}

	/**
	 * @see manageuser.logics.TblUserLogic#deleteUserInfor(int, boolean)
	 * 
	 */
	@SuppressWarnings("finally")
	@Override
	public boolean deleteUserInfor(int id, boolean check) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến checkDeleteUserInfor
		boolean checkDeleteUser = true;
		boolean checkDeleteDetail = true;
		// Khởi tạo đối tượng
		TblUserDao tblUserDao = new TblUserDaoImpl();
		// Mở try
		try {
			// Mở kết nối
			tblUserDao.open();
			// Ngắt auto commit
			tblUserDao.disableAutoCommit();
			if (check) {
				// Khởi tạo đối tượng TblDetailUserJapanDao
				TblDetailUserJapanDao tblDetailDao = new TblDetailUserJapanDaoImpl();
				// Lấy về connection
				conn = (Connection) tblUserDao.getConn();
				// Set connectio cho đối tượng
				tblDetailDao.setConn(conn);
				checkDeleteDetail = tblDetailDao.deleteTblDetailUserJapan(id);
			}
			// Xóa TblUser
			if (checkDeleteDetail) {
				tblUserDao.deleteTblUser(id);
			} else {
				checkDeleteUser = false;
			}
			// Set Commit
			tblUserDao.setCommit();
			// Bắt lỗi
		} catch (Exception e) {
			// Set RollBack
			tblUserDao.setRollBack();
			checkDeleteUser = false;

		} finally {
			// Đóng kết nối
			tblUserDao.close();
			// Trả về kết quả cho phương thức
			return checkDeleteUser;
		}

	}

	/**
	 * @see manageuser.logics.TblUserLogic#checkExistToDeleteUser(int)
	 * 
	 */
	@Override
	public int checkExistToDeleteUser(int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến kiểm tra
		int check = -1;
		// Khởi tạo đối tượng TblUserDao
		TblUserDao tbl = new TblUserDaoImpl();
		// Gán lại giá trị cho biến check
		check = tbl.checkExistToDeleteUser(id);
		// Trả về giá trị cho phương thức
		return check;
	}

}
