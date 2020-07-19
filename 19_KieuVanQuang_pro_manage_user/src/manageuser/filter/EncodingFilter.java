/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [FilterForRequest.java], [April 5, 2020] [Kiều Văn Quang]
 */
package manageuser.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class FilterForRequest dùng để encoding cho các request, response
 * 
 * @author Kiều Văn Quang
 *
 */
public class EncodingFilter implements Filter {

	/**
	 * Phương thức doFilter 
	 * Truyền vào request, response, và chain
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Ép kiểu cho ServletRequest thành HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// Set encoding dạng UTF-8 cho request và response
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		// Cho đi qua filter
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

}
