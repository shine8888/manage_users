/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [EditUserController.java], [April 5, 2020] [Kiều Văn Quang]
 */
package manageuser.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.Validate;

/**
 * Class EditUserController dùng để xử lý các thao tác chỉnh sửa thông tin user
 * 
 * @author Kiều Văn Quang
 *
 */
@WebServlet(urlPatterns = { "/EditDetailUser.do", "/EditUserValidate.do" })
public class EditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Khai báo biến chung id
	private int id = -1;
	// Khai báo đối tượng TblUserLogic
	private TblUserLogic tbl;

	// Khởi tạo constructor
	public EditUserController() {
		tbl = new TblUserLogicImpl();
	}

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
		// Lấy giá trị cho index từ request
		String index = request.getParameter("id");
		// Lấy về giá trị cho mode từ request
		String mode = request.getParameter("mode");
		// Chuyển đổi từ String sang int, rồi gán giá trị cho id
		id = Common.convertStringToInt(index, 0);

		// Mở try
		try {
			// Kiểm tra điều kiện cho các trường hợp chuyển về màn hình edit
			if ((tbl.checkExistTblUserId(id) && mode.equals(Constant.EDIT_MODE_05))
					|| mode.equals(Constant.EDIT_MODE_04) || mode.equals(Constant.EDIT_MODE_03)) {
				// Gọi hàm setDataLogic
				Common.setDataLogic(request);
				// Khởi tạo đối tượng UserInforEntity và gán giá trị từ hàm getDefaultValue
				UserInforEntity user = getDefaultValue(request);

				// Set đối tượng user lên request để hiển thị ở ADM003
				request.setAttribute("userInf", user);
				// Set lại giá trị mode lên request để phục vụ hiển thị ở ADM003
				request.setAttribute("mode", mode);
				request.setAttribute("type", "edit");

				// Chuyển hướng sang màn hình ADM003
				RequestDispatcher rd = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM003_PATH);
				rd.forward(request, response);

				// Nếu điều kiện if bị sai thì chuyển vào hàm else
			} else {
				// Chuyển hướng sang trang thông báo lỗi
				response.sendRedirect(request.getContextPath() + Constant.URL_SYSTEM_ERROR_CONTROLLER + "?type="
						+ Constant.EDIT + "&code="+Constant.ER013);
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

	/**
	 * Phương thức doPost
	 * 
	 * @param request  			Dùng để lấy thông tin từ phía client gửi về server
	 * @param response 			Dùng để trả dữ liệu từ server cho client
	 * @throws ServletException Ngoại lệ Servlet
	 * @throws IOException      Ngoại lệ đọc ghi file
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Mở try
		try {
			// Khởi tạo session
			HttpSession session = request.getSession();
			// Khởi tạo các đối tượng phục vụ cho phương thức
			TblUserLogic tbl = new TblUserLogicImpl();
			UserInforEntity user = new UserInforEntity();
			Validate v = new Validate();

			// Lấy các giá trị type, index từ trên request
			String type = request.getParameter("type");
			String index = request.getParameter("id");
			// Convert từ dạng string sang int
			int id = Common.convertStringToInt(index, 0);
			// Tạo biến kiểm tra xem xem có tồn tại đối tượng tbl_user hay không
			boolean check = tbl.checkExistTblUserId(id);
			if (check) {
				// Lấy giá trị cho user từ hàm getDefaultValue
				user = getDefaultValue(request);
				// Validate user
				List<String> listError = v.validateUser(user, type);
				// Kiểm tra nếu listError rỗng( Validate thành công)
				if (listError.isEmpty()) {
					// Set lại giá trị birthday dạng date cho user
					user.setBirthday(Common.convertStringToDate(user.getListTime().get(0)));
					// Kiểm tra xem trình độ tiếng Nhật có tồn tại hay không
					if ( !user.getCodeLevel().equals(Constant.N0)) {
						// Gán các giá trị cho trường trình độ tiếng Nhật
						user.setNameLevel(Common.getNameLevel(user.getCodeLevel()));
						user.setStartDate(Common.convertStringToDate(user.getListTime().get(1)));
						user.setEndDate(Common.convertStringToDate(user.getListTime().get(2)));
						user.setTotal(Common.convertStringToInt(user.getScore(), 0));
					} else {
						user.getListTime().set(1, null);
						user.getListTime().set(2, null);
					}
					// Lấy key
					String key = Common.getKey();
					// Set userInfor +key lên session
					session.setAttribute("userInfor" + key, user);
					session.setAttribute("keyEdit", Constant.KEY);
					// Chuyển hướng tới URL: /AddUserConfirm.do
					response.sendRedirect(request.getContextPath() + Constant.URL_EDIT_CONFIRM_CONTROLLER + "?type="+Constant.EDIT+"&key="+key);

					// Nếu validate không thành công
				} else {
					// Gán lại id lên request
					request.setAttribute("id", user.getUserId());
					// Gọi hàm setDataLogic
					Common.setDataLogic(request);
					// Set các giá trị cần thiết lên request
					request.setAttribute("userInf", user);
					request.setAttribute("listError", listError);
					request.setAttribute("type", "edit");
					// Chuyển hướng trở lại màn hình ADM003
					RequestDispatcher rq = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM003_PATH);
					rq.forward(request, response);
					// Xóa list lỗi
					listError.clear();
				}

				// Nếu không tồn tại user trong DB
			} else {
				// Chuyển vào SystemErrorController
				response.sendRedirect(request.getContextPath() + Constant.URL_SYSTEM_ERROR_CONTROLLER);
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
	
	/**
	 * Hàm dùng để lấy ra giá trị cho user
	 * @param request	Dùng để lấy dữ liệu từ client gửi lên server
	 * @return			Trả về một đối tượng UserInforEntity
	 */
	public UserInforEntity getDefaultValue(HttpServletRequest request) {
		// Khởi tạo List lưu các giá trị kiểu Date
		List<String> listTime = new ArrayList<String>();
		// Lấy giá trị mode từ request
		String mode = request.getParameter("mode");
		// Khởi tạo đối tượng session
		HttpSession session = request.getSession();
		// Khởi tạo đối tượng UserInforEntity
		UserInforEntity user = new UserInforEntity();

		// Switch case để xét các trường hợp về lại màn hình ADM003 Edit
		switch (mode) {
		// Trường hợp từ ADM005 back về để sửa
		case Constant.EDIT_MODE_05:
			// Mở try
			try {
				// Lấy UserInfor bằng id
				user = tbl.getUserInforById(id);
				// Gán giá trị cho listBirthDay để hiển thị ở ô selectbox
				user.setListBirthday(Common.convertStringToListInteger(user.getBirthday().toString()));
				// Kiểm tra nếu tồn tại trình độ tiếng Nhật
				if (user.getNameLevel() != null) {
					// Gán giá trị cho listStartDate, listEndate để hiển thị ở ô selectbox
					user.setListStartDate(Common.convertStringToListInteger(user.getStartDate().toString()));
					user.setListEndDate(Common.convertStringToListInteger(user.getEndDate().toString()));
					// Gán giá trị cho thuộc tính Score để hiển thị
					user.setScore(user.getTotal() + "");
					// Nếu khong tồn tại trình độ tiếng Nhật
				} else {
					// Gán các giá trị cho listStartDate và listEndDate để hiển thị ở selectbox
					user.setListStartDate(Common.getDateNow());
					List<Integer> list = new ArrayList<Integer>();
					list.add(user.getListStartDate().get(0) + 1);
					list.add(user.getListStartDate().get(1));
					list.add(user.getListStartDate().get(2));
					user.setListEndDate(list);
					// Gán giá trị cho Score là null
					user.setScore(null);
				}
				// Bắt lỗi
			} catch (ClassNotFoundException | SQLException e) {
				// Thông báo lỗi
				String methodName = new Object() {
				}.getClass().getEnclosingMethod().getName();
				String className = this.getClass().getName();
				System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			}
			break;

		// Trường hợp từ ADM04 quay trở lại để chỉnh sửa
		case Constant.EDIT_MODE_04:
			// Lấy key từ trên request
			String key = request.getParameter("key");
			// Ép kiểu và gán giá trị cho đối tượng user
			user = (UserInforEntity) session.getAttribute("userInfor" + key);
			// Gán lại key lên request
			request.setAttribute("key", key);
			// Xóa đi đối tượng userInfor+key trên session
			session.removeAttribute("userInfor" + key);
			break;

		// Trường hợp submit form để validate
		case Constant.EDIT_MODE_03:
			// Lấy về giá trị cho loginName từ request
			String loginName = request.getParameter("loginName");
			// Lấy về giá trị cho groupId từ request
			String groupId = request.getParameter("group_id");
			// Lấy về giá trị cho fullName từ request
			String fullName = request.getParameter("name");
			// Lấy về giá trị cho fullNameKana từ request
			String fullNameKana = request.getParameter("nameKana");

			// Lấy về giá trị cho ngày sinh từ request
			String birthYear = request.getParameter("birthYear");
			String birthMonth = request.getParameter("birthMonth");
			String birthDay = request.getParameter("birthDay");
			// Chuyển sang dạng date yyyy/MM/dd để phục vụ việc ép kiểu
			String time = birthYear + "/" + birthMonth + "/" + birthDay;

			// Lấy về giá trị cho email từ request
			String email = request.getParameter("email");
			// Lấy về giá trị cho tel từ request
			String tel = request.getParameter("tel");
			// Lấy về giá trị cho codeLevel từ request
			String codeLevel = request.getParameter("kyu_id");

			// Lấy về giá trị cho ngày cấp chứng chỉ từ request
			String yearBeginning = request.getParameter("yearBeginning");
			String monthBeginning = request.getParameter("monthBeginning");
			String dayBeginning = request.getParameter("dayBeginning");
			// Chuyển sang dạng date yyyy/MM/dd để phục vụ việc ép kiểu
			String time2 = yearBeginning + "/" + monthBeginning + "/" + dayBeginning;

			// Lấy về giá trị cho ngày hết hạn chứng chỉ từ request
			String yearEnding = request.getParameter("yearEnding");
			String monthEnding = request.getParameter("monthEnding");
			String dayEnding = request.getParameter("dayEnding");
			// Chuyển sang dạng date yyyy/MM/dd để phục vụ việc ép kiểu
			String time3 = yearEnding + "/" + monthEnding + "/" + dayEnding;

			// Thêm các giá trị cho listTime
			listTime.add(time);
			listTime.add(time2);
			listTime.add(time3);

			// Lấy về giá trị cho total từ request
			String total = request.getParameter("total");

			// Chuyển đổi các chuỗi này qua kiểu dữ liệu Integer
			List<Integer> listBirthday = Common.convertStringToListInteger(time);
			List<Integer> listStartDate = Common.convertStringToListInteger(time2);
			List<Integer> listEndDate = Common.convertStringToListInteger(time3);
			// Chuyển đổi kiểu groupId từ String sang int
			int idGroup = Common.convertStringToInt(groupId, 0);

			// Set các giá trị cho user
			user.setUserId(id);
			user.setLoginName(loginName);
			user.setGroupId(idGroup);
			user.setCodeLevel(codeLevel);
			user.setFullName(fullName);
			user.setFullNameKana(fullNameKana);
			user.setEmail(email);
			user.setTel(tel);
			user.setListTime(listTime);
			user.setScore(total);
			user.setListBirthday(listBirthday);
			user.setListStartDate(listStartDate);
			user.setListEndDate(listEndDate);
			user.setGroupName(Common.getGroupName(user.getGroupId()));
			user.setNameLevel(Common.getNameLevel(user.getCodeLevel()));
			break;
		}
		// Trả về đối tượng cho phương thức
		return user;
	}

}
