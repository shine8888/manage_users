/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [AddUserController.java], [Mar 9, 2020] [Kiều Văn Quang]
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

import manageuser.entities.UserInforEntity;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.Validate;

/**
 * Class AddUserInputController dùng để xử lý thêm người dùng vào trong database
 * @author Kiều Văn Quang
 *
 */
@WebServlet(urlPatterns = { "/AddUser.do", "/AddUserValidate.do" })
public class AddUserInputController extends HttpServlet {
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
		try {
			// Khởi tạo đối tượng UserInforEntities
			UserInforEntity userInf = new UserInforEntity();
			// GÁn giá trị cho lựa chọn cho các pulldown
			Common.setDataLogic(request);
			// Gán giá trị cho đối tượng
			userInf = getDefaultValue(request);
			// Set đói tượng userInf lên req
			request.setAttribute("userInf", userInf);
			// Di chuyển qua màn hình ADM003
			request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM003_PATH).forward(request, response);
			// Bắt lỗi
		} catch (Exception e) {
			// Thông báo lỗi
			String method = new Object() {}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + method + ". Error : " + e.getMessage());


		}

	}

	/**
	 * Phương thức doPost
	 * @param request			Dùng để lấy thông tin từ phía client gửi về server
	 * @param response			Dùng để trả dữ liệu từ server cho client
	 * @throws ServletException	Ngoại lệ Servlet
	 * @throws IOException		Ngoại lệ đọc ghi file
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Khởi tạo và gán giá trị cho session
		HttpSession session = request.getSession();
		// Lấy về kiểu validate là add, hay edit
		String type = request.getParameter("type");
		// Tạo đối tượng Validate
		Validate v = new Validate();
		
		// Mở try
		try {
			// Khởi tạo đối tượng và lấy giá trị cho user
			UserInforEntity user = getDefaultValue(request);
			List<String> listError = v.validateUser(user, type);
			// Lấy key
			String key = Common.getKey();
			
			// Kiểm tra list lỗi sau khi Validate
			if (listError.size() == 0) {
				// Sau khi Validate thành công. Gán lại các giá trị ngày tháng cho đối tượng user
				user.setBirthday(Common.convertStringToDate(user.getListTime().get(0)));
				user.setStartDate(Common.convertStringToDate(user.getListTime().get(1)));
				user.setEndDate(Common.convertStringToDate(user.getListTime().get(2)));
				user.setTotal(Common.convertStringToInt(user.getScore(), 0));
				
				if(user.getNameLevel() == null) {
					user.getListTime().set(1, null);
					user.getListTime().set(2, null);
				}
				// Set user lên session
				session.setAttribute("keyAdd", Constant.KEY);
				session.setAttribute("userInfor" + key, user);
				// Chuyển hướng tới URL: /AddUserConfirm.do
				response.sendRedirect(request.getContextPath() + Constant.URL_ADD_USER_CONFIRM + "?key=" + key);
				// Khi Validate không thành công
			} else {
				// Gọi lại hàm setDataLogic để lấy giá trị cho các ô selectbox
				Common.setDataLogic(request);
				// Gán các giá trị lên request
				request.setAttribute("userInf", user);
				request.setAttribute("listError", listError);
				
				// Chuyển hướng về EditUserController
				RequestDispatcher rq = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.ADM003_PATH);
				rq.forward(request, response);
				
				// Xóa list lỗi
				listError.clear();

			}
			// Bắt lỗi
		} catch (Exception e) {
			// Thông báo lỗi
			String method = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + method + ". Error : " + e.getMessage());
			// Di chuyển qua trang thông báo lỗi
			RequestDispatcher rq = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.URL_ERROR_PAGE);
			rq.forward(request, response);
		}

	}

	/**
	 * Hàm getDefaultValue
	 * @param request			Dùng để lấy thông tin từ phía client gửi về server
	 * @return UserInforEntity	Kiểu trả về của phương thức là đối tượng UserInforEntity
	 */
	@SuppressWarnings("finally")
	public UserInforEntity getDefaultValue(HttpServletRequest request) {
		// Khởi tạo List lưu các giá trị kiểu Date
		List<String> listTime = new ArrayList<String>();
		// Khởi tạo một user với giá trị null
		UserInforEntity user = new UserInforEntity();
		// Mở try
		try {
			// Lấy về mode từ request
			String mode = request.getParameter("mode");
			// Dùng switch case để chia các trường hợp di chuyển giữa các màn hình
			switch (mode) {
			
			// Trường hợp từ ADM002 chuyển sang ADM003
			case Constant.DEFAULT_TYPE:
				// Set giá trị cho các list ngày tháng năm, phục vụ việc hiển thị ở ADM003
				user.setListBirthday(Common.getDateNow());
				user.setListStartDate(Common.getDateNow());
				user.setListEndDate(Common.getDateNow());
				user.getListEndDate().set(0, user.getListStartDate().get(0) + 1);	
				break;
				
			// Trường hợp submit form để validate
			case Constant.SUBMIT_TYPE:
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
				// Lấy về giá trị cho password từ request
				String password = request.getParameter("password");
				// Lấy về giá trị cho password từ request
				String confirmPassword = request.getParameter("confirmPasssword");
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
				user.setLoginName(loginName);
				user.setGroupId(idGroup);
				user.setCodeLevel(codeLevel);
				user.setFullName(fullName);
				user.setFullNameKana(fullNameKana);
				user.setEmail(email);
				user.setTel(tel);
				user.setPassword(password);
				user.setConfirmPassword(confirmPassword);
				user.setListTime(listTime);
				user.setScore(total);
				user.setListBirthday(listBirthday);
				user.setListStartDate(listStartDate);
				user.setListEndDate(listEndDate);
				user.setGroupName(Common.getGroupName(user.getGroupId()));
				user.setNameLevel(Common.getNameLevel(user.getCodeLevel()));
				break;

			case Constant.BACK_TYPE:
				HttpSession session = request.getSession();
				String key = request.getParameter("key");
				user = (UserInforEntity) session.getAttribute("userInfor" + key);
				// Xóa đi đối tượng userInfor+key trên session
				session.removeAttribute("userInfor" + key);
				break;
			}
			// Bắt lỗi
		} catch (Exception e) {
			// Thông báo lỗi
			String method = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + method + ". Error : " + e.getMessage());
		} finally {
			// Trả về user cho phương thức
			return user;
		}

	}

}
