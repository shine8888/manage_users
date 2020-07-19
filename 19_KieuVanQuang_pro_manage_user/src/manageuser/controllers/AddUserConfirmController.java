/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [AddUserConfirmController.java], [April 3, 2020] [Kiều Văn Quang]
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
 * Class AddUserConfirmController dùng để xác nhận thêm mới user
 * 
 * @author Kiều Văn Quang
 *
 */
@WebServlet(urlPatterns = { "/AddUserConfirm.do", "/AddUserOk.do" })
public class AddUserConfirmController extends HttpServlet {
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
		// Khởi tạo keyAdd và lấy giá trị cho nó từ session
		String keyAdd = (String) session.getAttribute("keyAdd");
		// Xóa đối tượng keyAdd ở trên session
		session.removeAttribute("keyAdd");
		if (keyAdd == null) {
			response.sendRedirect(request.getContextPath()+Constant.URL_LIST_USER);	
		} else if(keyAdd.equals(Constant.KEY)){
			String key = request.getParameter("key");
			// Lấy về các đối tượng userInfor và listMstGroup
			UserInforEntity userInfor = new UserInforEntity();
			userInfor = (UserInforEntity) session.getAttribute("userInfor" + key);
			// Set lại các đối tượng lên request
			request.setAttribute("userI", userInfor);
			request.setAttribute("key", key);
			// Di chuyển sang màn hình ADM004
			RequestDispatcher rd = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM004_PATH);
			rd.forward(request, response);
		}
	}

	/**
	 Phương thức doPost
	 * @param request			Dùng để lấy thông tin từ phía client gửi về server
	 * @param response			Dùng để trả dữ liệu từ server cho client
	 * @throws ServletException	Ngoại lệ Servlet
	 * @throws IOException		Ngoại lệ đọc ghi file
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy về một session
		HttpSession session = request.getSession();
		try {
			TblUserLogic logic = new TblUserLogicImpl();
			String key = request.getParameter("key");
			// Lấy về các đối tượng userInfor và listMstGroup
			UserInforEntity user = new UserInforEntity();
			user = (UserInforEntity) session.getAttribute("userInfor" + key);
			// Xóa session
			session.removeAttribute("userInfor" + key);
			// Kiểm tra xem Login Name và Email có tồn tại trong DB không
			if (!logic.checkExistLoginName(user.getLoginName(), user.getUserId())
					&& !logic.checkExistEmail(user.getEmail(), user.getUserId())) {
				// Nếu chưa tồn tại thì gọi đến phương thức createUser
				if (logic.createUser(user)) {
					// Trả về màn hình thông báo thêm thành công
					response.sendRedirect(
							request.getContextPath() + Constant.URL_SUCCESS_CONTROLLER + "?type=" + Constant.ADD);
				} else {
					// Chuyển về màn hình thông báo lỗi
					response.sendRedirect(request.getContextPath() + Constant.URL_SYSTEM_ERROR_CONTROLLER + "?type="
							+ Constant.ADD + "&code="+Constant.ER015);
				}
			}
			// Bắt lỗi
		} catch (Exception e) {
			// Thông báo lỗi
			String method = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + method + ". Error : " + e.getMessage());
			// Chuyển về màn hình thông báo lỗi
			response.sendRedirect(request.getContextPath() + Constant.URL_SYSTEM_ERROR_CONTROLLER + "?type="
					+ Constant.ADD + "&code="+Constant.ER015);
		}
	}

}
