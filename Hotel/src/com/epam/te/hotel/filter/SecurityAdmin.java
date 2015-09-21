package com.epam.te.hotel.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.te.hotel.controller.JspPageName;
import com.epam.te.hotel.manager.ConfigManager;
import com.epam.te.hotel.model.entity.Role;
import com.epam.te.hotel.model.entity.User;


/**
 * Servlet Filter implementation class SecurityAdmin
 */
public class SecurityAdmin implements Filter {
	
	public static final String USER = "user";
	public static final String ERROR_MESSAGE = "errorMessage";

    /**
     * Default constructor. 
     */
    public SecurityAdmin() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		User user = (User) session.getAttribute(USER);
		if (user != null) {
			Role user_role = user.getRole();
			if (!Role.ADMIN.equals(user_role)){
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.INDEX);
				request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance()
								.getProperty(ConfigManager.DOES_NOT_HAVE_ACCESS_LEVEL_MESSAGE));
				dispatcher.forward(request, response);
			}
			chain.doFilter(request, response);
		} else {
			request.setAttribute(ERROR_MESSAGE,	ConfigManager.getInstance().getProperty(
							ConfigManager.USER_NOT_FOUND_MESSAGE));
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.INDEX);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
