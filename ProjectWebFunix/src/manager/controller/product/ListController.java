package manager.controller.product;

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


import manager.common.Common;
import manager.common.Constant;
import manager.logic.SearchLogic;
import manager.model.Product;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/ListController")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SearchLogic sr = new SearchLogic();
		List<Product> listProducts = new ArrayList<Product>();

		int limit = Constant.LIMIT;
		int offset = Constant.OFFSET;
		int currentPage = Constant.DEFAULT_PAGE;
		int totalItems = Constant.TOTAL_ITEM_DEFAULT;
		String brandName = Constant.BRAND_NAME_DEFAULT;
		String nameProduct = Constant.NAME_PRODUCT_DEFAULT;
		String mode = request.getParameter("mode");
		if (mode == null || mode == "" || "Category".equals(mode)) {
			mode = Constant.MODE_DEFAULT;
		}
		switch (mode) {
		// Trường hợp mặc định
		case Constant.MODE_DEFAULT:
			brandName = "";
			if (request.getParameter("searching") == null) {
				nameProduct = "";
			} else {
				nameProduct = request.getParameter("searching");
			}
			break;

		case Constant.MODE_SEARCHING:
			brandName = Constant.MODE_SEARCHING;
			break;

		case Constant.MODE_APPLE:
			brandName = Constant.MODE_APPLE;
			nameProduct = request.getParameter("searching");
			break;

		case Constant.MODE_NOKIA:
			brandName = Constant.MODE_NOKIA;
			nameProduct = request.getParameter("searching");
			break;

		case Constant.MODE_SAMSUNG:
			brandName = Constant.MODE_SAMSUNG;
			nameProduct = request.getParameter("searching");
			break;

		case Constant.MODE_XIAOMI:
			brandName = Constant.MODE_XIAOMI;
			nameProduct = request.getParameter("searching");
			break;
		case Constant.MODE_HUAWEI:
			brandName = Constant.MODE_HUAWEI;
			nameProduct = request.getParameter("searching");
			break;

		case Constant.MODE_OPPO:
			brandName = Constant.MODE_OPPO;
			nameProduct = request.getParameter("searching");
			break;
		case Constant.MODE_LG:
			brandName = Constant.MODE_LG;
			nameProduct = request.getParameter("searching");
			break;

		case Constant.MODE_PAGING:
			brandName = (String) session.getAttribute("brandName");
			nameProduct = (String) session.getAttribute("nameProduct");
			System.out.println(brandName + "  va  " + nameProduct);
			if (brandName == null) {
				brandName = "";
			}
			currentPage = Common.convertStringToInt(request.getParameter("currentPage"), currentPage);
		}
		System.out.println(brandName + "1234" + nameProduct);
		try {
			totalItems = sr.getAllProduct(brandName);
			if (totalItems == 0) {

			} else {
				int totalPage = Common.getTotalPage(totalItems, limit);
				if (currentPage > totalPage) {
					currentPage = totalPage;
				}
				offset = Common.getOffSet(currentPage, limit);
				listProducts = sr.getListItems(offset, limit, brandName, nameProduct);
				List<Integer> listPaging = Common.getListPaging(totalItems, limit, currentPage);

				if (listPaging.size() != 0) {
					if (listPaging.get(0) > Constant.DEFAULT_PAGE) {
						request.setAttribute("PageBack", listPaging.get(0) - Constant.LIMIT_PAGE);
						request.setAttribute("SymbolBack", Constant.BACK_SYMBOL);
					}
				}

				if (listPaging.get(listPaging.size() - 1) < totalPage) {
					request.setAttribute("PageNext", listPaging.get(listPaging.size() - 1) + 1);
					request.setAttribute("SymbolNext", Constant.NEXT_SYMBOL);
				}

				request.setAttribute("listPaging", listPaging);
			}

			// Set các giá trị lên session để dùng trong trường hợp back
			session.setAttribute("brandName", brandName);
			session.setAttribute("currentPage", currentPage);
			session.setAttribute("nameProduct", nameProduct);

			// Set các giá trị lên request để bên jsp nhận dữ liệu
			request.setAttribute("brandName", brandName);
			request.setAttribute("listProducts", listProducts);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("nameProduct", nameProduct);

			RequestDispatcher rq = request.getRequestDispatcher(Constant.FILE_JSP_PATH + Constant.HOME_PATH);
			rq.forward(request, response);
		} catch (Exception e) {

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
