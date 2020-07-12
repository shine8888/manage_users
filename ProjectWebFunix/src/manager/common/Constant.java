/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [Constant.java], [Jun 13, 2020] [Kiều Văn Quang]
 */
package manager.common;

/**
 * @author Kiều Văn Quang
 *
 */
public class Constant {
	public final static String URL = "jdbc:mysql://localhost:3306/shoppingdb?useSSL=false&allowPublicKeyRetrieval=true";
	public final static String USER="root";
	public final static String PASSWORD = "12345";
	public final static String DRIVER = "com.mysql.jdbc.Driver";
	
	public final static int LIMIT_PAGE = 3;
	public final static int OFFSET = 0;
	public final static int LIMIT = 2;
	public final static int DEFAULT_PAGE = 1;
	public final static int TOTAL_ITEM_DEFAULT = 0;
	public final static String BRAND_NAME_DEFAULT = "";
	public final static String NAME_PRODUCT_DEFAULT = "";
	
	
	public final static String MODE_DEFAULT = "default";
	public final static String MODE_SEARCHING = "searching";
	public final static String MODE_APPLE = "Apple";
	public final static String MODE_SAMSUNG = "SamSung";
	public final static String MODE_XIAOMI = "XiaoMi";
	public final static String MODE_HUAWEI = "Huawei";
	public final static String MODE_LG = "LG";
	public final static String MODE_OPPO = "Oppo";
	public final static String MODE_NOKIA = "Nokia";
	public final static String MODE_PAGING = "paging";
	
	public final static String BACK_SYMBOL = "<<";
	public final static String NEXT_SYMBOL = ">>";
	
	public final static String URL_LIST_ITEMS = "/ListController";
	public static final String FILE_JSP_PATH = "View/jsp";
	public static final String HOME_PATH = "/Home.jsp";
	public static final String INFOR_PRODUCT_PATH = "/InforProduct.jsp";
	
}
