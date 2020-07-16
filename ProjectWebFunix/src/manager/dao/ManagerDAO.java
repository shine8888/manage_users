/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [ManagerDAO.java], [Jul 15, 2020] [Kiều Văn Quang]
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
public class ManagerDAO extends BaseDAO {
	/**
	 * 
	 * @param accountName
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public Account getExistAccountName(String accountName, int role) throws Exception {
		Account acc = null;
		try {
			openConnection();
			if (conn != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT a.user_name, a.account_role, a.password  ");
				sql.append("FROM account a ");
				sql.append("WHERE user_name = ? ");
				sql.append("AND account_role = ?;");
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				int i = 1;
				pre.setString(i++, accountName);
				pre.setInt(i++, role);
				ResultSet rs = pre.executeQuery();
				while (rs.next()) {
					acc = new Account();
					acc.setAccountName(rs.getString("user_name"));
					acc.setPassword(rs.getString("password"));
					acc.setRole(rs.getInt("account_role"));
				}
			}
		} catch (SQLException e) {
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
		return acc;
	}
	
	/**
	 * 
	 * @param email
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public Account getExistEmail(String email, int role) throws Exception {
		Account acc = null;
		try {
			openConnection();
			if (conn != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT a.user_name, a.account_role, a.password  ");
				sql.append("FROM account a ");
				sql.append("WHERE user_mail = ? ");
				sql.append("AND account_role = ?;");
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				int i = 1;
				pre.setString(i++, email);
				pre.setInt(i++, role);
				ResultSet rs = pre.executeQuery();
				while (rs.next()) {
					acc = new Account();
					acc.setAccountName(rs.getString("user_name"));
					acc.setPassword(rs.getString("password"));
					acc.setRole(rs.getInt("account_role"));
				}
			}
		} catch (SQLException e) {
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
		return acc;
	}
	
	/**
	 * 
	 * @param phone
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public Account getExistPhone(String phone, int role) throws Exception {
		Account acc = null;
		try {
			openConnection();
			if (conn != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT a.user_name, a.account_role, a.password  ");
				sql.append("FROM account a ");
				sql.append("WHERE user_phone = ? ");
				sql.append("AND account_role = ?;");
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				int i = 1;
				pre.setString(i++, phone);
				pre.setInt(i++, role);
				ResultSet rs = pre.executeQuery();
				while (rs.next()) {
					acc = new Account();
					acc.setAccountName(rs.getString("user_name"));
					acc.setPassword(rs.getString("password"));
					acc.setRole(rs.getInt("account_role"));
				}
			}
		} catch (SQLException e) {
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
		return acc;
	}
}
