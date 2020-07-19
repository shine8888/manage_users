/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [TblUserDaoImpl.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.TblUserDao;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInforEntity;
import manageuser.utils.Constant;

/**
 * Class TblUserDaoImpl thực hiện xử lý truy vấn với Database
 * 
 * @author Kiều Văn Quang
 *
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {

	/**
	 * @see manageuser.dao.TblUserDao#getListUsers(int, int, int, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * 
	 */
	public ArrayList<UserInforEntity> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException {
		// khởi tạo list lưu kết quả
		ArrayList<UserInforEntity> list = new ArrayList<UserInforEntity>();
		try {
			// mở kết nối
			open();
			if (conn != null) {
				// tạo câu truy vấn
				StringBuilder sql = new StringBuilder();
				sql.append(
						"select tbl_user.user_id, tbl_user.full_name, tbl_user.birthday, mst_group.group_name,tbl_user.email, tbl_user.tel,"
								+ " mst_japan.name_level, tbl_detail_user_japan.end_date, tbl_detail_user_japan.total ");
				sql.append("from tbl_user INNER JOIN mst_group on tbl_user.group_id = mst_group.group_id ");
				sql.append("left join tbl_detail_user_japan on tbl_detail_user_japan.user_id = tbl_user.user_id ");
				sql.append("left join mst_japan on tbl_detail_user_japan.code_level = mst_japan.code_level ");
				sql.append("where ");
				// nếu nhóm tìm kiếm bằng 0 thì sẽ thực hiện tìm tất cả các nhóm
				if (groupId != 0) {
					sql.append("tbl_user.group_id = ? and ");
				}
				// thêm điều kiện về rule để tìm người dùng không phải admin
				sql.append("tbl_user.rule = ? ");
				// thêm điều kiện về tên
				sql.append("and tbl_user.full_name like concat('%',?,'%') ");
				// thêm điêu kiện sắp xếp
				String orderby = "";
				if (Constant.SORT_TYPE_NAME.equals(sortType)) {
					orderby = "order by tbl_user.full_name " + sortByFullName + ", mst_japan.code_level "
							+ sortByCodeLevel + ", tbl_detail_user_japan.end_date " + sortByEndDate;
				} else if (Constant.SORT_TYPE_LEVEL.equals(sortType)) {
					orderby = "order by mst_japan.code_level " + sortByCodeLevel + ", tbl_user.full_name "
							+ sortByFullName + ", tbl_detail_user_japan.end_date " + sortByEndDate;
				} else if (Constant.SORT_TYPE_END_DATE.equals(sortType)) {
					orderby = "order by tbl_detail_user_japan.end_date " + sortByEndDate + ", tbl_user.full_name "
							+ sortByFullName + ", mst_japan.code_level " + sortByCodeLevel;
				}
				sql.append(orderby);

				// thêm câu lệnh để lấy ra số lượng và vị trí cần lấy bản ghi
				sql.append(" limit ? offset ? ;");
				// tạo preparedStatment
				PreparedStatement stInsertInto = conn.prepareStatement(sql.toString());
				// truyền giá trị tham số vào câu lệnh truy vấn
				int j = 1;
				if (groupId != 0) {
					stInsertInto.setInt(j++, groupId);
				}
				stInsertInto.setInt(j++, Constant.RULE_USER);
				stInsertInto.setString(j++, fullName);
				stInsertInto.setInt(j++, limit);
				stInsertInto.setInt(j++, offset);
				ResultSet rsCheck = stInsertInto.executeQuery();
				// lấy ra kết quả
				while (rsCheck.next()) {
					UserInforEntity user = new UserInforEntity();
					user.setUserId(Integer.parseInt(rsCheck.getString("user_id")));
					user.setFullName(rsCheck.getString("full_name"));
					user.setBirthday(rsCheck.getDate("birthday"));
					user.setGroupName(rsCheck.getString("group_name"));
					user.setEmail(rsCheck.getString("email"));
					user.setTel(rsCheck.getString("tel"));
					user.setNameLevel(rsCheck.getString("name_level"));
					user.setEndDate(rsCheck.getDate("end_date"));
					user.setTotal(rsCheck.getInt("total"));

					list.add(user);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			// in ra log
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		} finally {
			// đóng kết nối
			close();
		}
		return list;
	}

	/**
	 * @see manageuser.dao.TblUserDao#getTotalUser(int, java.lang.String)
	 * 
	 */
	@Override
	public int getTotalUser(int groupId, String fullName) throws ClassNotFoundException, SQLException {
		// Khởi tạo totalUser
		int totalUser = 0;
		try {
			// Mở kết nối
			open();
			if (conn != null) {
				// Tạo câu truy vấn
				StringBuilder sql = new StringBuilder();
				sql.append("Select Count(login_name) ");
				sql.append("From tbl_user ");
				sql.append("Where tbl_user.rule = ? ");
				sql.append("And tbl_user.full_name like concat('%',?,'%') ");
				// Kiểm tra điều kiện groupId khác 0 thì nối thêm câu truy vấn
				if (groupId != 0) {
					sql.append("and tbl_user.group_id = ? ;");
				}
				// Khởi tạo câu lệnh prepareStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// Khởi tạo biến i
				int i = 1;
				pre.setInt(i++, Constant.RULE_USER);
				pre.setString(i++, fullName);
				if (groupId != 0) {
					pre.setInt(i++, groupId);
				}
				// Khởi tạo đối tượng ResultSet để lấy kết quả
				ResultSet result = pre.executeQuery();
				while (result.next()) {
					totalUser = Integer.parseInt(result.getString(1));
				}
			}
			// Mở bắt lỗi
		} catch (SQLException | ClassNotFoundException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối
			close();
		}
		// Trả về kết quả cho phương thức
		return totalUser;
	}

	/**
	 * @see manageuser.dao.TblUserDao#getTblUserByUserName(java.lang.String)
	 * 
	 */
	@SuppressWarnings({ "null", "finally" })
	@Override
	public TblUserEntity getTblUserByUserName(String userName) throws ClassNotFoundException, SQLException {
		// Khởi tạo đối tượng user với giá trị null
		TblUserEntity user = null;
		// Mở try
		try {
			// Mở kết nối DB
			open();
			// Kiểm tra xem kết nối có tồn tại hay không
			if (conn != null) {
				// Khởi tạo câu truy vấn sql
				StringBuilder sql = new StringBuilder("");
				// Append các giá trị cho câu truy vấn
				sql.append("Select Tbl_User.pass, tbl_user.salt ");
				sql.append("From Tbl_User where ");
				sql.append("Tbl_User.login_name = ? ");
				sql.append("and rule = ? ;");

				// Khởi tạo lệnh PrepareStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// Truyền giá trị vào câu truy vấn
				int index = 1;
				pre.setString(index++, userName);
				pre.setInt(index++, Constant.RULE_ADMIN);
				// Khởi tạo resultset để lấy kết quả từ câu truy vấn
				ResultSet rs = pre.executeQuery();
				// Thực hiện vòng lặp lấy kết quả
				while (rs.next()) {
					// Khởi tạo biến i
					int i = 1;
					// Khởi tạo đối tượng user
					user = new TblUserEntity();
					// Khởi tạo và gán giá trị cho password
					String password = rs.getString(i++);
					// Khởi tạo và gán giá trị cho salt
					String salt = rs.getString(i++);
					// Them password và salt vào user
					user.setPassword(password);
					user.setSalt(salt);
					// Kết thúc vòng while
				}
				// Kết thúc if
			}
			// Mở bắt lỗi
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			// Ném lỗi đi cho phương thức khác gọi đến nhận biết
			throw e;
		} finally {
			// Đóng kết nối
			close();
			// Trả về user
			return user;
		}
	}

	/**
	 * @see manageuser.dao.TblUserDao#checkExistLoginName(java.lang.String)
	 * 
	 */
	@SuppressWarnings("finally")
	@Override
	public TblUserEntity getTblUserByLoginName(String loginName, int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo total
		TblUserEntity user = null;
		// Mở try
		try {
			// Mở kết nối DB
			open();
			// Kiểm tra xem kết nối có tồn tại hay không
			if (conn != null) {
				// Khởi tạo câu truy vấn sql
				StringBuilder sql = new StringBuilder("");
				sql.append("Select  u.login_name, u.user_id from tbl_user u ");
				sql.append("Where u.login_name = ? ");
				// Kiểm tra điều kiện xem id có tồn tại hay không
				if (id > 0) {
					sql.append("And u.user_id = ?;");
				}
				// Khởi tạo lệnh PrepareStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// truyền vào giá trị cho lệnh preparestatement
				int index = 1;
				pre.setString(index++, loginName);
				// Kiểm tra điều kiện khi id tồn tại thì thêm giá trị vào để thực thi
				// sql
				if (id > 0) {
					pre.setInt(index++, id);
				}
				// Khởi tạo resultset để lấy kết quả từ câu truy vấn
				ResultSet rs = pre.executeQuery();
				// Khởi tạo đối tượng user
				while (rs.next()) {
					user = new TblUserEntity();
					user.setLoginName(rs.getString("login_name"));
					user.setUserId(rs.getInt("user_id"));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			// Ném lỗi đi cho phương thức khác gọi đến nhận biết
			throw e;
		} finally {
			// Đóng kết nối DB
			close();
			// Trả về giá trị cho phương thức
			return user;
		}
	}

	/**
	 * @see manageuser.dao.TblUserDao#getExistEmail(java.lang.String, int)
	 * 
	 */
	@SuppressWarnings("finally")
	@Override
	public TblUserEntity getExistEmail(String email, int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo total
		TblUserEntity user = null;
		// Mở try
		try {
			// Mở kết nối DB
			open();
			// Kiểm tra xem kết nối có tồn tại hay không
			if (conn != null) {
				// Khởi tạo câu truy vấn sql
				StringBuilder sql = new StringBuilder("");
				sql.append("Select u.email from tbl_user u ");
				sql.append("Where u.email = ? ");
				if (id != 0) {
					sql.append("and u.user_id = ? ;");
				}
				// Khởi tạo lệnh PrepareStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// truyền vào giá trị cho lệnh preparestatement
				int index = 1;
				pre.setString(index++, email);
				if (id != 0) {
					pre.setInt(index++, id);
				}
				// Khởi tạo resultset để lấy kết quả từ câu truy vấn
				ResultSet rs = pre.executeQuery();
				while (rs.next()) {
					user = new TblUserEntity();
					user.setEmail(rs.getString("email"));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			// Ném lỗi đi cho phương thức khác gọi đến nhận biết
			throw e;
		} finally {
			// Đóng kết nối DB
			close();
			// Trả về giá trị cho phương thức
			return user;
		}
	}

	/**
	 * @see manageuser.dao.TblUserDao#insertUser(manageuser.entities.TblUserEntity)
	 * 
	 */
	@Override
	public int insertUser(TblUserEntity user) throws ClassNotFoundException, SQLException {
		// Khởi tạo giá trị id
		int id = 0;
		// Mở bắt lỗi
		try {
			// Kiểm tra xem kết nối có tồn tại hay không
			if (conn != null) {
				// Tạo mảng chứa tên cột là user_id của bảng Tbl_User
				String columnNames[] = new String[] { "user_id" };
				// Khởi tạo câu sql
				StringBuilder sql = new StringBuilder();
				// Gán các giá trị cho câu sql
				sql.append("Insert IGNORE Into Tbl_user(group_id, login_name, pass, full_name, ");
				sql.append("full_name_kana, email, tel, birthday, rule, salt)");
				sql.append(" values(?,?,?,?,?,?,?,?,?,?);");
				// Khởi tạo câu lệnh PreparedStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString(), columnNames);
				// Khởi tạo index giá trị bằng 1
				int index = 1;
				// Truyền các giá trị vào câu lệnh preparedStatement để thực hiện truy vấn
				pre.setInt(index++, user.getGroupId());
				pre.setString(index++, user.getLoginName());
				pre.setString(index++, user.getPassword());
				pre.setString(index++, user.getFullName());
				pre.setString(index++, user.getFullNameKana());
				pre.setString(index++, user.getEmail());
				pre.setString(index++, user.getTel());
				pre.setDate(index++, (Date) user.getBirthday());
				pre.setInt(index++, user.getRule());
				pre.setString(index++, user.getSalt());
				// Kiểm tra sau khi thực hiện câu truy vấn
				pre.execute();
				ResultSet rs = pre.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
			// Mở bắt lỗi
		} catch (Exception e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			// Ném lỗi
			throw e;
		}
		// Trả về giá trị cho phương thức
		return id;
	}

	/**
	 * @see manageuser.dao.TblUserDao#getNumberTblUserById(int)
	 * 
	 */
	@SuppressWarnings("finally")
	public TblUserEntity getTblUserById(int id) throws ClassNotFoundException, SQLException {
		TblUserEntity user = null;
		// Mở try
		try {
			// Mở kết nối DB
			open();
			// Kiểm tra xem kết nối có tồn tại hay không
			if (conn != null) {
				// Khởi tạo câu truy vấn sql
				StringBuilder sql = new StringBuilder("");
				sql.append("Select  u.user_id, u.login_name from tbl_user u ");
				sql.append("Where u.user_id = ?;");

				// Khởi tạo lệnh PrepareStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// truyền vào giá trị cho lệnh preparestatement
				int index = 1;
				pre.setInt(index++, id);
				// Khởi tạo resultset để lấy kết quả từ câu truy vấn
				ResultSet rs = pre.executeQuery();
				while (rs.next()) {
					user = new TblUserEntity();
					user.setUserId(rs.getInt("user_id"));
				}
				// Gán giá trị cho total
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			// Ném lỗi đi cho phương thức khác gọi đến nhận biết
			throw e;
		} finally {
			// Đóng kết nối DB
			close();
			// Trả về giá trị cho phương thức
			return user;
		}
	}

	/**
	 * @see manageuser.dao.TblUserDao#getUserInforById(int)
	 * 
	 */
	@SuppressWarnings("finally")
	@Override
	public UserInforEntity getUserInforById(int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo UserInforEntity
		UserInforEntity user = new UserInforEntity();
		try {
			// Mở kết nối
			open();
			// Kiểm tra xem conn có khác null hay không
			if (conn != null) {
				// Khởi tạo câu sql bằng StringBuilder
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT u.user_id, u.login_name,mg.group_id, mg.group_name, ");
				sql.append("u.full_name, u.full_name_kana, u.birthday, u.email, ");
				sql.append("u.tel, mj.name_level,mj.code_level, dj.start_date, dj.end_date, dj.total ");
				sql.append("FROM Tbl_User u Inner Join Mst_Group mg ON u.group_id = mg.group_id ");
				sql.append("LEFT JOIN tbl_detail_user_japan dj ON dj.user_id = u.user_id ");
				sql.append("LEFT JOIN Mst_Japan mj ON mj.code_level = dj.code_level ");
				sql.append("WHERE u.user_id = ? ");
				sql.append("AND u.rule = ?;");
				// Khởi tạo câu lệnh PreparedStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// Truyền giá trị để thực thi câu lệnh
				int index = 1;
				pre.setInt(index++, id);
				pre.setInt(index++, Constant.RULE_USER);
				// Khai báo đối tượng resultset để lấy các giá trị tương ứng gán vào user
				ResultSet rs = pre.executeQuery();
				// Mở vòng lặp while lấy và gán giá tị
				while (rs.next()) {
					user.setUserId(rs.getInt("user_id"));
					user.setLoginName(rs.getString("login_name"));
					user.setGroupId(rs.getInt("group_id"));
					user.setGroupName(rs.getString("group_name"));
					user.setFullName(rs.getString("full_name"));
					user.setFullNameKana(rs.getString("full_name_kana"));
					user.setBirthday(rs.getDate("birthday"));
					user.setEmail(rs.getString("email"));
					user.setTel(rs.getString("tel"));
					if (rs.getString("name_level") != null) {
						user.setCodeLevel(rs.getString("code_level"));
						user.setNameLevel(rs.getString("name_level"));
						user.setStartDate(rs.getDate("start_date"));
						user.setEndDate(rs.getDate("end_date"));
						user.setTotal(rs.getInt("total"));
					}
				}
			}
			// Mở bắt lỗi
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			// Ném đi lỗi
			throw e;
		} finally {
			// Đóng kết nối
			close();
			// Trả về giá trị cho phương thức
			return user;
		}

	}

	/**
	 * @see manageuser.dao.TblUserDao#updateUserInfor(manageuser.entities.TblUserEntity)
	 * 
	 */
	@Override
	public boolean updateUserInfor(TblUserEntity user) throws ClassNotFoundException, SQLException {
		// Mở try
		try {
			// Kiểm tra xem conn có tồn tại hay không
			if (conn != null) {
				// Khởi tạo câu truy vấn
				StringBuilder sql = new StringBuilder();
				// Gán thêm giá trị cho câu truy vấn
				sql.append("Update tbl_user Set ");
				sql.append("group_id =?, full_name=?, full_name_kana=?, email=?, tel=?, birthday=? ");
				sql.append("Where user_id = ?;");
				// Khởi tạo câu lệnh preparedStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// Khởi tạo index
				int index = 1;
				// Truyền các giá trị vào câu preparedStatement để thực hiện câu truy vấn
				pre.setInt(index++, user.getGroupId());
				pre.setString(index++, user.getFullName());
				pre.setString(index++, user.getFullNameKana());
				pre.setString(index++, user.getEmail());
				pre.setString(index++, user.getTel());
				pre.setDate(index++, user.getBirthday());
				pre.setInt(index++, user.getUserId());
				pre.executeUpdate();
			}
			// Đúng thì trả về true
			return true;
			// Mở bắt lỗi
		} catch (SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			// Sai thì trả về false
			return false;
		}
	}

	/**
	 * @see manageuser.dao.TblUserDao#deleteTblUser(int)
	 * 
	 */
	@Override
	public boolean deleteTblUser(int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến check
		boolean check = false;
		// Mở try
		try {
			// Kiểm tra xem conn có tồn tại hay không
			if (conn != null) {
				// Khởi tạo câu truy vấn
				StringBuilder sql = new StringBuilder();
				// Gán thêm giá trị cho câu truy vấn
				sql.append("Delete From Tbl_user Where user_id = ?;");
				// Khởi tạo câu lệnh preparedStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// Khởi tạo index
				int index = 1;
				// Truyền các giá trị vào câu preparedStatement để thực hiện câu truy vấn
				pre.setInt(index++, id);
				pre.executeUpdate();
			}
			check = true;
			// Mở bắt lỗi
		} catch (SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		}
		return check;
	}

	/**
	 * @see manageuser.dao.TblUserDao#checkExistToDeleteUser(int)
	 * 
	 */
	@Override
	public int checkExistToDeleteUser(int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo giá trị trả về
		int a = -1;
		// Mở try
		try {
			// Mở kết nối tới DB
			open();
			// Kiểm tra xem kết nối tồn tại hay chưa
			if (conn != null) {
				// Khởi tạo và thêm giá trị cho câu SQL
				StringBuilder sql = new StringBuilder();
				sql.append("Select t.rule From Tbl_User t ");
				sql.append("Where t.user_id = ?; ");

				// Khởi tạo và chạy câu lệnh preparedStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				int index = 1;
				pre.setInt(index++, id);
				// Lấy về tập kết quả
				ResultSet rs = pre.executeQuery();
				rs.next();
				// Gán giá trị cho total
				a = rs.getInt("rule");
			}
			// Bắt lỗi
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;

		} finally {
			// Đóng kết nối
			close();
		}
		// Trả về giá trị cho phương thức
		return a;
	}

	/**
	 * @see manageuser.dao.TblUserDao#getListColumnSort()
	 * 
	 */
	@Override
	public List<String> getListColumn() throws ClassNotFoundException, SQLException {
		// Khởi tạo list column
		List<String> listColumn = new ArrayList<String>();
		// Mở try
		try {
			// Mở kết nối
			open();
			// Kiểm tra tồn tại của kết nối
			if (conn != null) {
				// Khởi tạo câu query
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT column_name from INFORMATION_SCHEMA.columns ");
				sql.append("WHERE TABLE_SCHEMA='19_kieuvanquang_manageuser' ;");
				// Khởi tạo và thực thi câu lệnh prepareStatement
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// Lấy về tập kết quả khi thực thi sql
				ResultSet rs = pre.executeQuery();
				// Dùng while để kiểm tra và gán giá trị cho listColumn
				while (rs.next()) {
					listColumn.add(rs.getString(1));
				}
			}
			// Bắt lỗi
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối
			close();
		}
		// Trả về listColumn
		return listColumn;
	}

}
