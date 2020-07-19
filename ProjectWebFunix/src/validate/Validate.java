/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [Validate.java], [Jul 16, 2020] [Kiều Văn Quang]
 */
package validate;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import manager.common.Constant;
import manager.logic.ManagerLogic;
import properties.MessageProperties;

/**
 * @author Kiều Văn Quang
 *
 */
public class Validate {

	public static List<String> listError = new ArrayList<String>();
	public static ManagerLogic ml = new ManagerLogic();

	public static List<String> checkLogin(String username, String password) {
		// Kiểm tra xem username có phải chuỗi rỗng hay không
		if ("".equals(username)) {
			// Thêm message lỗi ER00101 trong file error.properties vào messError
			listError.add(MessageProperties.getValueByKey(Constant.ER001_LOGIN_NAME));
		}
		// Kiểm tra xem password có phải chuỗi rỗng hay không
		if ("".equals(password)) {
			// Thêm message lỗi ER00102 trong file error.properties vào messError
			listError.add(MessageProperties.getValueByKey(Constant.ER001_PASSWORD));
		}
		// Kiểm tra size của list lỗi
		if (listError.size() == 0) {
			// Kiểm tra nếu có tài khoản trong database
			if (ml.checkExistAccount(username, password)) {
				// Set session cho user
				listError.add(MessageProperties.getValueByKey(Constant.ER005_LOGIN_NAME));
			}
		}
		// Trả về danh sách lỗi
		return listError;
	}
	
	
	
	
	public static List<String> validateLogin(String userName, int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo đối tượng ManagerLogic
		ManagerLogic ml = new ManagerLogic();
		// Khởi tạo list lỗi
		List<String> listError = new ArrayList<String>();
		// Kiểm tra các điều kiện và thêm lỗi vào listError nếu có
		if (!checkER001(userName)) {
			listError.add(MessageProperties.getValueByKey(Constant.ER001_LOGIN_NAME));
		} else {
			if (!(Pattern.compile("^[a-zA-Z]+[a-zA-Z0-9]").matcher(userName).find())) {
				listError.add(MessageProperties.getValueByKey(Constant.ER019_LOGIN_NAME));
			} else {
				if (userName.length() < 4 || userName.length() > 15) {
					listError.add(MessageProperties.getValueByKey(Constant.ER006_LOGIN_NAME));
				} else {
					if (ml.checkExistAccount(userName, id)) {
						listError.add(MessageProperties.getValueByKey(Constant.ER003_LOGIN_NAME));
					}
				}
			}
		}
		// Trả về listError
		return listError;
	}
	
	
	
	public static List<String> validateEmail(String email, int id) throws ClassNotFoundException, SQLException {
		// Khởi tạo listError và đối tượng TblUserLogic
		List<String> listError = new ArrayList<String>();
		ManagerLogic ml = new ManagerLogic();
		// Kiểm tra điều kiện và thêm lỗi vào list nếu có
		if (!checkER001(email)) {
			listError.add(MessageProperties.getValueByKey(Constant.ER001_EMAIL));
		} else {
			if (email.length() > 100) {
				listError.add(MessageProperties.getValueByKey(Constant.ER006_EMAIL));
			} else {
				if (!checkFormatEmail(email)) {
					listError.add(MessageProperties.getValueByKey(Constant.ER005_EMAIL));
				} else {
					if (id != 0 && ml.checkExistEmail(email, id)) {
					} else if (id == 0 && ml.checkExistEmail(email, id)) {
						listError.add(MessageProperties.getValueByKey(Constant.ER003_EMAIL));
					}
				}
			}
		}
		// Trả về list lỗi
		return listError;
	}
	

	public static List<String> validateTel(String tel) {
		// Khởi tạo list lỗi
		List<String> listError = new ArrayList<String>();
		// Kiểm tra điều kiện và thêm lỗi vào listError nếu có
		if (!checkER001(tel)) {
			listError.add(MessageProperties.getValueByKey(Constant.ER001_PHONE));
		} else {
			if (tel.length() > 15) {
				listError.add(MessageProperties.getValueByKey(Constant.ER006_PHONE));
			} else {
				if (!checkFormatTel(tel)) {
					listError.add(MessageProperties.getValueByKey(Constant.ER005_PHONE));
				}
			}
		}
		// Trả về list lỗi
		return listError;

	}
	
	public static List<String> validateAddress(String address){
		List<String> listError = new ArrayList<String>();
		if(!checkER001(address)) {
			listError.add(MessageProperties.getValueByKey(Constant.ER001_ADDRESS));
		}
		return listError;
	}
	
	
	public static List<String> validatePassword(String pass) throws UnsupportedEncodingException {
		List<String> listError = new ArrayList<String>();
		if (!checkER001(pass)) {
			listError.add(MessageProperties.getValueByKey(Constant.ER001_PASSWORD));
		} else {
			if (pass.length() < 5 || pass.length() > 20) {
				listError.add(MessageProperties.getValueByKey(Constant.ER006_PASSWORD));
			}
		}

		// Trả về list lỗi
		return listError;

	}

	public static List<String> validateConfirmPassword(String pass, String confirm) {
		// Khởi tạo list lỗi
		List<String> listError = new ArrayList<String>();
		// Kiểm tra điều kiện và thêm lỗi vào listError nếu có
		if (!checkER017(pass, confirm)) {
			listError.add(MessageProperties.getValueByKey(Constant.ER017_CONFIRM_PASSWORD));
		}

		// Trả về list lỗi
		return listError;
	}

	public static boolean checkER001(String text) {
		// Kiểm tra điều kiện là chuỗi rỗng hoặc null
		if (text == null || "".equals(text)) {
			// Trả về false
			return false;
		}
		// Trả về true
		return true;
	}

	public static boolean checkER006(String text) {
		// Kiểm tra độ dài chuỗi có lớn hơn 255
		if (text.length() > 255) {
			// Trả về false
			return false;
		}
		// Trả về true
		return true;
	}

	public static boolean checkER018(String text) {
		// Trả về cho giá trị nếu text chứa các ký tự half-size từ 0-9
		return Pattern.matches("[0-9]+", text);
	}

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
