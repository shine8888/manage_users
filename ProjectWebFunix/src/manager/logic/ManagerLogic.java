/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [ManagerLogic.java], [Jul 16, 2020] [Kiều Văn Quang]
 */
package manager.logic;

import manager.dao.ManagerDAO;
import manager.model.Account;

/**
 * @author Kiều Văn Quang
 *
 */
public class ManagerLogic {
	public ManagerDAO md = new ManagerDAO();

	public boolean checkExistAccount(String username) {
		boolean check = false;
		try {
			Account acc = md.getExistAccountName(username);
			if (acc != null && username.equals(acc.getAccountName())) {
				check = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public boolean checkExistEmail(String email, int role) {
		boolean check = false;
		try {
			Account acc = md.getExistEmail(email, role);
			if (acc != null && email.equals(acc.getEmail())) {
				check = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public boolean checkLogin(String userName, String password) {
		return md.checkExistLoginId(userName, password);
	}
}
