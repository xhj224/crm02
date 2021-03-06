package com.tz.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tz.entity.User;

@WebFilter(filterName = "PermissionFilter", urlPatterns = { "/permission/*" }, initParams = {
		@WebInitParam(name = "errorPage", value = "/user?action=login&info=3") })
public class PermissionFilter implements Filter {
	private FilterConfig config;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (hasPrivilege(req)) {
			chain.doFilter(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + config.getInitParameter("errorPage"));
		}
	}

	@Override
	public void destroy() {
	}

	private boolean hasPrivilege(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return true;
		}
		return false;
	}
}
