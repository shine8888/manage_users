package manager.controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.logic.SearchLogic;
import manager.model.Product;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public SearchLogic s;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
    	System.out.println("Quang");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("vaof r");
			s = new SearchLogic();
			String name = "iphone";
			System.out.println(name);
			List<Product> listItems = s.searchingItems(name);
			request.setAttribute("listItems", listItems);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("View/jsp/Home.jsp");
			// truyền response cho ADM001
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("vaof r123");
		doGet(request, response);
	}

}
