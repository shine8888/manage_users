/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [LoginController.java], [Mar 9, 2020] [Kiều Văn Quang]
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.Constant;
import manageuser.validates.Validate;

/**
 * Class LoginController thực hiện việc xử lý đăng nhập
 * 
 * @author Kiều Văn Quang
 *
 */
@WebServlet(urlPatterns = { "/Login.do" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * Phương thức doGet
	 * 
	 * @param request  			Dùng để lấy thông tin từ phía client gửi về server
	 * @param response 			Dùng để trả dữ liệu từ server cho client
	 * @throws ServletException Ngoại lệ Servlet
	 * @throws IOException      Ngoại lệ đọc ghi file
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RequestDispatcher rd = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM001_PATH);
			rd.forward(request, response);
		} catch (Exception e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			response.sendRedirect(request.getContextPath() + Constant.URL_SYSTEM_ERROR_CONTROLLER + "?type=" + Constant.LOGIN);
		}
	}

	/**
	 * Phương thức doPost
	 * 
	 * @param request  			Dùng để lấy thông tin từ phía client gửi về server
	 * @param response 			Dùng để trả dữ liệu từ server cho client
	 * @throws ServletException Ngoại lệ Servlet
	 * @throws IOException      Ngoại lệ đọc ghi file
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// Lấy ra đường dẫn của project
		String contextPath = request.getContextPath();
		try {
			// Gắn biến username để lấy dữ liệu loginname từ form
			String username = request.getParameter("loginName");
			// Gắn biến password để lấy dữ liệu password từ form
			String password = request.getParameter("password");
			// Khởi tạo biến session
			HttpSession session = request.getSession(false);
			// Khởi tạo biến list lưu các lỗi khi đăng nhập
			List<String> listError = new ArrayList<String>();
			// Kiểm tra đăng nhập của user
			listError = Validate.checkLogin(username, password);
			
			// Kiểm tra xem listError có rỗng hay không
			if (listError.isEmpty()) {
				// set sesion cho login_user
				session.setAttribute("loginName", username);
				// Điều hướng đến servlet ListUser.do vào màn hình ADM002
				response.sendRedirect(contextPath + Constant.URL_LIST_USER+"?mode=default");
			
			// Ngược lại
			} else {
				// Set listError lên request để hiển thị ra JSP
				request.setAttribute("listError", listError);
				// Điều hướng lại trang ADM001
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM001_PATH);
				// truyền response cho ADM001
				requestDispatcher.forward(request, response);
				// Xóa đi list lỗi
				listError.clear();
			}
		
		// Bắt lỗi
		} catch (Exception e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			// Chuyển đến trang system error
			try {
				response.sendRedirect(contextPath + Constant.URL_SYSTEM_ERROR_CONTROLLER + "?type=" + Constant.LOGIN);
			} catch (IOException e1) {
				System.out.println(this.getClass().getName() + "-" 
						+ Thread.currentThread().getStackTrace()[1].getMethodName() + "-" + e.getMessage());
			}
		}
	}
}
