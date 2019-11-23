package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() { }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String username = null;
		HttpSession sess = ((HttpServletRequest) request).getSession(false);

		if (sess != null) {
			username = (String) sess.getAttribute("loggedUser");
		}

		if (username == null) {
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/login.jsf");
		} else {
			chain.doFilter(request, response);
		}
	}
	
	public void init(FilterConfig arg0) throws ServletException { }

}