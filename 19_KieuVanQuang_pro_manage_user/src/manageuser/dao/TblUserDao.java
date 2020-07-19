/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [TblUserDao.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInforEntity;

/**
 * Interface TblUserDao khởi tạo các phương thức dùng để xử lý với database
 * 
 * @author Kiều Văn Quang
 *
 */
public interface TblUserDao extends BaseDao{
	
	/**
	 * Hàm lấy đối tượng TblUserEntity bởi userName
	 * 
	 * @param userName						Truyền vào userName						
	 * @return TblUserEntity				Trả về đối tượng TblUserEntity
	 * @throws ClassNotFoundException		Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException					Ném ra ngoại lệ SQL
	 */
	public TblUserEntity getTblUserByUserName(String userName) throws ClassNotFoundException, SQLException;

	/**
	  * Hàm lấy về list user có trong DB
	 * 
	 * @param offset						Truyền vào vị trí bắt đầu lấy	
	 * @param limit							Truyền vào số lượng bản ghi lấy ra
	 * @param groupId						Truyền vào groupID của user
	 * @param fullName						Truyền vào fullName của user
	 * @param sortType						Truyền vào trường sắp xếp
	 * @param sortByFullName				Truyền vào kiểu sắp xếp cho sortByFullName
	 * @param sortByCodeLevel				Truyền vào kiểu sắp xếp cho sortByCodeLevel
	 * @param sortByEndDate					Truyền vào kiểu sắp xếp cho sortByEndDate	
	 * @return ArrayList<UserInforEntity>	Trả về list đối tượng UserInforEntity
	 * @throws ClassNotFoundException		Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException					Ném ra ngoại lệ SQL
	 */
	public ArrayList<UserInforEntity> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException;
	
	/**
	 * Hàm lấy về tên các trường trong database
	 * 
	 * @return List<String>					Trả về list các tên trường
	 * @throws ClassNotFoundException		Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException					Ném ra ngoại lệ SQL
	 */
	public List<String> getListColumn() throws ClassNotFoundException, SQLException;
	
	/**
	 * Hàm lấy về tổng số user trong DB thỏa mãn điều kiện tìm kiếm
	 * 
	 * @param groupId					Truyền vào groupID của user
	 * @param fullName					Truyền vào fullName của user
	 * @return int						Trả về số lượng user thỏa mãn điều kiện
	 * @throws ClassNotFoundException	Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException				Ném ra ngoại lệ SQL
	 */
	public int getTotalUser(int groupId, String fullName) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm kiểm tra một user có tồn tại trong DB
	 * 
	 * @param loginName					Truyền vào loginName của user
	 * @return TblUserEntity 			Trả về đối tượng TblUserEntity
	 * @throws ClassNotFoundException	Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException				Ném ra ngoại lệ SQL
	 */
	public TblUserEntity getTblUserByLoginName(String loginName, int id) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm kiểm tra một user có tồn tại trong DB
	 * 
	 * @param email						Truyền vào email của user
	 * @param id						Truyền vào id của user
	 * @return int						Trả về số lượng email thỏa mãn
	 * @throws ClassNotFoundException	Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException				Ném ra ngoại lệ SQL
	 */
	public TblUserEntity getExistEmail(String email, int id) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm chèn một TblUser vào trong Database
	 * 
	 * @param user						Truyền vào đối tượng TblUserEntity
	 * @return int						Trả về là id của user mới được chèn vào
	 * @throws ClassNotFoundException	Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException				Ném ra ngoại lệ SQL
	 */
	public int insertUser(TblUserEntity user) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm kiểm tra một user có tồn tại trong DB
	 * 
	 * @param id						Truyền vào id của user
	 * @return int						Trả vè số lượng TblUserEntity tồn tại trong DB
	 * @throws ClassNotFoundException	Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException				Ném ra ngoại lệ SQL
	 */
	public TblUserEntity getTblUserById(int id) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm lấy về một UserInfor
	 * 
	 * @param id						Truyền vào id của user
	 * @return UserInforEntity			Trả về đối tượng UserInforEntity
	 * @throws ClassNotFoundException	Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException				Ném ra ngoại lệ SQL
	 */
	public UserInforEntity getUserInforById(int id) throws ClassNotFoundException, SQLException;
	
	/**
	 * Hàm update thông tin của người dùng
	 * 
	 * @param user						Truyền vào một user
	 * @return boolean					Trả về kết quả là true/false
	 * @throws ClassNotFoundException	Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException				Ném ra ngoại lệ SQL
	 */
	public boolean updateUserInfor(TblUserEntity user) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm xóa thông tin của TblUser
	 * 
	 * @param id						Truyền vào id của user
	 * @return boolean					Trả về kết quả là true/false
	 * @throws ClassNotFoundException	Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException				Ném ra ngoại lệ SQL
	 */
	public boolean deleteTblUser(int id) throws ClassNotFoundException, SQLException;
	
	/**
	 * Hàm kiểm tra tồn tại của user trước khi xóa
	 * 
	 * @param id						Truyền vào id của user
	 * @return int						Trả về rule của user
	 * @throws ClassNotFoundException	Ném ra ngoại lệ không tìm thấy class
	 * @throws SQLException				Ném ra ngoại lệ SQL
	 */
	public int checkExistToDeleteUser(int id) throws ClassNotFoundException, SQLException;

}
