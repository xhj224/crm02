<%--
  Created by IntelliJ IDEA.
  User: xhj224
  Date: 2016/12/15
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="${base}/style/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${base}/style/style2.css">
    <script type="text/javascript" src="${base}/js/function.js"></script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a href="javascript:void(0);" class="navbar-brand">登录界面</a>
            </div>
        </div>
    </nav>
    <c:if test="${!empty param.info}">
        <div class="row div01">
            <div class="alert alert-danger" role="alert">
                <c:choose>
                    <c:when test="${param.info == 1}">
                        对不起,用户名或者密码有误,请重新输入!
                    </c:when>
                    <c:when test="${param.info == 2}">
                        对不起,验证码有误,请重新输入!
                    </c:when>
                    <c:when test="${param.info == 3}">
                        对不起,请先登陆!
                    </c:when>
                </c:choose>
            </div>
        </div>
    </c:if>
    <form role="form" class="col-md-offset-5" action="${base}/user" method="post">
        <div class="row">
            <div class="form-group col-md-4">
                <label for="username">用户名</label>
                <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名"
                <c:set var="newUserInfo" value="${mu:toUTF8(cookie.userInfo.value)}"/>
                <c:if test="${!empty fn:split(newUserInfo,':')[0]}">
                       value="${fn:split(newUserInfo,':')[0]}"
                </c:if>
                >
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <label for="password">密码</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"
                <c:set var="newUserInfo" value="${mu:toUTF8(cookie.userInfo.value)}"/>
                <c:if test="${!empty fn:split(newUserInfo,':')[1]}">
                       value="${fn:split(newUserInfo,':')[1]}"
                </c:if>
                >
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <label for="token">验证码</label>
                <input type="text" name="token" class="form-control" id="token" placeholder="请输入验证码">
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <img src="${base}/token" id="code">
                <a href="javascript:changeToken();">看不清，换一个</a>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <label class="checkbox-inline">
                    <input type="checkbox" name="noLogin" value="0">记住我
                </label>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <input class="sr-only" type="hidden" name="action" value="loginCheck">
                <button type="submit" class="btn btn-default ">确定</button>
                <button type="reset" class="btn btn-default ">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
