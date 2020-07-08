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
	public List<Product> searchingItems(String name) throws Exception{
		list = new ListProductDAO();
		List<Product> listItems = list.search(name);
		return listItems;
	}
}
