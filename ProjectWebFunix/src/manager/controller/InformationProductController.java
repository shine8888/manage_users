package manager.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.common.Common;
import manager.common.Constant;
import manager.logic.InformationProductLogic;
import manager.model.Product;

/**
 * Servlet implementation class InformationProductController
 */
@WebServlet("/InforProductController")
public class InformationProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			InformationProductLogic ipl = new InformationProductLogic();
			String code = request.getParameter("id");
			int id = Common.convertStringToInt(code, 0);
			Product product = ipl.getInforProduct(id);
			System.out.println(product.toString() + "Quang");
			request.setAttribute("product", product);
			
			RequestDispatcher rd = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.INFOR_PRODUCT_PATH);
			rd.forward(request, response);
		} catch (Exception e) {
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
