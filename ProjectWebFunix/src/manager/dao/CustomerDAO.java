/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [CustomerDAO.java], [Jul 15, 2020] [Kiều Văn Quang]
 */
package manager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import manager.model.Account;

/**
 * @author Kiều Văn Quang
 *
 */
public class CustomerDAO extends BaseDAO {
	public boolean signUpAccount(Account acc) throws Exception {
		boolean check = false;
		try {
			openConnection();
			if (conn != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO account ");
				sql.append("VALUES(?, ?, ?, ?, ?, ?);");
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				int i = 1;
				pre.setString(i++, acc.getEmail());
				pre.setString(i++, acc.getPassword());
				pre.setInt(i++, acc.getRole());
				pre.setString(i++, acc.getAccountName());
				pre.setString(i++, acc.getAddress());
				pre.setString(i++, acc.getPhone());
				pre.executeUpdate();
			}
			check = true;
		} catch (SQLException e) {
			// Thông báo lỗi
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = this.getClass().getName();
			System.out.println(className + ". " + methodName + ". Error : " + e.getMessage());
			throw e;
		}
		return check;
	}
	
//	public static void main(String[] args) {
//		CustomerDAO c = new CustomerDAO();
//		Account a = new Account("quangNV", "ohio@hh.oo", "HG", "0625", "quanghh", 1);
//		try {
//			System.out.println(c.signUpAccount(a));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
