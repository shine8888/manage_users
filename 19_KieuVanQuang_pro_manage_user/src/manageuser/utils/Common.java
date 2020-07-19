/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [Common.java.java], [Feb 24, 2020] [Kiều Văn Quang]
 */
package manageuser.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import manageuser.dao.TblUserDao;
import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.MstGroupEntity;
import manageuser.entities.MstJapanEntity;
import manageuser.entities.TblDetailUserJapanEntity;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInforEntity;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.MstJapanLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;

/**
 * Class Common chứa các phương thức dùng chung cho cả project
 * 
 * @author Kiều Văn Quang
 *
 */
public class Common {

	/**
	 * Hàm so sánh 2 chuỗi
	 * @param a				 Nhập vào chuỗi a
	 * @param b				 Nhập vào chuỗi b
	 * @return boolean		 Trả về kết quả true/false
	 */
	public static boolean compareString(String a, String b) {
		// Khởi tạo biến check
		boolean check = false;
		// So sánh 2 chuỗi a và b
		if (a.equals(b)) {
			// Nếu bằng nhau trả về kết quả true
			check = true;
			// Kết thúc hàm if
		}
		return check;
		// Kết thúc phương thứcs
	}

	/**
	 * Phương thức mã hóa mật khẩu
	 * 
	 * @param a				 Nhập vào chuỗi a
	 * @param b				 Nhập vào chuỗi b
	 * @return String		 Trả về 1 String đã được mã hóa
	 */
	public static String encryptPassword(String a, String b) {
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for SHA-1
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// Add password bytes to digest
			// Get the hash's bytes
			String passwordToHash = a + b;
			byte[] bytes = md.digest(passwordToHash.getBytes());
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Occured error in encryptPassword"+e.getMessage());
		}
		// Return result to method
		return generatedPassword;
	}

	/**
	 * Hàm kiểm tra đăng nhập
	 * 
	 * @return trả về giá trị true/false cho phương thức
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	/**
	 * Hàm kiểm tra đăng nhập
	 * 
	 * @param session					Truyền vào session để kiểm tra
	 * @return boolean					Trả về kết quả true/false
	 * @throws ClassNotFoundException	Ném ra ngoại lệ ClassNotFoundException
	 * @throws SQLException				Ném ra ngoại lệ SQLException
	 */
	public static boolean checkLogin(HttpSession session) throws ClassNotFoundException, SQLException, NullPointerException {
		// Khởi tạo biến check
		boolean check = false;
		// Khởi tạo đối tượng TblUserDao
		TblUserDao userDao = new TblUserDaoImpl();
		// Mở try
		try {
			// Kiểm tra xem session có tồn tại hay không
			if (session != null) {
				// Khởi tạo đối tượng TblUserEntity
				TblUserEntity user = new TblUserEntity();
				// Lấy userName từ session
				String userName = session.getAttribute("loginName").toString();
				// Gán giá trị cho đối tượng user
				user = userDao.getTblUserByUserName(userName);
				// Kiểm tra nếu user khác null
				if (user != null) {
					// Gán giá trị check bằng true
					check = true;
				} else {
					// Gán giá trị check bằng false
					check = false;
				}
			}
		// Bắt lỗi
		} catch (NullPointerException | ClassNotFoundException | SQLException e) {
			System.out.println("Occured error in checkLogin/Common " + e.getMessage());
		}
		// Trả về kết quả cho phương thức
		return check;
	}


	/**
	 * Hàm thay thế các ký tự đặc biệt
	 * 
	 * @param a				Truyền vào String
	 * @return String		Trả về một String đã được thay thế
	 */
	public static String replaceWildCard(String a) {
		if (a != null) {
			a = a.replace("%", "\\%").replace("_", "\\_");
		}
		return a;
	}

	/**
	 * Hàm lấy ra tổng số trang
	 * 
	 * @param totalUser 			Tổng số user được truyền vào
	 * @param limit     			Số record trên 1 page
	 * @return int 					Trả về tổng số page
	 */
	public static int getTotalPage(int totalUser, int limit) {
		// Khởi tạo biến kiểu double
		double x = (double) totalUser / limit;
		// Làm tròn biến x là ra tổng số trang
		int totalPage = (int) Math.ceil(x);
		// Trả về tổng số trang
		return totalPage;
	}

	/**
	 * Hàm lấy ra list paging
	 * 
	 * @param totalUser 				Nhập vào tổng số user totalUser
	 * @param limit 					Nhập vào giới hạn bản ghi trên 1 trang limit
	 * @param currentPage 				Nhập vào page hiện tại currentPage
	 * @return ArrayList<Integer>		Trả về một list
	 */
	public static ArrayList<Integer> getListPaging(int totalUser, int limit, int currentPage) {
		// Khai tạo đối tượng listPaging
		ArrayList<Integer> listPaging = new ArrayList<Integer>();
		// Gọi hàm tính tổng số page
		int totalPages = getTotalPage(totalUser, limit);
		// Khởi tạo biến đếm i
		int i = 0;
		// Tính toán để trả về số paging
		while (i < Constant.LIMIT_PAGE) {
			i++;
			int pageAddList = i + (((currentPage - 1) / Constant.LIMIT_PAGE) * Constant.LIMIT_PAGE);
			// Nếu page thêm vào lớn hơn tổng số page thì dừng lại
			if (pageAddList > totalPages) {
				break;
			}
			listPaging.add(pageAddList);
		}

		return listPaging;

	}


	/**
	 * Hàm lấy vị trí bản ghi bắt đầu hiển thị
	 * 
	 * @param currentPage			Truyền vào trang hiện tại
	 * @param limit					Truyền vào giới hạn bản ghi trên 1 trang
	 * @return int					Trả về vị trí bản ghi bắt đầu hiển thị
	 */
	public static int getOffSet(int currentPage, int limit) {
		return ((currentPage - 1) * limit);
	}

	/**
	 * Hàm lấy ra list các năm
	 * 
	 * @param beginYear				Truyền vào năm bắt đầu
	 * @param endYear				Truyền vào năm kết thúc
	 * @return List<Integer> 		Trả về một list các năm
	 */
	public static List<Integer> getListYear(int beginYear, int endYear) {
		// Khởi tạo một listYear với kiểu dữ liệu là Integer
		List<Integer> listYear = new ArrayList<Integer>();
		// Khởi chạy vòng lặp để thêm giá trị vào mảng listYear
		for (int i = beginYear; i <= endYear; i++) {
			// Thêm giá trị
			listYear.add(i);
		}
		// Trả về mảng giá trị listYear
		return listYear;

	}

	/**
	 * Hàm lấy về năm hiện tại
	 * 
	 * @return int		 Trả về năm hiện tại
	 */
	@SuppressWarnings("deprecation")
	public static int getYearNow() {
		// Khởi tạo hàm Date
		Date date = new Date();
		// Lấy giá trị cho năm hiện tại
		int year = date.getYear() + 1900;
		// Trả về năm hiện tại
		return year;
	}

	/**
	 * Hàm lấy về một list các tháng
	 * 
	 * @return List<Integer> 		 Trả về giá trị cho listMonth
	 */
	public static List<Integer> getListMonth() {
		// Khởi tạo một listMonth với kiểu dữ liệu là Integer
		List<Integer> listMonth = new ArrayList<Integer>();
		// Khởi chạy vòng lặp để thêm giá trị vào mảng listMonth
		for (int i = 1; i <= 12; i++) {
			// Thêm giá trị
			listMonth.add(i);
		}
		// Trả về mảng giá trị listMonth
		return listMonth;
	}

	/**
	 * Hàm lấy về một list số ngày của một tháng
	 * 
	 * @return List<Integer>		 Trả về một listDay
	 */
	public static List<Integer> getListDay() {
		// Khởi tạo một List Day
		List<Integer> listDay = new ArrayList<Integer>();
		// Khởi chạy vòng for để thêm giá trị vào listDay
		for (int i = 1; i <= 31; i++) {
			// Thêm giá trị cho listDay
			listDay.add(i);
		}
		// Trả về giá trị cho listDay
		return listDay;
	}

	/**
	 * Hàm kiểm tra năm nhuận
	 * 
	 * @param year				 Nhập vào một năm bất kỳ
	 * @return boolean 			 Trả về kết quả True/False
	 */
	public static boolean checkLeapYear(int year) {
		// Khởi tạo biến check
		boolean check = false;
		// Kiểm tra điều kiện là năm nhuận
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100) != 0)) {
			// Gán giá trị true cho biến check
			check = true;
		}
		// Trả về kết quả cho hàm checkLeapYear
		return check;

	}

	/**
	 * Hàm lấy về giá trị năm, tháng, ngày hiện tại
	 * 
	 * @return List<Integer>		 Trả về listDateNow
	 */
	@SuppressWarnings("deprecation")
	public static List<Integer> getDateNow() {
		// Khởi tạo listDateNow
		List<Integer> listDateNow = new ArrayList<Integer>();
		Date dNow = new Date();
		listDateNow.add(dNow.getYear()+1900);
		listDateNow.add(dNow.getMonth()+1);
		listDateNow.add(dNow.getDate());
		// Trả về listDateNow
		return listDateNow;
	}

	/**
	 * Hàm chuyển đổi chuỗi sang dạng int
	 * @param time				Truyền vào string dạng ngày tháng yyyy/MM/dd hoặc yyyy-MM-dd
	 * @return List<Integer>	Trả về list giá trị kiểu int
	 */
	public static List<Integer> convertStringToListInteger(String time) {
		// Khởi tạo list giá trị int
		List<Integer> list = new ArrayList<Integer>();
		// Khởi tạo mảng giá trị string gồm 3 phần tử
		String[] text = new String[3];
		// Sử dụng vòng lặp for để tách chuỗi
		for (int i = 0; i < time.length(); i++) {
			if (time.contains("/")) {
				text = time.split("/");
			} else if (time.contains("-")) {
				text = time.split("-");
			}
		}
		// Sử dụng vòng lặp for để ép kiểu string sang int
		for (int i = 0; i < text.length; i++) {
			list.add(Integer.parseInt(text[i]));
		}
		// Trả về list giá trị cho phương thức
		return list;
	}

	/**
	 * Hàm so sánh ngày tháng
	 * 
	 * @param first					Truyền vào ngày thứ nhất
	 * @param second				Truyền vào ngày thứ hai
	 * @return boolean				Trả về kết quả true/false
	 */
	public static boolean compareDate(Date first, Date second) {
		// Khởi tạo biến check
		boolean check = false;
		// Khởi tạo giá trị so sánh compare
		int compare = first.compareTo(second);
		// Nếu compare = 0 thì trả về giá trị true
		if (compare == 0) {
			// Trả về giá trị true
			return check = true;
		}
		// Trả về kết quả cho phương thức
		return check;
	}

	/**
	 * Hàm lấy key cho session
	 * 
	 * @return String		 Trả về một string
	 */
	public static String getKey() {
		// Khởi tạo biến time
		String time = "";
		// Khởi tạo đối tượng Calendar
		Calendar cal = Calendar.getInstance();
		// Lấy giá trị salt là đến milisecond
		long salt = cal.getTimeInMillis();
		// Gán vào chuỗi time
		time += salt;
		// Trả kết quả về cho phương thức
		return time;
	}

	/**
	 * Hàm trả về một TblUserEntity
	 * 
	 * @param user 					Truyền vào một UserInfor
	 * @return TblUserEntity		Trả về một TblUserEntity
	 */
	public static TblUserEntity getTblUserByUserInfor(UserInforEntity user) {
		// Khởi tạo đối tượng TblUserEntity
		TblUserEntity u = new TblUserEntity();
		
		// Gán các giá trị cho đối tượng TblUserEntity
		u.setUserId(user.getUserId());
		u.setLoginName(user.getLoginName());
		u.setGroupId(user.getGroupId());
		u.setFullName(user.getFullName());
		u.setFullNameKana(user.getFullNameKana());
		u.setEmail(user.getEmail());
		u.setTel(user.getTel());
		u.setBirthday(user.getBirthday());
		u.setRule(Constant.RULE_USER);
		u.setSalt(Common.getKey());
		u.setPassword(Common.encryptPassword(user.getPassword(), u.getSalt()));
		
		// Trả về đối tượng TblUserEntity cho phương thức
		return u;
	}

	/**
	 * Hàm lấy về một TblDetailUserJapanEntity từ UserInfor
	 * 
	 * @param user 							Truyền vào một UserInfor
	 * @return TblDetailUserJapanEntity		Trả về một TblDetailUserJapanEntity
	 */
	public static TblDetailUserJapanEntity getTblDetailByUserInfor(UserInforEntity user) {
		// Khởi tạo đối tượng TblDetailUserJapanEntity
		TblDetailUserJapanEntity u = new TblDetailUserJapanEntity();
		
		// Truyền vào các thuộc tính cho đối tượng TblDetailUserJapanEntity
		u.setUserId(user.getUserId());
		u.setCodeLevel(user.getCodeLevel());
		u.setDetailUserJapanId(user.getDetailUserJapanId());
		u.setEndDate(user.getEndDate());
		u.setStartDate(user.getStartDate());
		u.setTotal(user.getTotal());
	
		// Trả TblDetailUserJapanEntity cho phương thức
		return u;
	}

	/**
	 * Hàm kiểm tra trình độ tiếng Nhật
	 * 
	 * @param u			 Truyền vào một TblDetailUserJapanEntity
	 * @return boolean 	 Trả về true/false
	 */
	public static boolean checkJapanLevel(TblDetailUserJapanEntity u) {
		// Kiểm tra xem trình độ tiếng Nhật của user có tồn tại
		if (u.getCodeLevel() == null) {
			// Trả về false
			return false;
		}
		// Trả về true
		return true;
	}

	/**
	 * Hàm chuyển đổi từ String sang dạng sql.Date để chèn vào DB
	 * 
	 * @param text				 	Nhập vào một chuỗi String
	 * @return java.sql.Date		Trả về kết quả dạng Date
	 * @throws ParseException	 	Ném ra lỗi ParseException
	 */
	public static java.sql.Date convertStringToDate(String text) throws ParseException {
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		// Set Lenient về False để bắt buộc kiểu Date đúng với định dạng được chọn
		date.setLenient(false);
		// Mở try
		try {
			Date javaDate = date.parse(text);
			java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
			return sqlDate;
		}
		// Bắt lỗi
		catch (ParseException e) {
			System.out.println("Occured error when you were in Convert Times"+e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Hàm lấy ra Group Id
	 * @param id			Truyền vào một mã id
	 * @return String		Trả về GroupName tương ứng
	 */
	public static String getGroupName(int id) {
		// Khởi tạo giá trị id
		String name = "";
		// Mở try
		try {
			// Khởi tọa MstGroupLogic
			MstGroupLogic g = new MstGroupLogicImpl();
			// Khởi tạo và gán giá trị cho list đối tượng MstGroupEntity
			List<MstGroupEntity> list = g.getAllMstGroup();
			// Sử dụng vòng for để tìm ra group id 
			for (int i = 0; i < list.size(); i++) {
				// Kiểm tra điều kiện
				if (id == (i + 1)) {
					// Gán giá trị cho id thỏa mãn
					name = list.get(i).getGroupName();
				}
			}
		
		// Bắt lỗi
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Occured error in getGroupId/Common"+e.getMessage());
		}
		// Trả về giá trị id cho phương thức
		return name;
	}
	
	/**
	 * Hàm lấy về Code Level
	 * @param text			Truyền vào chuỗi
	 * @return String		Trả về một Code Level
	 */
	public static String getNameLevel(String text) {
		// Khởi tạo String name
		String nameLevel = null;
		// Mở try
		try {
			// Khởi tạo đối tượng MstJapanLogic
			MstJapanLogic mstJapan = new MstJapanLogicImpl();
			// Khởi tạo list đối tượng MstJapanEntity
			ArrayList<MstJapanEntity> listMstJapan = mstJapan.getAllMstJapan();
			// Khởi chạy vòng lặp for để lấy Code Level
			for (int i = 0; i < listMstJapan.size(); i++) {
				// Kiểm tra điều kiện thỏa mãn
				if (text.contentEquals(listMstJapan.get(i).getCodeLevel())) {
					// Gán giá trị code level
					nameLevel = listMstJapan.get(i).getNameLevel();
				}
			}
		// Bắt lỗi
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Occured error in getCodeLevel/Common");
		}
		// Trả về codeLevel cho phương thức
		return nameLevel;
	}

	/**
	 * Hàm chuyển đổi String sang int
	 * @param id			Truyền vào chuỗi
	 * @param def			Truyền vào giá trị default
	 * @return int 			Trả về giá trị int
	 */
	public static int convertStringToInt(String id, int def) {
		// Gán giá trị default cho biến i
		int i = def;
		// Mở try
		try {
			// Ép kiểu từ string sang int
			int a = Integer.parseInt(id);
			// Gán giá trị lại cho i
			i = a;
		// Bắt lỗi
		} catch (Exception e) {
			System.out.println("Occured error in convertStringToInt/Common");
		}
		// Trả về giá trị cho phương thức
		return i;
	}

	/**
	 * Hàm lấy về các giá trị hiển thị cho các ô selectbox
	 * 
	 * @param request					Truyền vào request để nhận dữ liệu từ client
	 * @throws ClassNotFoundException	Ném ra ngoại lệ ClassNotFoundException
	 * @throws SQLException				Ném ra ngoại lệ SQLException	
	 */
	public static void setDataLogic(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		// Khởi tạo các đối tượng mstGroup, mstJapan
		MstGroupLogic mstGroup = new MstGroupLogicImpl();
		MstJapanLogic mstJapan = new MstJapanLogicImpl();

		// Khởi tạo các listMstGroup, listMstGroupName
		ArrayList<MstGroupEntity> listMstGroup = mstGroup.getAllMstGroup();
		// Khởi tạo listMstJapan
		ArrayList<MstJapanEntity> listMstJapan = mstJapan.getAllMstJapan();

		// Khởi tạo và lấy list các giá trị cho các ô selectbox Năm/Tháng/Ngày
		ArrayList<Integer> listYearEndDate = (ArrayList<Integer>) Common.getListYear(Constant.YEAR_BEGINING,
				Constant.YEAR_ENDDATE);
		ArrayList<Integer> listYear = (ArrayList<Integer>) Common.getListYear(Constant.YEAR_BEGINING, Constant.YEAR_END);
		ArrayList<Integer> listMonth = (ArrayList<Integer>) Common.getListMonth();
		ArrayList<Integer> listDay = (ArrayList<Integer>) Common.getListDay();

		// Gán các list lên request
		request.setAttribute("year", listYear);
		request.setAttribute("listMstGroup", listMstGroup);
		request.setAttribute("listMstJapan", listMstJapan);
		request.setAttribute("listYear", listYearEndDate);
		request.setAttribute("listMonth", listMonth);
		request.setAttribute("listDay", listDay);

	}
	
}