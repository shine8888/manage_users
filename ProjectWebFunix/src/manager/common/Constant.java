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
	public final static String USER = "root";
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
	public static final String CART_PATH = "/Cart.jsp";

	public static final String MESSAGE_PROPERTIES = "message_error_ja.properties";

	public static final String ER001_LOGIN_NAME = "ER001_LOGIN_NAME";
	public static final String ER006_LOGIN_NAME = "ER006_LOGIN_NAME";
	public static final String ER019_LOGIN_NAME = "ER019_LOGIN_NAME";
	public static final String ER003_LOGIN_NAME = "ER003_LOGIN_NAME";
	public static final String ER005_LOGIN_NAME = "ER005_LOGIN_NAME";

	public static final String ER001_EMAIL = "ER001_EMAIL";
	public static final String ER006_EMAIL = "ER006_EMAIL";
	public static final String ER005_EMAIL = "ER005_EMAIL";
	public static final String ER003_EMAIL = "ER003_EMAIL";

	public static final String ER001_PHONE = "ER001_PHONE";
	public static final String ER006_PHONE = "ER006_PHONE";
	public static final String ER005_PHONE = "ER005_PHONE";
	public static final String ER003_PHONE = "ER003_PHONE";

	public static final String ER001_ADDRESS = "ER001_ADDRESS";

	public static final String ER001_PASSWORD = "ER001_PASSWORD";
	public static final String ER006_PASSWORD = "ER006_PASSWORD";
	public static final String ER017_CONFIRM_PASSWORD = "ER017_CONFIRM_PASSWORD";

}
