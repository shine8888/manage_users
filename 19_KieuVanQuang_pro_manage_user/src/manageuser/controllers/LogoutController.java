/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [LogoutController.java], [Mar 9, 2020] [Kiều Văn Quang]
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

import manageuser.utils.Constant;

/**
 * Class LogoutController dùng để xử lý việc đăng xuất khỏi hệ thống
 * 
 * @author Kiều Văn Quang
 *
 */
@WebServlet("/Logout.do")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Phương thức doGet
	 * 
	 * @param request  			Dùng để lấy thông tin từ phía client gửi về server
	 * @param response 			Dùng để trả dữ liệu từ server cho client
	 * @throws ServletException Ngoại lệ Servlet
	 * @throws IOException      Ngoại lệ đọc ghi file
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy session từ trên request
		HttpSession session = request.getSession(false);
		// Kiểm tra xem session có tồn tại hay không
		if (session != null) {
			// Xóa các session
			session.invalidate();
		}
		// Chuyển qua màn hình log out
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM001_PATH);
		dispatcher.forward(request, response);
	}

}
