/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [Common.java], [Jul 11, 2020] [Kiều Văn Quang]
 */
package manager.common;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Kiều Văn Quang
 *
 */
public class Common {
	public int getAllItems(String name) {

		return 0;
	}

	public static int getTotalPage(int totalItems, int limit) {
		// Khởi tạo biến kiểu double
		double x = (double) totalItems / limit;
		// Làm tròn biến x là ra tổng số trang
		int totalPage = (int) Math.ceil(x);
		// Trả về tổng số trang
		return totalPage;
	}

	public static ArrayList<Integer> getListPaging(int totalItems, int limit, int currentPage) {
		// Khai tạo đối tượng listPaging
		ArrayList<Integer> listPaging = new ArrayList<Integer>();
		// Gọi hàm tính tổng số page
		int totalPages = getTotalPage(totalItems, limit);
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

	public static int getOffSet(int currentPage, int limit) {
		return ((currentPage - 1) * limit);
	}

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
			System.out.println("Occured error in encryptPassword" + e.getMessage());
		}
		// Return result to method
		return generatedPassword;
	}

	public static String[] splitString(String a) {
		String[] list = new String[2];
		for (int i = 0; i < list.length; i++) {
			list = a.split(";");
		}
		return list;
	}

	public static BigDecimal round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd;
	}

	public static boolean compareString(String a, String b) {
		boolean check = false;
		if (a.equals(b)) {
			check = true;
		}
		return check;
	}

	public static String getSalt() {
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

	public static void main(String[] args) {
		System.out.println(encryptPassword("admin123", "abcxyz1"));
	}
}
