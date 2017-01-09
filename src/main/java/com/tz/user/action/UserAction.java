package com.tz.user.action;

import com.tz.entity.User;
import com.tz.user.service.IUserService;
import com.tz.util.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Description:
 * Created by xhj224.
 * Date: 2016/12/15 10:22.
 * Project: crm1215.
 */
@WebServlet(
        name = "userAction",
        urlPatterns = {"/user"}
)
public class UserAction extends HttpServlet {
    private static final String DEFAULT_ACTION = "login";
    private IUserService userService = (IUserService) BeanFactory.getBean("userService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String base = req.getContextPath();
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        if (action == null || action.length() == 0) {
            action = DEFAULT_ACTION;
        }
        switch (action) {
            case "login":
                String info = (String) req.getAttribute("info");
                req.setAttribute("info", info);
                req.getRequestDispatcher("/WEB-INF/jsp/view/user_login_view.jsp").forward(req, resp);
                break;

            case "logout":
                if (session != null) {
                    session.invalidate();
                }
                resp.sendRedirect(base + "/user?action=login");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String base = req.getContextPath();

        String action = req.getParameter("action");
        if (action == null || action.length() == 0) {
            action = DEFAULT_ACTION;
        }
        switch (action) {
            case "login":
                req.setAttribute("action", "login");
                req.getRequestDispatcher("/user").forward(req, resp);
                break;

            case "loginCheck":
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String token = req.getParameter("token");
                String noLogin = req.getParameter("noLogin");

                String code = (String) getServletContext().getAttribute("token");

                if ((username != null && username.length() != 0) && (password != null && password.length() != 0) && (token != null && token.length() != 0)) {
                    Cookie cookie;
                    if (noLogin != null) {
                        //说明要七天免登陆
                        cookie = new Cookie("userInfo", URLEncoder.encode(username + ":" + password, "UTF-8"));
                        cookie.setMaxAge(7 * 24 * 60 * 60);
                        //设置Cookie作用域
                        cookie.setPath("/");
                        resp.addCookie(cookie);
                    }

                    //先比对验证码
                    if (code.equals(token)) {
                        User user = userService.login(username, password);
                        if (user != null) {
                            //把用户名放入到session数据范围
                            session.setAttribute("user", user);
                            session.setMaxInactiveInterval(60 * 15);
                            //说明登陆成功
                            resp.sendRedirect(base + "/permission/emp?action=list");
                        } else {
                            //说明登陆失败
                            resp.sendRedirect(base + "/user?action=login&info=1");
                        }
                    } else {
                        resp.sendRedirect(base + "/user?action=login&info=2");
                    }
                } else {
                    resp.sendRedirect(base + "/user?action=login");
                }
                break;
        }
    }
}
