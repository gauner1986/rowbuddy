package de.rowbuddy.server.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/SessionFilter")
public class SessionFilter implements Filter {

	private FilterConfig filterConfig;
    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean authorized = false;
	    if (request instanceof HttpServletRequest) {
	        HttpSession session = ((HttpServletRequest)request).getSession(false);
	        if (session != null) {
	            if (session.getAttribute("rbf") != null){
	                authorized = true;
	            }
	        }
	    }
	            
	    if (authorized) {
	        chain.doFilter(request, response);
	        return;
	    } else if (filterConfig != null) {
	        String login_page = filterConfig.getInitParameter("login_page");
	        if (login_page != null && !"".equals(login_page)) {
	            filterConfig.getServletContext().getRequestDispatcher(login_page).forward(request, response);
	            return;
	        }
	    }
	    
	    throw new ServletException("Unauthorized access, unable to forward to login page");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
	}

}
