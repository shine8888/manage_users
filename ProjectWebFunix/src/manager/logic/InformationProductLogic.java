/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [InformationProductLogic.java], [Jul 12, 2020] [Kiều Văn Quang]
 */
package manager.logic;

import manager.dao.ListProductDAO;
import manager.model.Product;

/**
 * @author Kiều Văn Quang
 *
 */
public class InformationProductLogic {
	private ListProductDAO listDAO;

	public InformationProductLogic() {
		listDAO = new ListProductDAO();
	}

	public Product getInforProduct(int id) throws Exception {
		Product p = listDAO.getProduct(id);
		return p;
	}

	public static void main(String[] args) {
		InformationProductLogic i = new InformationProductLogic();
		try {
			Product p = i.getInforProduct(1);
			System.out.println(p.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
