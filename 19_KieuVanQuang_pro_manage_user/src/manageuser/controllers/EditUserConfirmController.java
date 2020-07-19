/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [EditUserConfirmController.java], [April 6, 2020] [Kiều Văn Quang]
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.UserInforEntity;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Constant;

/**
 * Class EditUserConfirmController dùng để xử lý việc update thông tin user
 * @author Kiều Văn Quang
 *
 */
@WebServlet(urlPatterns = { "/EditUserConfirm.do", "/EditUserOk.do" })
public class EditUserConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Phương thức doGet
	 * @param request			Dùng để lấy thông tin từ phía client gửi về server
	 * @param response			Dùng để trả dữ liệu từ server cho client
	 * @throws ServletException	Ngoại lệ Servlet
	 * @throws IOException		Ngoại lệ đọc ghi file
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Khởi tạo session
		HttpSession session = request.getSession();
		// Khởi tạo keyEdit và lấy giá trị cho nó từ trên session
		String keyEdit = (String) session.getAttribute("keyEdit");
		// Xóa đối tượng keyEdit ở trên session
		session.removeAttribute("keyEdit");
		// Kiểm tra điều kiện xem keyEdit có tồn tại hay không
		if (keyEdit == null) {
			// Nếu không tồn tại thì quay trở lại màn hình listuser
			response.sendRedirect(request.getContextPath() + Constant.URL_LIST_USER);
		
		// Nếu keyEdit có giá trị đúng bằng Key đã set lên thì thực hiện khối lệnh trong if
		} else if (keyEdit.equals(Constant.KEY)) {
			// Lấy key và type từ request
			String key = request.getParameter("key");
			String type = request.getParameter("type");
			// Lấy về đối tượng UserInforEntity từ trên session
			Object u = session.getAttribute("userInfor" + key);
			// Ép kiểu cho đối tượng vừa lấy được từ session về UserInfor
			UserInforEntity user = (UserInforEntity) u;
			// Gán đối tượng và type lên request để hiển thị ở màn hình ADM004
			request.setAttribute("userI", user);
			request.setAttribute("type", type);
			request.setAttribute("key", key);
			// Di chuyển qua màn hình ADM004
			RequestDispatcher rd = request
					.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM004_PATH + "?type=edit&key=" + key);
			rd.forward(request, response);
		}
	}

	/**
	 * Phương thức doPost
	 * @param request			Dùng để lấy thông tin từ phía client gửi về server
	 * @param response			Dùng để trả dữ liệu từ server cho client
	 * @throws ServletException	Ngoại lệ Servlet
	 * @throws IOException		Ngoại lệ đọc ghi file
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy session từ trên request
		HttpSession session = request.getSession();
		// Khởi tạo đối tượng TblUserLogic
		TblUserLogic tbl = new TblUserLogicImpl();
		// Lấy key từ request
		String key = request.getParameter("key");
		// Lấy về đối tượng từ session
		Object u = session.getAttribute("userInfor" + key);
		// Ép kiểu cho đối tượng vừa lấy về sang UserInforEntity
		UserInforEntity userInfor = (UserInforEntity) u;
		session.removeAttribute("userInfor" + key);
		String email = userInfor.getEmail();
		String loginName = userInfor.getLoginName();
		int userId = userInfor.getUserId();
		// Mở try
		try {
			// Kiểm tra điều kiện xem userId, email, và loginName tồn tại không
			if (tbl.checkExistTblUserId(userId) && tbl.checkExistEmail(email, userId)
					&& tbl.checkExistLoginName(loginName, userId)) {
				// Kiểm tra việc updateUserInfor có thành công hay không
				if (tbl.updateUserInfor(userInfor)) {
					// Nếu thành công thì di chuyển vào SuccessController cùng type=edit
					response.sendRedirect(
							request.getContextPath() + Constant.URL_SUCCESS_CONTROLLER + "?type=" + Constant.EDIT);
					
				// Nếu không thành công thì vào SystemErrorController cùng type=edit và mã lỗi ER015
				} else {
					response.sendRedirect(request.getContextPath() + Constant.URL_SYSTEM_ERROR_CONTROLLER + "?type="
							+ Constant.EDIT + "&code="+Constant.ER015);
				}
				
			// Nếu user khong còn tồn tại tại thì vào SystemErrorController cùng type=edit và mã lỗi ER013
			} else {
				response.sendRedirect(request.getContextPath() + Constant.URL_SYSTEM_ERROR_CONTROLLER + "?type="
						+ Constant.EDIT + "&code="+Constant.ER013);
			}
			
		// Bắt ngoại lệ
		} catch (Exception e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
		}

	}

}
