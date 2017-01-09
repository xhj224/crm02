package com.tz.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * 字符集过滤器
 *
 * @author xhj224
 */
@WebFilter(
        filterName = "characterEncodingFilter",
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "charset", value = "UTF-8")
        }
)
public class CharacterEncodingFilter implements Filter {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private String charset;

    @Override
    public void init(FilterConfig config) throws ServletException {
        charset = config.getInitParameter("charset");
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
