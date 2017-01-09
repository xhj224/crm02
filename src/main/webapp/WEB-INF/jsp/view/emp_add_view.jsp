<%--@elvariable id="employee" type="com.tz.entity.Employee"--%>
<%--
  Created by IntelliJ IDEA.
  User: xhj224
  Date: 2016/12/15
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>添加员工</title>
    <link type="text/css" rel="stylesheet" href="${base}/style/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${base}/style/style2.css">
    <script src="${base}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a href="javascript:void(0);" class="navbar-brand">添加员工界面</a>
            </div>
        </div>
    </nav>
    <form role="form" class="col-md-offset-5" method="post" action="${base}/permission/emp">
        <div class="row">
            <div class="form-group col-md-4">
                <label for="username">用户名</label>
                <input type="text" name="name" class="form-control" id="username" placeholder="请输入用户名"
                <c:if test="${!empty employee.name}">
                       value="${employee.name}"
                </c:if>
                >
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <label for="title">职称</label>
                <select class="form-control" name="title" id="title">
                    <option value="首席执行官"
                            <c:if test="${employee.title=='首席执行官'}">
                                selected
                            </c:if>
                    >首席执行官
                    </option>
                    <option value="总裁"
                            <c:if test="${employee.title=='总裁'}">
                                selected
                            </c:if>
                    >总裁
                    </option>
                    <option value="总经理"
                            <c:if test="${employee.title=='总经理'}">
                                selected
                            </c:if>
                    >总经理
                    </option>
                    <option value="经理"
                            <c:if test="${employee.title=='经理'}">
                                selected
                            </c:if>
                    >经理
                    </option>
                    <option value="设计总监"
                            <c:if test="${employee.title=='设计总监'}">
                                selected
                            </c:if>
                    >设计总监
                    </option>
                    <option value="设计师"
                            <c:if test="${employee.title=='设计师'}">
                                selected
                            </c:if>
                    >设计师
                    </option>
                    <option value="设计助理"
                            <c:if test="${employee.title=='设计助理'}">
                                selected
                            </c:if>
                    >设计助理
                    </option>
                    <option value="后勤"
                            <c:if test="${employee.title=='后勤'}">
                                selected
                            </c:if>
                    >后勤
                    </option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <label for="salary">薪资</label>
                <input type="text" name="salary" class="form-control" id="salary" placeholder="请输入薪资"
                       onkeyup="value=value.replace(/[^\d.]/g,'')"
                <c:if test="${!empty employee.salary}">
                       value="${employee.salary}"
                </c:if>
                >
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <label for="hiredate">入职日期</label>
                <input type="text" name="hiredate" class="form-control" id="hiredate" placeholder="请输入入职日期"
                       onclick="WdatePicker({maxDate:'%y-%M-%d'})"
                <c:if test="${!empty employee.hiredate}">
                       value="${employee.hiredate}"
                </c:if>
                >
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <input type="hidden" name="action" value="addEmp">
                <input type="hidden" name="id" value="${employee.id}">
                <button type="submit" class="btn btn-default ">确定</button>
                <button type="reset" class="btn btn-default ">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>