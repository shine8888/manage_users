/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [SuccessController.java], [April 3, 2020] [Kiều Văn Quang]
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
import manageuser.utils.MessageProperties;

/**
 * Class SuccessController dùng để quản lý các luồng thông báo ra màn hình
 * ADM006
 * 
 * @author Kiều Văn Quang
 *
 */
@WebServlet(urlPatterns = { "/SuccessController.do" })
public class SuccessController extends HttpServlet {
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
		
		// Sử dụng switch case để chia các trường hợp di chuyển màn hình
		switch (type) {
		
		// Trường hợp thêm thành công
		case Constant.ADD:
			// Lấy câu thông báo
			String success = MessageProperties.getValueByKey(Constant.MSG001);
			// Gàn giá trị lên request
			request.setAttribute("messages", success);
			// Chuyển qua màn hình ADM006 với câu thông báo thành công
			RequestDispatcher rd = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM006_PATH);
			rd.forward(request, response);
			break;
			
		// Trường hợp edit thành công
		case Constant.EDIT:
			// Lấy câu thông báo
			String success1 = MessageProperties.getValueByKey(Constant.MSG002);
			// Gán giá trị lên request
			request.setAttribute("messages", success1);
			// Chuyển sang màn hình thông báo lỗi
			RequestDispatcher rd1 = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM006_PATH);
			rd1.forward(request, response);
			break;
			
		// Trường hợp edit thành công
		case Constant.DELETE:
			// Lấy câu thông báo
			String success2 = MessageProperties.getValueByKey(Constant.MSG003);
			// Gán giá trị lên request
			request.setAttribute("messages", success2);
			// Chuyển sang màn hình thông báo lỗi
			RequestDispatcher rd2 = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM006_PATH);
			rd2.forward(request, response);
			break;
		}

	}

}
