/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [ValidateUser.java.java], [Feb 24, 2020] [Kiều Văn Quang]
 */
package manageuser.validates;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import manageuser.entities.MstGroupEntity;
import manageuser.entities.MstJapanEntity;
import manageuser.entities.UserInforEntity;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.MstJapanLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Constant;
import manageuser.utils.MessageErrorProperties;

/**
 * @author Kieu Van Quang
 *
 */
public class Validate {
	// Khởi tạo thuộc tính ListError
	public static List<String> listError = new ArrayList<String>();
	
	
	/**
	 * Phương thức validate các trường nhập vào
	 * 
	 * @param Truyền vào một user
	 * @return Trả về một List Lỗi
	 */
	public List<String> validateUser(UserInforEntity user, String type) {
		// Khai báo List chứa các danh sách lỗi
		List<String> listErrorLoginName = new ArrayList<String>();
		// Khai báo List chứa các danh sách lỗi
		List<String> listErrorPassword = new ArrayList<String>();
		// Khai báo List chứa các danh sách lỗi
		List<String> listErrorConfirmPassword = new ArrayList<String>();
		try {
			if (type.equals(Constant.ADD_TYPE)) {
				// Khai báo List chứa các danh sách lỗi
				listErrorLoginName = validateLogin(user.getLoginName(), user.getUserId());
				// Khai báo List chứa các danh sách lỗi
				listErrorPassword = validatePassword(user.getPassword());
				// Khai báo List chứa các danh sách lỗi
				listErrorConfirmPassword = validateConfirmPassword(user.getPassword(),
						user.getConfirmPassword());
			}
			// Khai báo List chứa các danh sách lỗi
			List<String> listErrorEmail = validateEmail(user.getEmail(), user.getUserId());
			// Khai báo List chứa các danh sách lỗi
			List<String> listErrorGroupName = validateGroupName(user.getGroupName());
			// Khai báo List chứa các danh sách lỗi
			List<String> listErrorFullName = validateFullName(user.getFullName());
			// Khai báo List chứa các danh sách lỗi
			List<String> listErrorFullNameKana = validateFullNameKana(user.getFullNameKana());
			// Khai báo List chứa các danh sách lỗi
			List<String> listErrorBirthday = validateBirthday(user.getListTime().get(0));
			// Khai báo List chứa các danh sách lỗi
			List<String> listErrorTel = validateTel(user.getTel());

			// Validate phần trình độ tiếng Nhật
			List<String> listErrorCodeLevel = new ArrayList<String>();
			List<String> listErrorStartDate = new ArrayList<String>();
			List<String> listErrorEndDate = new ArrayList<String>();
			List<String> listErrorTotal = new ArrayList<String>();
			if ( !user.getCodeLevel().equals(Constant.N0)) {
				// Khai báo List chứa các danh sách lỗi
				listErrorCodeLevel = validateJapaneseLevel(user.getCodeLevel());
				// Khai báo List chứa các danh sách lỗi
				listErrorStartDate = validateStartingDateLevel(user.getListTime().get(1));
				// Khai báo List chứa các danh sách lỗi
				listErrorEndDate = validateEndingDateLevel(user.getListTime().get(1),
						user.getListTime().get(2));
				// Khai báo List chứa các danh sách lỗi
				listErrorTotal = validateTotal(user.getScore());
			} else {
				if (!"".contentEquals(user.getScore())) {
					listErrorCodeLevel = validateJapaneseLevel(user.getCodeLevel());
					// Khai báo List chứa các danh sách lỗi
					listErrorStartDate = validateStartingDateLevel(user.getListTime().get(1));
					// Khai báo List chứa các danh sách lỗi
					listErrorEndDate = validateEndingDateLevel(user.getListTime().get(1),
							user.getListTime().get(2));
					// Khai báo List chứa các danh sách lỗi
					listErrorTotal = validateTotal(user.getScore());
				} else {
					user.setNameLevel(null);
				}
			}
			// Gán các giá trị cho mảng lỗi. Sau đó remove giá trị ở các list
			if (!listErrorLoginName.isEmpty()) {
				listError.add(listErrorLoginName.get(0));
			}
			if (!listErrorGroupName.isEmpty()) {
				listError.add(listErrorGroupName.get(0));
			}

			if (!listErrorFullName.isEmpty()) {
				listError.add(listErrorFullName.get(0));
			}
			if (!listErrorFullNameKana.isEmpty()) {
				listError.add(listErrorFullNameKana.get(0));
			}
			if (!listErrorBirthday.isEmpty()) {
				listError.add(listErrorBirthday.get(0));
			}
			if (!listErrorEmail.isEmpty()) {
				listError.add(listErrorEmail.get(0));
			}
			if (!listErrorTel.isEmpty()) {
				listError.add(listErrorTel.get(0));
			}
			if (!listErrorPassword.isEmpty()) {
				listError.add(listErrorPassword.get(0));
			}
			if (!listErrorConfirmPassword.isEmpty()) {
				listError.add(listErrorConfirmPassword.get(0));
			}
			if (!listErrorCodeLevel.isEmpty()) {
				listError.add(listErrorCodeLevel.get(0));
			}
			if (!listErrorStartDate.isEmpty()) {
				listError.add(listErrorStartDate.get(0));
			}
			if (!listErrorEndDate.isEmpty()) {
				listError.add(listErrorEndDate.get(0));
			}
			if (!listErrorTotal.isEmpty()) {
				listError.add(listErrorTotal.get(0));
			}
			// Bắt lỗi
		} catch (Exception e) {
			System.out.println("Ocurred error when you were in ValidateUser/AddUserInputController");
		}
		// Trả về listError chứa các lỗi
		return listError;
	}
	
	
	/**
	 * Hàm kiểm tra lỗi khi đăng nhập
	 * 
	 * @param username 					Truyền vào username
	 * @param password 					Truyền vào password
	 * @return ArrayList<String>	  	Trả về một danh sách chứa các lỗi
	 */
	public static List<String> checkLogin(String username, String password) {
		// Khởi tạo đối tượng user logic
		TblUserLogic userLogic = new TblUserLogicImpl();
		// Kiểm tra xem username có phải chuỗi rỗng hay không
		if ("".equals(username)) {
			// Thêm message lỗi ER00101 trong file error.properties vào messError
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER001_LOGIN_NAME));
		}
		// Kiểm tra xem password có phải chuỗi rỗng hay không
		if ("".equals(password)) {
			// Thêm message lỗi ER00102 trong file error.properties vào messError
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER001_PASSWORD));
		}
		// Kiểm tra size của list lỗi
		if (listError.size() == 0) {
			// Kiểm tra nếu có tài khoản trong database
			if (!userLogic.checkExistLoginId(username, password)) {
				// Set session cho user
				listError.add(MessageErrorProperties.getValueByKey(Constant.ER016));
			}
		}
		// Trả về danh sách lỗi
		return listError;
	}	
	
	
	/**
	 * Hàm Validate Login Name
	 * 
	 * @param userName						Truyền vào userName
	 * @return List<String>					Trả về một List String là lỗi
	 * @throws ClassNotFoundException		Ném ra một ngoại lệ ClassNotFoundException
	 * @throws SQLException					Ném ra một ngoại lệ SQLException	
	 */
	public static List<String> validateLogin(String userName, int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo đối tượng TblUserLogic
		TblUserLogic tblLogic = new TblUserLogicImpl();
		// Khởi tạo list lỗi
		List<String> listError = new ArrayList<String>();
		// Kiểm tra các điều kiện và thêm lỗi vào listError nếu có
		if (!checkER001(userName)) {
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER001_LOGIN_NAME));
		} else {
			if (!(Pattern.compile("^[a-zA-Z]+[a-zA-Z0-9]").matcher(userName).find())) {
				listError.add(MessageErrorProperties.getValueByKey(Constant.ER019_LOGIN_NAME));
			} else {
				if (userName.length() < 4 || userName.length() > 15) {
					listError.add(MessageErrorProperties.getValueByKey(Constant.ER007_LOGIN_NAME));
				} else {
					if (tblLogic.checkExistLoginName(userName, id)) {
						listError.add(MessageErrorProperties.getValueByKey(Constant.ER003_LOGIN_NAME));
					}
				}
			}
		}
		// Trả về listError
		return listError;
	}

	/**
	 * Hàm Validate GroupName
	 * 
	 * @param groupName					Truyền vào string groupName
	 * @return List<String>				Trả về một list lỗi
	 * @throws ClassNotFoundException	Ném ra một ngoại lệ ClassNotFoundException
	 * @throws SQLException				Ném ra một ngoại lệ SQLException
	 */
	public static List<String> validateGroupName(String groupName) throws ClassNotFoundException, SQLException {
		// Khởi tạo đối tượng MstGroupLogic
		MstGroupLogic mstGroup = new MstGroupLogicImpl();
		// Khởi tạo listMstGroup, listName
		ArrayList<MstGroupEntity> listMstGroup = mstGroup.getAllMstGroup();
		ArrayList<String> listName = new ArrayList<String>();
		// Dùng vòng lặp for dể thêm tê nhóm vào list
		for (int i = 0; i < listMstGroup.size(); i++) {
			listName.add(listMstGroup.get(i).getGroupName());
		}
		// Khởi tạo list lỗi
		List<String> listError = new ArrayList<String>();
		// Kiểm tra điều kiện và thêm lỗi vào listError nếu có
		if (!checkER002(groupName)) {
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER002_GROUP_NAME));
		} else {
			if (!listName.contains(groupName)) {
				listError.add(MessageErrorProperties.getValueByKey(Constant.ER004_GROUP_NAME));
			}
		}
		// Trả về list lỗi
		return listError;
	}

	/**
	 * Hàm ValidateFullName
	 * 
	 * @param fullName			Truyền vào fullName
	 * @return List<String>		Trả về list lỗi
	 */
	public static List<String> validateFullName(String fullName) {
		// Khởi tạo listError
		List<String> listError = new ArrayList<String>();
		// Kiểm tra điều kiện và thêm lỗi vào listError nếu có
		if (!checkER001(fullName)) {
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER001_FULL_NAME));
		} else {
			if (!checkER006(fullName)) {
				listError.add(MessageErrorProperties.getValueByKey(Constant.ER006_FULL_NAME));
			}
		}
		// Trả về list lỗi
		return listError;
	}

	/**
	 * Hàm validateFullNameKana
	 * 
	 * @param fullNameKana			Truyền vào fullNameKana
	 * @return List<String> 		Trả về một list lỗi
	 */
	public static List<String> validateFullNameKana(String fullNameKana) {
		// Khởi tạo listError
		List<String> listError = new ArrayList<String>();
		// Kiểm tra điều kiện và thêm lỗi vào listError nếu có
		if (!checkER009(fullNameKana)) {
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER009_FULL_NAME_KANA));
		} else {
			if (!checkER006(fullNameKana)) {
				listError.add(MessageErrorProperties.getValueByKey(Constant.ER006_FULL_NAME_KANA));
			}
		}
		// Trả về list lỗi
		return listError;
	}
	
	/**
	 * Hàm ValidatteBirthday
	 * @param birthday			Truyền vào string dạng date
	 * @return List<String>		Trả về list lỗi
	 * @throws ParseException	Ném ra ngoại lệ ParseException
	 */
	public static List<String> validateBirthday(String birthday) throws ParseException {
		// Khởi tạo listError
		List<String> listError = new ArrayList<String>();
		// Mở try
		try {
			// Kiểm tra điều kiện và thêm lỗi vào list nếu có
			if (!checkER011(birthday)) {
				listError.add(MessageErrorProperties.getValueByKey(Constant.ER011_BIRTH_DAY));
			}
		// Bắt lỗi
		} catch (ParseException e) {
			throw e;
		}
		// Trả về list lỗi
		return listError;
	}
	
	/**
	 * Hàm ValidateEmail
	 * @param email						Truyền vào email
	 * @param id						Truyền vào id của user
	 * @return List<String>				Trả về list lỗi
	 * @throws ClassNotFoundException 	Ném ra ngoại lệ ClassNotFoundException
	 * @throws SQLException				Ném ra ngoại lệ SQLException
	 */
	public static List<String> validateEmail(String email, int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo listError và đối tượng TblUserLogic
		List<String> listError = new ArrayList<String>();
		TblUserLogic user = new TblUserLogicImpl();
		// Kiểm tra điều kiện và thêm lỗi vào list nếu có
		if (!checkER001(email)) {
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER001_EMAIL));
		} else {
			if (email.length() > 100) {
				listError.add(MessageErrorProperties.getValueByKey(Constant.ER006_EMAIL));
			} else {
				if (!checkFormatEmail(email)) {
					listError.add(MessageErrorProperties.getValueByKey(Constant.ER005_EMAIL));
				} else {
					if (id != 0 && user.checkExistEmail(email, id)) {
					} else if (id == 0 && user.checkExistEmail(email, id)) {
						listError.add(MessageErrorProperties.getValueByKey(Constant.ER003_EMAIL));
					}
				}
			}
		}
		// Trả về list lỗi
		return listError;
	}
	
	/**
	 * Hàm ValidateTel
	 * @param tel				Truyền vào chuỗi là số điện thoại
	 * @return List<String>		Trả về list lỗi		
	 */
	public static List<String> validateTel(String tel) {
		// Khởi tạo list lỗi
		List<String> listError = new ArrayList<String>();
		// Kiểm tra điều kiện và thêm lỗi vào listError nếu có
		if (!checkER001(tel)) {
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER001_TEL));
		} else {
			if (tel.length() > 14) {
				listError.add(MessageErrorProperties.getValueByKey(Constant.ER006_TEL));
			} else {
				if (!checkFormatTel(tel)) {
					listError.add(MessageErrorProperties.getValueByKey(Constant.ER005_TEL));
				}
			}
		}
		// Trả về list lỗi
		return listError;

	}
	
	/**
	 * Hàm ValidatePassword
	 * @param pass								Truyền vào password
	 * @return List<String>						Trả về list lỗi
	 * @throws UnsupportedEncodingException		Ném ra ngoại lệ UnsupportedEncodingException
	 */
	public static List<String> validatePassword(String pass) throws UnsupportedEncodingException {
		List<String> listError = new ArrayList<String>();

		if (!checkER001(pass)) {
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER001_PASSWORD));
		} else {
			if (!checkER008(pass)) {
				listError.add(MessageErrorProperties.getValueByKey(Constant.ER008_PASSWORD));
			} else {
				if (pass.length() < 5 || pass.length() > 15) {
					listError.add(MessageErrorProperties.getValueByKey(Constant.ER007_PASSWORD));
				}
			}
		}
		// Trả về list lỗi
		return listError;
	}
	
	/**
	 * Hàm ValidateConfirmPassword
	 * @param pass					Truyền vào password
	 * @param confirm				Truyền vào passwordconfirm
	 * @return List<String>			Trả về list lỗi
	 */
	public static List<String> validateConfirmPassword(String pass, String confirm) {
		// Khởi tạo list lỗi
		List<String> listError = new ArrayList<String>();
		// Kiểm tra điều  kiện và thêm lỗi vào listError nếu có
		if (!checkER017(pass, confirm)) {
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER017_CONFIRM_PASSWORD));
		}
		
		// Trả về list lỗi
		return listError;
	}
	
	/**
	 * Hàm ValidateJapaneseLevel
	 * @param text 						Truyên vào chuỗi 
	 * @return List<String>				Trả về list lỗi
	 * @throws ClassNotFoundException	Ném ra ngoại lệ ClassNotFoundException
	 * @throws SQLException				Ném ra ngoại lệ SQLException	
	 */
	public static List<String> validateJapaneseLevel(String text) throws ClassNotFoundException, SQLException {
		// Khởi tạo list lỗi và đối tượng MstJapanLogic
		List<String> listError = new ArrayList<String>();
		MstJapanLogic mstJapan = new MstJapanLogicImpl();
		// Khởi tạo listMstJapan
		ArrayList<MstJapanEntity> listMstJapan = mstJapan.getAllMstJapan();
		// Khởi tạo listName
		ArrayList<String> listName = new ArrayList<String>();
		// Dùng vòng lặp for để thêm name vào list
		for (int i = 0; i < listMstJapan.size(); i++) {
			listName.add(listMstJapan.get(i).getCodeLevel());
		}
		// Kiểm tra xem listName có chứa tên truyền vào hay không
		if (!listName.contains(text)) {
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER004_LEVEL_NAME));
		}
		// Trả về list lỗi
		return listError;
	}

	/**
	 * Hàm ValidateStartingDateLevel
	 * @param date 					Truyền vào String dạng Date
	 * @return List<String>			Trả về list lỗi
	 * @throws ParseException		Ném ra lỗi ParseException
	 */
	public static List<String> validateStartingDateLevel(String date) throws ParseException {
		// Khởi tạo list lỗi
		List<String> listError = new ArrayList<String>();
		// Kiểm tra điều kiện và thêm lỗi vào listError nếu có
		if (!checkER011(date)) {
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER011_START_DATE));
		}
		// Trả về list lỗi
		return listError;
	}
	
	/**
	 * Hàm ValidateEndingDateLevel
	 * @param startDate				Truyền vào startDate dạng String
	 * @param endDate				Truyền vào endDate dạng String
	 * @return List<String>			Trả về list lỗi
	 * @throws ParseException		Ném ra lỗi ParseException
	 */
	public static List<String> validateEndingDateLevel(String startDate, String endDate) throws ParseException {
		// Khởi tạo list lỗi
		List<String> listError = new ArrayList<String>();
		// Kiểm tra điều kiện và thêm lỗi vào listError nếu có
		if (!checkER011(startDate) || !checkER011(endDate)) {
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER011_END_DATE));
		} else {
			if (!checkER012(startDate, endDate)) {
				listError.add(MessageErrorProperties.getValueByKey(Constant.ER012_END_DATE));
			}
		}
		// Trả về list lỗi
		return listError;
	}
	
	/**
	 * Hàm ValidateTotal
	 * @param text				Truyền vào một string
	 * @return List<String> 	Trả về list lỗi
	 */
	public static List<String> validateTotal(String text) {
		// Khởi tạo list lỗi
		List<String> listError = new ArrayList<String>();
		// Kiểm tra điều kiện và thêm lỗi vào listError nếu có
		if (!checkER001(text)) {
			listError.add(MessageErrorProperties.getValueByKey(Constant.ER001_TOTAL));
		} else {
			if (!checkER018(text)) {
				listError.add(MessageErrorProperties.getValueByKey(Constant.ER018_TOTAL));
			} else {
				if (!checkER006(text)) {
					listError.add(MessageErrorProperties.getValueByKey(Constant.ER006_TOTAL));
				}
			}
		}
		// Trả về list lỗi
		return listError;
	}	
	
	/**
	 * Hàm check lỗi ER001
	 * 
	 * @param Nhập text vào để kiểm tra
	 * @return Trả về kết quả True/False
	 */
	public static boolean checkER001(String text) {
		// Kiểm tra điều kiện là chuỗi rỗng hoặc null
		if (text == null || "".equals(text)) {
			// Trả về false
			return false;
		}
		// Trả về true
		return true;
	}

	/**
	 * Hàm check lỗi ER002
	 * 
	 * @param Nhập text vào để kiểm tra
	 * @return Trả về kết quả True/False
	 */
	public static boolean checkER002(String text) {
		// Kiểm tra điều kiện là chuỗi rỗng hoặc null
		if ("0".equals(text) || text == null) {
			// Trả về false
			return false;
		}
		// Trả về true
		return true;
	}

	/**
	 * Hàm check lỗi ER006
	 * 
	 * @param Nhập text vào để kiểm tra
	 * @return Trả về kết quả True/False
	 */
	public static boolean checkER006(String text) {
		// Kiểm tra độ dài chuỗi có lớn hơn 255
		if (text.length() > 255) {
			// Trả về false
			return false;
		}
		// Trả về true
		return true;
	}

	/**
	 * Hàm kiểm tra lỗi ER009
	 * 
	 * @param Nhập vào text để kiểm tra xem có là ký tự Katana
	 * @return Trả về true/false
	 */
	public static boolean checkER009(String text) {
		// Khởi tạo dãy char
		char[] c = text.toCharArray();
		// Khởi chạy vòng lặp để kiểm tra điều kiện
		for (int i = 0; i < c.length; i++) {
			// Thực hiện kiểm tra điều kiện xem có là ký tự Katana hay không
			if ((Character.UnicodeBlock.of(c[i]) != Character.UnicodeBlock.KATAKANA) && (!isDigit(c[i]))
					&& (!Character.isWhitespace(c[i]))) {
				// Không phải thì trả về false
				return false;
			}
		}
		// Trả về true
		return true;
	}

	/**
	 * Hàm kiểm tra dạng format của date
	 * 
	 * @param Nhập vào text để kiểm tra
	 * @return Trả về kết quả true/false
	 */
	@SuppressWarnings({ "finally", "unused" })
	public static boolean checkER011(String text) throws ParseException {
		// Khởi tạo biến check
		boolean check = false;
		// Kiểm tra xem chuỗi có là rỗng hay không
		if (text.trim().equals("")) {
			// Trả về true nếu là rỗng
			return check;
		}
		// Nếu không rỗng thực hiện tiếp điều kiện ở else
		else {
			// Set kiểu dữ liệu cho date là Năm/Tháng/Ngày
			SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
			// Set Lenient về False để bắt buộc kiểu Date đúng với định dạng được chọn
			date.setLenient(false);
			// Bắt try / catch
			try {
				Date javaDate = date.parse(text);
				return check = true;
			}
			// Bắt lỗi
			catch (ParseException e) {
				System.out.println("Occured error when you were in checkER011");
				throw e;
			} finally {
				return check;
			}
		}
	}

	/**
	 * Hàm kiểm tra lỗi ER012
	 * 
	 * @param start
	 * @param end
	 * @return Trả về kết quả là true/false
	 * @throws ParseException
	 */
	@SuppressWarnings("finally")
	public static boolean checkER012(String start, String end) throws ParseException {
		// Khởi tạo biến check
		boolean check = false;
		// Set kiểu dữ liệu cho date là Năm/Tháng/Ngày
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		// Set Lenient về False để bắt buộc kiểu Date đúng với định dạng được chọn
		date.setLenient(false);
		try {
			// Khởi tạo và gán giá trị cho 2 biến startingDate và endingDate
			Date startingDate = date.parse(start);
			Date endingDate = date.parse(end);
			// Khởi tạo giá trị so sánh compare
			int compare = startingDate.compareTo(endingDate);
			// Nếu compare < 0 thì startingDate trước endingDate
			if (compare < 0) {
				// Trả về giá trị true
				return check = true;
			}
			// Bắt lỗi
		} catch (ParseException e) {
			// In lỗi ra console
			System.out.println("Occured error when you were in checkER012");
			// Ném lỗi đi
			throw e;
		} finally {
			// Trả về giá trị check
			return check;
		}
	}

	/**
	 * Hàm kiểm tra lỗi ER018
	 * 
	 * @param text
	 * @return Trả về kết quả true/false
	 */
	public static boolean checkER018(String text) {
		// Trả về cho giá trị nếu text chứa các ký tự half-size từ 0-9
		return Pattern.matches("[0-9]+", text);
	}

	/**
	 * Hàm kiểm tra ký tự
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isDigit(char c) {
		// Khởi tạo giá trị cho biến x
		int x = (int) c;
		// Kiểm tra điều kiện cho x
		if ((x >= 48) && (x <= 57)) {
			// Trả về true nếu đúng
			return true;
		}
		// Trả về false
		return false;
	}

	/**
	 * Hàm kiểm tra ký tự một byte - ER008
	 * 
	 * @param Truyền vào một text
	 * @return Trả về một kết quả true/false
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("finally")
	public static boolean checkER008(String text) throws UnsupportedEncodingException {
		// Khởi tạo biến check
		boolean check = true;
		// Bắt try
		try {
			// Khởi tạo biến đếm byteLength
			int byteLengh;
			// Khởi chạy vòng lặp for
			for (int i = 0; i < text.length(); i++) {
				// Gán giá trị cho byteLength
				byteLengh = (text.charAt(i) + "").getBytes("UTF8").length;
				// Kiểm tra nếu byteLeng khác 1
				if (byteLengh != 1) {
					// Trả về false
					check = false;
				}
			}
			// Bắt lỗi
		} catch (UnsupportedEncodingException e) {
			System.out.println("Occured error when you were in checkER008");
			// Ném lỗi đi
			throw e;
		} finally {
			// Trả về biến check
			return check;
		}

	}

	/**
	 * Hàm kiểm tra lỗi ER017
	 * 
	 * @param Nhập vào pass
	 * @param Nhập vào giá trị confirm
	 * @return Trả về tru/false
	 */
	public static boolean checkER017(String pass, String confirm) {
		// Kiểm tra xem confirm có bằng null hoặc rỗng không
		if (confirm == null || "".equals(confirm)) {
			return false;
		} else {
			// Kiểm tra điều kiện 2 chuỗi có giống nhau hay không
			if (pass.equals(confirm)) {
				// Đúng trả về true
				return true;
			}
		}
		// Trả về false
		return false;

	}

	/**
	 * Hàm check định dạng email
	 * 
	 * @param Nhập vào email
	 * @return Trả về kết quả true/false
	 * 
	 */
	public static boolean checkFormatEmail(String email) {
		// Kiểm tra điều kiện định dạng cho email bằng biểu thức chính quy
		if (!Pattern.matches("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{1,}\\.[\\w]{1,})$", email)) {
			// Trả về kết quả false nếu sai
			return false;
		}
		// Trả về kết quả đúng
		return true;
	}

	public static boolean checkFormatTel(String tel) {
		// Kiểm tra điều kiện định dạng cho số điện thoại bằng biểu thức chính quy
		if (!Pattern.matches("\\d{1,4}[-]\\d{1,4}[-]\\d{1,4}$", tel)) {
			// Trả về kết quả false nếu sai
			return false;
		}
		// Trả về kết quả đúng
		return true;
	}

}
