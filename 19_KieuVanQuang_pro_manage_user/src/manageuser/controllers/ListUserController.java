/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [ListUserController.java.java], [Mar 9, 2020] [Kiều Văn Quang]
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

import manageuser.entities.MstGroupEntity;
import manageuser.entities.UserInforEntity;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.utils.MessageProperties;

/**
 * Class ListUserController thực hiện việc hiển thị list user khi tìm kiếm
 * 
 * @author Kiều Văn Quang
 */
@WebServlet("/ListUser.do")
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Phương thức doGet
	 * 
	 * @param request  			Dùng để lấy thông tin từ phía client gửi về server
	 * @param response 			Dùng để trả dữ liệu từ server cho client
	 * @throws ServletException Ngoại lệ Servlet
	 * @throws IOException      Ngoại lệ đọc ghi file
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Khởi tạo session
		HttpSession session = request.getSession();
		try {
			// Khởi tạo các đối tượng cần thiết cho phương thức
			MstGroupLogic mstGroupLogicImpl = new MstGroupLogicImpl();
			TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
			List<UserInforEntity> listUserInfor = new ArrayList<UserInforEntity>();
			List<MstGroupEntity> listMstGroup = new ArrayList<MstGroupEntity>();

			// Lấy ra số lượng bản ghi giới hạn hiển thị trên 1 page
			int limit = Integer.parseInt(Constant.LIMIT);
			// Lấy ra các giá trị mặc định cần dùng
			// Các giá trị mặc định sort
			String sortEndDate = Constant.SORT_ENDDATE_DEFAULT;
			String sortName = Constant.SORT_NAME_DEFAULT;
			String sortLevel = Constant.SORT_LEVEL_DEFAULT;
			
			// Khởi tạo tên tìm kiếm mặc định
			String searchingName = Constant.SEARCHING_DEFAULT;
			// Khởi tạo tổng số user mặc định
			int totalUser = Constant.TOTAL_USER_DEFAULT;
			// Khởi tạo offset là vị trí cần lấy bản ghi trong db
			int offset = Constant.OFFSET_DEFAULT;
			// Khởi tạo Group mặc định
			int group = Constant.GROUP_ID_DEFAULT;
			// Khởi tạo currentPage mặc định
			int currentPage = Constant.DEFAULT_PAGE;
			// Khởi tạo kiểu sắp xếp mặc định
			String sortType = Constant.DEFAULT_SORT_TYPE;

			// Lấy giá trị cho mode
			String mode = request.getParameter("mode");
			// Nếu mode = null thì gán mode có giá trị default
			if (mode == null) {
				mode = Constant.DEFAULT_MODE;
			}
			// Khởi chạy switch case để xét các trường hợp
			switch (mode) {
			
			// Trường hợp mặc định
			case Constant.DEFAULT_MODE:
				break;
				
			// Trường hợp search
			case Constant.SEARCHING_MODE:
				// Lấy ra tên tìm kiếm hiện tại
				searchingName = request.getParameter("searchingName");
				// Lấy ra group tìm kiếm hiện tại
				group = Common.convertStringToInt(request.getParameter("groupId"), group);
				break;
				
			// Trường hợp Sort và Paging
			case Constant.SORT_MODE:
			case Constant.PAGING_MODE:
				// Lấy ra tên tìm kiếm
				searchingName = request.getParameter("searchingName");
				// Lấy ra group tìm kiếm hiện tại
				group = Common.convertStringToInt(request.getParameter("groupId"), group);
				// Lấy ra kiểu sort
				sortType = request.getParameter("sortType");
				// Lấy ra currentPage
				currentPage = Common.convertStringToInt(request.getParameter("currentPage"), currentPage);
				
				// Khởi chạy thêm 1 switch case để tìm các trường hợp sort
				// các kiểu sort
				switch (sortType) {
				// Sort theo ngày kết thúc chứng chỉ tiếng Nhật
				case Constant.SORT_TYPE_END_DATE:
					sortEndDate = request.getParameter("sortEndDate");
					break;
				
				// Sort theo trình độ tiếng Nhật
				case Constant.SORT_TYPE_LEVEL:
					sortLevel = request.getParameter("sortLevel");
					break;
				// Sort theo tên
				case Constant.SORT_TYPE_NAME:
					sortName = request.getParameter("sortName");
					break;
				}
				break;
				
			// Trường hợp back về từ ADM003
			case Constant.BACK_MODE:
				// Lấy ra kiểu sort trước khi chuyển vào màn hình ADM003
				sortType = (String) session.getAttribute("sortType");
				// Lấy ra page được chọn trước khi chuyển vào màn hình ADM003
				currentPage = (int) session.getAttribute("currentPage");
				// Lấy ra tên lúc trước khi chuyển vào màn hình ADM003
				searchingName = (String) session.getAttribute("searchingName");
				// Lấy ra group lúc trước khi chuyển vào màn hình ADM003
				group = (int) session.getAttribute("groupId");
				// Khởi chạy thêm 1 switch case để tìm các trường hợp sort
				// các kiểu sort
				switch (sortType) {
				// Sort theo ngày kết thúc chứng chỉ tiếng Nhật
				case Constant.SORT_TYPE_END_DATE:
					sortEndDate = (String) session.getAttribute("sortEndDate");
					break;
				// Sort theo trình độ tiếng Nhật
				case Constant.SORT_TYPE_LEVEL:
					sortLevel = (String) session.getAttribute("sortLevel");
					break;
				// Sort theo tên
				case Constant.SORT_TYPE_NAME:
					sortName = (String) session.getAttribute("sortName");
					break;
				}
				break;
			}

			// Gọi hàm lấy ra tổng số user có trong DB
			totalUser = tblUserLogicImpl.getTotalUser(group, searchingName);
			// Gọi hàm lấy ra danh sách group có trong DB
			listMstGroup = mstGroupLogicImpl.getAllMstGroup();
			
			// Kiểm tra nếu không có bản ghi nào trong DB thì in ra câu thông báo lỗi
			if (totalUser == 0) {
				String err = MessageProperties.getValueByKey(Constant.MSG005);
				request.setAttribute("mess", err);
			} else {
				// Gọi hàm lấy ra tổng số trang hiển thị
				int totalPage = Common.getTotalPage(totalUser, limit);
				// Xét điều kiện khi mà nhập ở trên url trang hiện tại > tổng số trang có
				// thì trả về trang cuối cùng
				if (currentPage > totalPage) {
					currentPage = totalPage;
				}
				// Lấy ra vị trí bản ghi cần lấy trước khi sang màn hình ADM003
				offset = Common.getOffSet(currentPage, limit);
				// Gọi hàm lấy ra tất cả danh sách user hiển thị lên table
				listUserInfor = tblUserLogicImpl.getListUsers(offset, limit, group, searchingName, sortType, sortName,
						sortLevel, sortEndDate);
				// Gọi hàm lấy ra listPaging hiển thị
				List<Integer> listPaging = Common.getListPaging(totalUser, limit, currentPage);
				// Xét các trường hợp hiển thị << ,>>, và không hiện paging
				if (listPaging.size() != 0) {
					// Kiểm tra điều kiện để hiển thị button back <<
					if (listPaging.get(0) > Constant.DEFAULT_PAGE) {
						request.setAttribute("currentPageBack", listPaging.get(0) - Constant.LIMIT_PAGE);
						request.setAttribute("paggingBack", Constant.BACK_SYMBOL);
					}
					
					// Kiểm tra điều kiện để hiển thị button next >>
					if (listPaging.get(listPaging.size() - 1) < totalPage) {
						request.setAttribute("currentPageNext", listPaging.get(listPaging.size() - 1) + 1);
						request.setAttribute("paggingNext", Constant.NEXT_SYMBOL);
					}
					
					// Kiểm tra điều kiện để không hiển thị paging
					if (listPaging.size() == 1 && listPaging.get(0) == 1) {
						listPaging.remove(0);
					}
					// Set listPagging lên request để hiển thị tại màn hình ADM002
					request.setAttribute("listPaging", listPaging);
				}
			}
			
			// Set các giá trị lên session để dùng trong trường hợp back
			session.setAttribute("searchingName", searchingName);
			session.setAttribute("groupId", group);
			session.setAttribute("sortType", sortType);
			session.setAttribute("currentPage", currentPage);
			session.setAttribute("sortName", sortName);
			session.setAttribute("sortLevel", sortLevel);
			session.setAttribute("sortEndDate", sortEndDate);
			
			// Set các giá trị lên request để bên jsp nhận dữ liệu
			request.setAttribute("searchingName", searchingName);
			request.setAttribute("groupId", group);
			request.setAttribute("listUserInfor", listUserInfor);
			request.setAttribute("sortLevel", sortLevel);
			request.setAttribute("sortEndDate", sortEndDate);
			request.setAttribute("sortType", sortType);
			request.setAttribute("sortName", sortName);
			request.setAttribute("listMstGroup", listMstGroup);		
			request.setAttribute("currentPage", currentPage);
					

			// Gửi dữ liệu và chuyển đến trang ADM002
			RequestDispatcher rq = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM002_PATH);
			rq.forward(request, response);
			
		// Bắt lỗi
		} catch (Exception e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			// hiển thị màn hình System_Error
			response.sendRedirect(
					request.getContextPath() + Constant.URL_SYSTEM_ERROR_CONTROLLER + "?code=" + Constant.ER015);
		}
	}

}
