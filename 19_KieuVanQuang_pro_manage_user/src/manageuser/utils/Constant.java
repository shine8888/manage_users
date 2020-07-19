/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [Constant.java], [Mar 13, 2020] [Kiều Văn Quang]
 */
package manageuser.utils;

/**
 * Chứa các file là hằng số cần dùng
 * 
 * @author Kiều Văn Quang
 *
 */
public class Constant {
	// Các hằng là tên đường dẫn các file Properties
	public static final String PROPERTIES_DATABASE_PATH = "database.properties";
	public static final String PROPERTIES_ERROR_MESSAGE_PATH = "message_error_ja.properties";
	public static final String PROPERTIES_PAGING_PATH = "Paging.properties";
	public static final String PROPERTIES_CONFIG = "config.properties";
	public static final String PROPERTIES_MESSAGE ="message_msg_ja.properties";

	// Các hằng là đường dẫn của các file JSP
	public static final String FILE_JSP_PATH = "View/jsp";
	public static final String ADM001_PATH = "/ADM001.jsp";
	public static final String ADM002_PATH = "/ADM002.jsp";
	public static final String ADM003_PATH = "/ADM003.jsp";
	public static final String ADM004_PATH = "/ADM004.jsp";
	public static final String ADM005_PATH = "/ADM005.jsp";
	public static final String ADM006_PATH = "/ADM006.jsp";
	public static final String URL_ERROR_PAGE = "/System_Error.jsp";

	// Các hằng là đường dẫn của các URL servlet
	public static final String URL_LOG_IN = "/Login.do";
	public static final String URL_LOG_OUT = "/Logout.do";
	public static final String URL_LIST_USER = "/ListUser.do";
	public static final String URL_SUCCESS_CONTROLLER = "/SuccessController.do";
	public static final String URL_SYSTEM_ERROR_CONTROLLER = "/SystemError.do";
	public static final String URL_EDIT_USER_CONTROLLER = "/EditDetailUser.do";
	public static final String URL_EDIT_CONFIRM_CONTROLLER = "/EditUserConfirm.do";
	public static final String URL_ADD_USER_CONFIRM = "/AddUserConfirm.do";

	// Các hằng gán kiểu giá trị sắp xếp
	public static final String ASC_SORT = "ASC";
	public static final String DESC_SORT = "DESC";

	// Các hằng gán giá trị mặc định
	public static final int OFFSET_DEFAULT = 0;
	public static final int GROUP_ID_DEFAULT = 0;
	public static final int TOTAL_USER_DEFAULT = 0;
	public static final String SORT_TYPE_DEFAULT = "fullName";
	public static final String SEARCHING_DEFAULT = "";
	public static final int DEFAULT_PAGE = 1;
	public static final int LIMIT_PAGE = 3;
	public static final String LIMIT = ConfigProperties.getValueByKey("LIMIT");
	public static final String SORT_NAME_DEFAULT = ConfigProperties.getValueByKey("SORT_NAME_DEFAULT");
	public static final String SORT_LEVEL_DEFAULT = ConfigProperties.getValueByKey("SORT_LEVEL_DEFAULT");
	public static final String SORT_ENDDATE_DEFAULT = ConfigProperties.getValueByKey("SORT_ENDDATE_DEFAULT");
	public static final String DEFAULT_SORT_TYPE = "fullName";


	// Các hằng gán các giá trị cho Switch Case Mode
	public static final String DEFAULT_MODE = "default";
	public static final String SORT_MODE = "Sort";
	public static final String PAGING_MODE = "Paging";
	public static final String SEARCHING_MODE = "Search";
	public static final String BACK_MODE = "Back";
	public static final String BACK_SYMBOL = "<<";
	public static final String NEXT_SYMBOL = ">>";
	// Các hằng gán giá trị cho các trường hợp Sort
	public static final String SORT_TYPE_NAME = "fullName";
	public static final String SORT_TYPE_LEVEL = "nameLevel";
	public static final String SORT_TYPE_END_DATE = "endDate";

	// Các hằng gán giá trị cho trường hợp thêm/back ở màn hình ADM003
	public static final String DEFAULT_TYPE = "default";
	public static final String SUBMIT_TYPE = "submit";
	public static final String BACK_TYPE = "back";
	// Các hằng là tên bảng trong database
	public static final String TABLE_USER_NAME = "tbl_user";
	public static final String TABLE_DETAIL_NAME = "tbl_detail_user_japan";
	public static final String TABLE_MST_JAPAN_NAME = "mst_japan";
	public static final String COLUMN_FULL_NAME = "full_name";
	public static final String COLUMN_LEVEL = "code_level";
	public static final String COLUMN_END_DATE = "end_date";
	public static final String DATABASE_NAME = "19_kieuvanquang_manageuser";

	// Các hằng lưu giá trị Rule cho trường hợp là admin và client
	public static final int RULE_USER = 1;
	public static final int RULE_ADMIN = 0;
	public static final int CASE_1 = 1;
	public static final int CASE_2 = 2;
	public static final int CASE_3 = 3;
	public static final int CASE_4 = 4;

	// Các hằng dùng cho màn hình ADM003
	public static final int YEAR_BEGINING = 1900;
	public static final int YEAR_ENDDATE = Common.getYearNow() + 1;
	public static final int YEAR_END = Common.getYearNow();

	// Các hằng lưu câu thông báo insert thành công hay thất bại ở ADM006 và System Error
	public static final String ADD = "add";
	public static final String EDIT = "edit";
	public static final String DELETE = "delete";
	public static final String LOGIN = "login";

	// Các hằng lưu giá trị cho switch case ở phần Edit User
	public static final String EDIT_MODE_05 = "Edit_05";
	public static final String EDIT_MODE_04 = "Edit_04";
	public static final String EDIT_MODE_03 = "Edit_03";

	// Các hằng lưu giá trị cho các type ADD và EDIT ở màn hình ADM003
	public static final String ADD_TYPE = "add";
	public static final String EDIT_TYPE = "edit";

	// Các hằng lưu giá trị cho các mã lỗi
	public static final String ER001_LOGIN_NAME = "ER001_LOGIN_NAME";
	public static final String ER007_LOGIN_NAME = "ER007_LOGIN_NAME";
	public static final String ER019_LOGIN_NAME = "ER019_LOGIN_NAME";
	public static final String ER003_LOGIN_NAME = "ER003_LOGIN_NAME";
	
	public static final String ER002_GROUP_NAME = "ER002_GROUP_NAME";
	public static final String ER004_GROUP_NAME = "ER004_GROUP_NAME";
	
	public static final String ER001_FULL_NAME = "ER001_FULL_NAME";
	public static final String ER006_FULL_NAME = "ER006_FULL_NAME";
	public static final String ER001_FULL_NAME_KANA = "ER001_FULL_NAME_KANA";
	public static final String ER009_FULL_NAME_KANA = "ER009_FULL_NAME_KANA";
	public static final String ER006_FULL_NAME_KANA = "ER006_FULL_NAME_KANA";
	
	public static final String ER001_BIRTH_DAY = "ER001_BIRTH_DAY";
	public static final String ER011_BIRTH_DAY = "ER011_BIRTH_DAY";
	
	public static final String ER001_EMAIL = "ER001_EMAIL";
	public static final String ER006_EMAIL = "ER006_EMAIL";
	public static final String ER005_EMAIL = "ER005_EMAIL";
	public static final String ER003_EMAIL = "ER003_EMAIL";
	
	public static final String ER001_TEL = "ER001_TEL";
	public static final String ER006_TEL = "ER006_TEL";
	public static final String ER005_TEL = "ER005_TEL";
	
	public static final String ER001_PASSWORD = "ER001_PASSWORD";
	public static final String ER007_PASSWORD = "ER007_PASSWORD";
	public static final String ER008_PASSWORD = "ER008_PASSWORD";
	public static final String ER001_CONFIRM_PASSWORD = "ER001_CONFIRM_PASSWORD";
	public static final String ER017_CONFIRM_PASSWORD = "ER017_CONFIRM_PASSWORD";

	public static final String ER004_LEVEL_NAME = "ER004_LEVEL_NAME";
	
	public static final String ER011_START_DATE = "ER011_START_DATE";
	public static final String ER011_END_DATE = "ER011_END_DATE";
	public static final String ER012_END_DATE = "ER012_END_DATE";

	public static final String ER001_TOTAL = "ER001_TOTAL";
	public static final String ER018_TOTAL = "ER018_TOTAL";
	public static final String ER006_TOTAL = "ER006_TOTAL";

	public static final String ER008 = "ER008";
	public static final String ER009 = "ER009";
	public static final String ER012 = "ER012";
	public static final String ER013 = "ER013";
	public static final String ER014 = "ER014";
	public static final String ER015 = "ER015";
	public static final String ER016 = "ER016";
	public static final String ER017 = "ER017";
	public static final String ER018 = "ER018";
	public static final String ER019 = "ER019";
	public static final String ER020 = "ER020";
	public static final String ER021 = "ER021";
	public static final String MSG001 = "MSG001";
	public static final String MSG002 = "MSG002";
	public static final String MSG003 = "MSG003";
	public static final String MSG004 = "MSG004";
	public static final String MSG005 = "MSG005";
	
	// Các hằng số lưu các giá trị default
	public static final int NUM_0 = 0;
	public static final int NUM_1 = 1;
	public static final int NUM_2 = 2;
	
	// Các hằng lưu giá trị cho các key xác nhận ở các màn hình
	public static final String KEY = "Quang";
	
	// Các hằng lưu giá trị default của trình độ tiếng nhật
	public static final String N0 = "N0";
}
