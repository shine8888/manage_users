package manager.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.common.Common;
import manager.common.Constant;
import manager.logic.SearchLogic;
import manager.model.Cart;
import manager.model.Product;

/**
 * Servlet implementation class AddToCartController
 */
@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cart cart;
	private SearchLogic s;

	public AddToCartController() {
		s = new SearchLogic();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			int idProduct = Common.convertStringToInt(request.getParameter("id"), 0);
			cart = new Cart();
			Product p = s.getProduct(idProduct);
			p.setNumber(1);
			cart.add(p);
			Cart sessionCart = (Cart) session.getAttribute("sessionCart");
			if (sessionCart == null) {
				sessionCart = new Cart();
				session.setAttribute("sessionCart", cart);
				System.out.println("Cart dang null");
			}
			sessionCart.add(p);
			sessionCart.getAmount();
			System.out.println(sessionCart.getAmount() + " day la co");
			session.setAttribute("sessionCart", sessionCart);

			RequestDispatcher rd = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.CART_PATH);
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String idDelete = request.getParameter("id-del");
			String addQuantity = request.getParameter("add");
			Cart sessionCart = (Cart) session.getAttribute("sessionCart");
			if (!"".equals(idDelete) || idDelete != null) {
				int id = Common.convertStringToInt(idDelete, -1);
				if (id >= 0) {
					sessionCart.remove(id);
				}
			}
			
			if(!"".equals(addQuantity) && addQuantity != null) {
				String[] list = Common.splitString(addQuantity);
				System.out.println("Dang sai o day");
				int id = Common.convertStringToInt(list[0], -1);
				int amount = Common.convertStringToInt(list[1], -1);
				if(id != -1 && amount != -1) {
					sessionCart.addAmount(id, amount);
				}				
			}
			session.setAttribute("sessionCart", sessionCart);

			RequestDispatcher rd = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.CART_PATH);
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println("Looix");
		}

	}

}
