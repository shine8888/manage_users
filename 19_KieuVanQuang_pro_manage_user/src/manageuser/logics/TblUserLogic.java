/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [TblUserLogic.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.entities.UserInforEntity;
/**
 * Interface TblUserLogic khởi tạo các logic với bảng TblUser
 * @author Kiều Văn Quang
 *
 */
public interface TblUserLogic {
	/**
	 * Hàm kiểm tra tồn tại LoginId
	 * 
	 * @param userName			Truyền vào userName
	 * @param password			Truyền vào password
	 * @return boolean 			Trả về giá trị true/false
	 */
	public boolean checkExistLoginId(String userName, String password);

	/**
	 * Hàm lấy ra tất cả các user
	 * 
	 * @param offset							Truyền vào vị trí lấy bản ghi
	 * @param limit								Truyền vào số lượng bản ghi hiển thị trên 1 trang
	 * @param groupId							Truyền vào group id
	 * @param fullName							Truyền vào fullName
	 * @param sortType							Truyền vào trường sắp xếp	
	 * @param sortByFullName					Truyền vào kiểu sắp xếp theo fullName			
	 * @param sortByCodeLevel					Truyền vào kiểu sắp xếp theo sortByCodeLevel	
	 * @param sortByEndDate						Truyền vào kiểu sắp xếp theo sortByEndDate	
	 * @return listUsers						Trả về giá trị cho listUser
	 * @throws ClassNotFoundException			Ném ra lỗi ClassNotFoundException
	 * @throws SQLException						Ném ra lỗi SQLException
	 */
	public ArrayList<UserInforEntity> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm lấy ra tổng số user
	 * 
	 * @param groupId					Truyền vào group id
	 * @param FullName					Truyền vào fullName
	 * @return Trả về tổng số user		Trả về tổng số user
	 * @throws ClassNotFoundException	Ném ra lỗi ClassNotFoundException
	 * @throws SQLException				Ném ra lỗi SQLException
	 */
	public int getTotalUser(int groupId, String FullName) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm kiếm tra tồn tại loginName
	 * 
	 * @param loginName						Truyền vào loginName
	 * @return boolean						Trả về giá trị true/false 
	 * @throws ClassNotFoundException		Ném ra lỗi ClassNotFoundException
	 * @throws SQLException					Ném ra lỗi SQLException
	 */
	public boolean checkExistLoginName(String loginName, int id) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm kiểm tra tồn tại email
	 * 
	 * @param email							Truyền vào email 
	 * @return boolean						Trả về giá trị true/false	
	 * @throws ClassNotFoundException		Ném ra lỗi ClassNotFoundException
	 * @throws SQLException					Ném ra lỗi SQLException
	 */
	public boolean checkExistEmail(String email, int id) throws ClassNotFoundException, SQLException;

	/**
	 * Phương thức createUser và kiểm tra việc add có thành công hay không
	 * 
	 * @param user							Truyền vào một đối tượng
	 * @return boolean						Trả về là True/False
	 * @throws ClassNotFoundException		Ném ra lỗi ClassNotFoundException
	 * @throws SQLException					Ném ra lỗi SQLException
	 */
	public boolean createUser(UserInforEntity user);

	/**
	 * Hàm kiểm tra sự tồn tại của user trong DB
	 * 
	 * @param id							Nhập vào id user
	 * @return boolean						Trả về kết quả true/false
	 * @throws ClassNotFoundException		Ném ra lỗi ClassNotFoundException
	 * @throws SQLException					Ném ra lỗi SQLException
	 */
	public boolean checkExistTblUserId(int id) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm lấy về một UserInforEntity
	 * 
	 * @param id							Nhập vào id user
	 * @return UserInforEntity				Trả về đối tượng UserInforEntity
	 * @throws ClassNotFoundException		Ném ra lỗi ClassNotFoundException
	 * @throws SQLException					Ném ra lỗi SQLException
	 */
	public UserInforEntity getUserInforById(int id) throws ClassNotFoundException, SQLException;

	/**
	 * Phương thức update user vào db và kiểm tra việc update có thành công hay
	 * không
	 * 
	 * @param user							Truyền vào một đối tượng
	 * @return boolean						Trả về là True/False
	 * @throws ClassNotFoundException		Ném ra lỗi ClassNotFoundException
	 * @throws SQLException					Ném ra lỗi SQLException
	 */
	public boolean updateUserInfor(UserInforEntity user);

	/**
	 * Hàm xóa thông tin của người dùng
	 * 
	 * @param id 							Truyền vào id user
	 * @param check 						Truyền vào biến kiểu boolean
	 * @return boolean						Kiểu trả về là true/false
	 * @throws ClassNotFoundException		Ném ra lỗi ClassNotFoundException
	 * @throws SQLException					Ném ra lỗi SQLException
	 */
	public boolean deleteUserInfor(int id, boolean check) throws ClassNotFoundException, SQLException;
	
	/**
	 * Hàm kiểm tra trước khi xóa user
	 * 
	 * @param id							Truyền vào id
	 * @return int							Trả về một giá trị int
	 * @throws ClassNotFoundException		Ném ra lỗi ClassNotFoundException
	 * @throws SQLException					Ném ra lỗi SQLException
	 */
	public int checkExistToDeleteUser(int id) throws ClassNotFoundException, SQLException;

}
