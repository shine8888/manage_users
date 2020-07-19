/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [ViewDetailUserController.java], [April 4, 2020] [Kiều Văn Quang]
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.entities.UserInforEntity;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Constant;

/**
 * Class ViewDetailUserController dùng để hiển thị thông tin chi tiết của người
 * dùng ở màn hình ADM005
 * 
 * @author Kiều Văn Quang
 *
 */
@WebServlet(name = "ViewDetailUser.do", urlPatterns = { "/ViewDetailUser.do" })
public class ViewDetailUserController extends HttpServlet {
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
		// Khởi tạo đối tượng TblUserLogic
		TblUserLogic tbl = new TblUserLogicImpl();
		// Khởi tạo và lấy giá trị cho index từ request
		String index = request.getParameter("id");
		// Ép kiểu dữ liệu từ string sang int
		int id = Integer.parseInt(index);
		
		// Mở try
		try {
			// Khai báo biến check kiểu boolean
			boolean check = tbl.checkExistTblUserId(id);
			// Kiểm tra điều kiện
			if (check) {
				// Lấy giá trị cho đối tượng UserInforEntity
				UserInforEntity user = tbl.getUserInforById(id);
				if (user.getTotal() != 0) {
					user.setScore(user.getTotal() + "");
				}
				// Gán lại user lên request
				request.setAttribute("user", user);
				// Chuyển sang màn hình ADM005
				RequestDispatcher rd = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM005_PATH);
				rd.forward(request, response);
			}
			// Mở bắt lỗi
		} catch (Exception e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
		}
	}

}
