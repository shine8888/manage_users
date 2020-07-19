/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [DeleteUserInforController.java], [April 8, 2020] [Kiều Văn Quang]
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.logics.TblDetailUserJapanLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.TblDetailUserJapanLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Class DeleteUserInforController dùng để xử lý việc xóa user trong database
 * 
 * @author Kiều Văn Quang
 *
 */
@WebServlet(name = "DeleteUserInfor.do", urlPatterns = { "/DeleteUserInfor.do" })
public class DeleteUserInforController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Phương thức doPost
	 * 
	 * @param request  				Dùng để lấy thông tin từ phía client gửi về server
	 * @param response 				Dùng để trả dữ liệu từ server cho client
	 * @throws ServletException	 	Ngoại lệ Servlet
	 * @throws IOException      	Ngoại lệ đọc ghi file
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Khởi tạo các đối tượng TblUserLogic và TblDetailJapanLogic
		TblUserLogic tl = new TblUserLogicImpl();
		TblDetailUserJapanLogic td = new TblDetailUserJapanLogicImpl();

		// Lấy id từ trên request
		String index = request.getParameter("id");
		// Chuyển id lấy được từ string sang kiểu int
		int id = Common.convertStringToInt(index, 0);

		// Kiểm tra nếu id bằng 0
		if (id <= 0) {
			response.sendRedirect(request.getContextPath() + Constant.URL_SYSTEM_ERROR_CONTROLLER + "?type="
					+ Constant.DELETE + "&code="+Constant.ER013);
		} else {
			// Mở try
			try {
				// Khởi tạo và lấy giá trị cho biến check
				int check = tl.checkExistToDeleteUser(id);

				// Kiểm tra nếu biến check có giá trị trả về là rule admin
				if (check == 0) {
					// Chuyển sang SystemErrorController cùng với mã lỗi ER020
					response.sendRedirect(request.getContextPath() + Constant.URL_SYSTEM_ERROR_CONTROLLER + "?type="
							+ Constant.DELETE + "&code="+Constant.ER020);
					// Kiểm tra nếu biến check có giá trị trả về là rule user
				} else if (check == 1) {
					// Kiểm tra tồn tại của trình độ tiếng Nhật của user
					boolean check1 = td.checkExistTblDetailUserJapan(id);

					// Kiểm tra điều kiện khi xóa user
					if (tl.deleteUserInfor(id, check1)) {
						// Thành công thì chuyển qua SuccessController cùng type= delete
						response.sendRedirect(request.getContextPath() + Constant.URL_SUCCESS_CONTROLLER + "?type="
								+ Constant.DELETE);
						// Nếu xóa không thành công
					} else {
						// Chuyển sang SystemErrorController cùng với mã lỗi ER014
						response.sendRedirect(request.getContextPath() + Constant.URL_SYSTEM_ERROR_CONTROLLER + "?type="
								+ Constant.DELETE + "&code="+Constant.ER014);
					}
					// Kiểm tra nếu ai đó vào trong DB đổi rule của user khác cả 0 và 1
				} else {
					// Chuyển sang SystemErrorController cùng với mã lỗi ER013
					response.sendRedirect(request.getContextPath() + Constant.URL_SYSTEM_ERROR_CONTROLLER + "?type="
							+ Constant.DELETE + "&code="+Constant.ER013);
				}
				// Bắt lỗi
			} catch (Exception e) {
				// Thông báo lỗi
				String methodName = new Object() {
				}.getClass().getEnclosingMethod().getName();
				String className = this.getClass().getName();
				System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			}
		}
	}

}
