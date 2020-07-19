/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [TblDetailUserJapanLogic.java], [Apr 7, 2020] [Kiều Văn Quang]
 */
package manageuser.logics;


import manageuser.entities.TblDetailUserJapanEntity;

/**
 * Interface TblDetailUserJapanLogic khởi tạo các logic với bảng
 * TblDetailUserJapan
 * 
 * @author Kiều Văn Quang
 *
 */
public interface TblDetailUserJapanLogic {
	/**
	 * Hàm dùng để kiểm tra trình độ tiếng Nhật
	 * 
	 * @param inputUser		Truyền vào đối tượng nhập từ bàn phím
	 * @param dbUser		Truyền vào đối tượng lấy ra từ DB
	 * @return int			Trả về một giá trị kiểu int
	 */
	public int checkTblDetailUserJapan(TblDetailUserJapanEntity inputUser, TblDetailUserJapanEntity dbUser);
	
	/**
	 * Hàm dùng để kiểm tra trình độ tiếng Nhật
	 * 
	 * @param id			Truyền vào id của user
	 * @return boolean 		Trả về giá trị true/false
	 */
	public boolean checkExistTblDetailUserJapan(int id);
}
