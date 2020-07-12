/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [Common.java], [Jul 11, 2020] [Kiều Văn Quang]
 */
package manager.common;

import java.util.ArrayList;


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
	
	
}
