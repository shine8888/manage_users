/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [Filter.java], [April 5, 2020] [Kiều Văn Quang]
 */
package manageuser.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Class FilterForRequest dùng để kiểm tra việc đăng nhập
 * 
 * @author Kiều Văn Quang
 *
 */
public class LoginFilter implements javax.servlet.Filter {
	/**
	 * Phương thức doFilter Truyền vào request, response, và chain
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Ép kiểu dữ liệu
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// Lấy về session
		HttpSession session = req.getSession(true);
		// Lấy về string chứa đường link servlet
		String path = req.getRequestURI();
		// Mở try
		try {
			// Kiểm tra nếu đã đăng nhập và nhập link Login.do
			if (path.contains(Constant.URL_LOG_IN) && Common.checkLogin(session)) {
				resp.sendRedirect(req.getContextPath() + Constant.URL_LIST_USER);
			// Kiểm tra điều kiện nếu link chứa url login.do và logout.do
			} else if (path.contains(Constant.URL_LOG_IN) || path.contains(Constant.URL_LOG_OUT)
					|| path.contains(Constant.URL_SYSTEM_ERROR_CONTROLLER) || Common.checkLogin(session)) {
				chain.doFilter(request, response);
			} else {
				// Chuyển về màn hình ADM001 khi chưa đăng nhập
				resp.sendRedirect(req.getContextPath() + Constant.URL_LOG_IN);
			}
		
		// Bắt lỗi
		} catch (Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.URL_ERROR_PAGE);
			rd.forward(req, resp);
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
		}
	}

	/**
	 * Phương thức khởi tạo
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

	/**
	 * Phương thức hủy
	 */
	public void destroy() {

	}

}
