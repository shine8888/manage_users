/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [SystemErrorController.java], [April 3, 2020] [Kiều Văn Quang]
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Constant;
import manageuser.utils.MessageErrorProperties;

/**
 * Class SystemErrorController dùng để xử lý các luồng thông báo ra màn hình
 * SystemError
 * 
 * @author Kiều Văn Quang
 *
 */
@WebServlet(name = "SystemError.do", urlPatterns = { "/SystemError.do" })
public class SystemErrorController extends HttpServlet {
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
		// Lấy về từ request giá trị cho type
		String type = request.getParameter("type");
		String code = request.getParameter("code");

		// Sử dụng switch case để chia các trường hợp di chuyển màn hình
		switch (type) {

		// Trường hợp add không thành công
		case Constant.ADD:
			// Lấy câu thông báo
			String error = MessageErrorProperties.getValueByKey(code);
			// Gàn giá trị lên request
			request.setAttribute("errorSystem", error);
			// Chuyển qua màn hình ADM006 với câu thông báo thành công
			RequestDispatcher rd = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.URL_ERROR_PAGE);
			rd.forward(request, response);
			break;

		// Trường hợp edit không thành công
		case Constant.EDIT:
			// Lấy câu thông báo lỗi
			String error1 = MessageErrorProperties.getValueByKey(code);
			// Gán giá trị lên request
			request.setAttribute("errorSystem", error1);
			// Chuyển sang màn hình thông báo lỗi
			RequestDispatcher rd1 = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.URL_ERROR_PAGE);
			rd1.forward(request, response);
			break;

		// Trường hợp edit không thành công
		case Constant.DELETE:
			// Lấy câu thông báo lỗi
			String error2 = MessageErrorProperties.getValueByKey(code);
			// Gán giá trị lên request
			request.setAttribute("errorSystem", error2);
			// Chuyển sang màn hình thông báo lỗi
			RequestDispatcher rd2 = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.URL_ERROR_PAGE);
			rd2.forward(request, response);
			break;
		}

	}

}
