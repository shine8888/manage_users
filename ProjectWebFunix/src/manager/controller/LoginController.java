package manager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.common.Constant;
import manager.logic.ManagerLogic;



/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get data from form login
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String contextPath = request.getContextPath();
		ManagerLogic ml = new ManagerLogic();
		
//		// Read information of account in web.xml
//		String uid = getServletContext().getInitParameter("username");
//		String pwd = getServletContext().getInitParameter("password");
		
		// Declare a list error
		List<String> listError = new ArrayList<String>();
		
		// Check username has entered or not
		if(username == null || "".equals(username)) {
			listError.add("Enter your username");
		}
		
		// Check password has entered or not
		if(password == null || "".equals(password)) {
			listError.add("Enter your password");
		}
		
		// Check matching username and password
		if(listError.size() == 0) {
			
			if(ml.checkLogin(username, password)) {
				// Điều hướng đến servlet ListUser.do vào màn hình ADM002
				response.sendRedirect(contextPath + Constant.URL_LIST_ITEMS+"?mode=default");
			
			// Back to Login.jsp with list error
			} else {
				listError.add("You entered wrong password or username");
				request.setAttribute("listError", listError);
				RequestDispatcher rd = request.getRequestDispatcher("View/jsp/Login.jsp");
				rd.forward(request, response);
			}
		
		// Back to Login.jsp with list error
		} else {
			request.setAttribute("listError", listError);
			RequestDispatcher rd = request.getRequestDispatcher("View/jsp/Login.jsp");
			rd.forward(request, response);
		}	 
	}

}
