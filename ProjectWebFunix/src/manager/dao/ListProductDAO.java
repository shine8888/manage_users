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
public class ListProductDAO extends BaseDAO {

	/**
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<Product> search(String name) throws Exception {
		List<Product> listItems = new ArrayList<Product>();
		try {
			openConnection();
			if (conn != null) {
				// tạo câu truy vấn
				StringBuilder sql = new StringBuilder();
				sql.append(
						"SELECT p.product_name, p.product_des, p.product_price, p.product_img_source, p.product_type, p.product_band ");
				sql.append("FROM shoppingdb.products p ");
				sql.append("WHERE p.product_name like concat('%',?,'%');");

				// tạo preparedStatment
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				pre.setString(1, name);
				ResultSet rs = pre.executeQuery();
				while (rs.next()) {
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

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Product getProduct(int id) throws Exception {
		Product p = new Product();
		try {
			openConnection();
			if (conn != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT p.product_id, p.product_name, p.product_des, p.product_price, p.product_img_source ");
				sql.append("FROM products p ");
				sql.append("WHERE product_id = ?;");
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				pre.setInt(1, id);
				ResultSet rs = pre.executeQuery();
				while (rs.next()) {
					p.setId(rs.getInt("product_id"));
					p.setName(rs.getString("product_name"));
					p.setDescription(rs.getString("product_des"));
					p.setPrice(rs.getInt("product_price"));
					p.setSrc(rs.getString("product_img_source"));
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
		return p;

	}

	/**
	 * 
	 * @param offset
	 * @param limit
	 * @param brandName
	 * @return
	 * @throws Exception
	 */
	public List<Product> getAllItems(int offset, int limit, String brandName, String nameProduct) throws Exception {
		List<Product> list = new ArrayList<Product>();
		try {
			openConnection();
			if (conn != null) {
				int count = 1;
				StringBuilder sql = new StringBuilder();
				sql.append(
						"SELECT p.product_id,p.product_name, p.product_des, p.product_price, p.product_img_source, p.product_type, p.product_band ");
				sql.append("FROM shoppingdb.products p ");
				sql.append("WHERE p.product_band like concat('%',?,'%') ");
				sql.append("And p.product_name like concat('%',?,'%') Limit ? Offset ? ;");
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				pre.setString(count++, brandName);
				pre.setString(count++, nameProduct);
				pre.setInt(count++, limit);
				pre.setInt(count++, offset);
				ResultSet rs = pre.executeQuery();
				while (rs.next()) {
					Product p = new Product();
					p.setId(rs.getInt("product_id"));
					p.setName(rs.getString("product_name"));
					p.setDescription(rs.getString("product_des"));
					p.setPrice(rs.getInt("product_price"));
					p.setSrc(rs.getString("product_img_source"));
					p.setType(rs.getString("product_type"));
					p.setBrand(rs.getString("product_band"));
					list.add(p);
				}

			}
		} catch (SQLException | ClassNotFoundException e) {
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
			;
		}
		return list;
	}

	public int getTotalItems(String brandName) throws Exception {
		int total = 0;
		try {
			openConnection();
			if (conn != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT COUNT(product_band) ");
				sql.append("From products ");
				sql.append("WHERE product_band like concat('%',?,'%');");
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				pre.setString(1, brandName);
				ResultSet rs = pre.executeQuery();
				while (rs.next()) {
					total = Integer.parseInt(rs.getString(1));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối
			closeConnection();
		}
		// Trả về kết quả cho phương thức
		return total;

	}
	

}
