package manager.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.logic.SearchLogic;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public SearchLogic s;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			String name = request.getParameter("searching");
//			s = new SearchLogic();
//			List<Product> listProducts = s.getListItems(0, 15, name);;
//			request.setAttribute("listProducts", listProducts);
//			RequestDispatcher requestDispatcher = request
//					.getRequestDispatcher("View/jsp/Home.jsp");
//			// truyền response cho ADM001
//			requestDispatcher.forward(request, response);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("vaof r123");
		doGet(request, response);
	}

}
