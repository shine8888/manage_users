/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [ListProductDAO.java], [Jun 12, 2020] [Kiều Văn Quang]
 */
package manager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manager.model.Product;

/**
 * @author Kiều Văn Quang
 *
 */
public class ListProductDAO extends BaseDAO{
	
public List<Product> search(String name) throws Exception {
	List<Product> listItems = new ArrayList<Product>();
	try {
		openConnection();
		if(conn != null) {
			// tạo câu truy vấn
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT p.product_name, p.product_des, product_price, product_img_source, product_type, product_band ");
			sql.append("FROM shoppingdb.products p ");
			sql.append("WHERE p.product_name like concat('%',?,'%');");
			
			// tạo preparedStatment
			PreparedStatement pre = conn.prepareStatement(sql.toString());
			pre.setString(1,name);
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				Product p = new Product();
				p.setName(rs.getString("product_name"));
				p.setPrice(rs.getInt("product_price"));
				p.setSrc(rs.getString("product_img_source"));
				p.setBrand(rs.getString("product_band"));
				listItems.add(p);
			}
		}
	} catch (SQLException | ClassNotFoundException e) {
		// in ra log
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String className = this.getClass().getName();
		System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
		throw e;
	} finally {
		// đóng kết nối
		closeConnection();
	}
	return listItems;
}

public Product getProduct(String name) {
	return null;
	
}

}
