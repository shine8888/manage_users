/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [SearchLogic.java], [Jul 9, 2020] [Kiều Văn Quang]
 */
package manager.logic;

import java.util.List;

import manager.dao.ListProductDAO;
import manager.model.Product;

/**
 * @author Kiều Văn Quang
 *
 */
public class SearchLogic {
	public ListProductDAO list;

	public List<Product> getListItems(int offset, int limit, String brandName, String nameProduct) throws Exception {
		list = new ListProductDAO();
		List<Product> listItems = list.getAllItems(offset, limit, brandName, nameProduct);
		return listItems;
	}

	public int getAllProduct(String name) throws Exception {
		list = new ListProductDAO();
		int total = list.getTotalItems(name);
		return total;
	}
	
	public Product getProduct(int id) throws Exception {
		list = new ListProductDAO();
		Product p = list.getProduct(id);
		return p;
	}
}
