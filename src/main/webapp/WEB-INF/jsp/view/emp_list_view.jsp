<%--suppress ALL --%>
<%--@elvariable id="pageBean" type="com.tz.entity.EmployeePageBean"--%>
<%--
  Created by IntelliJ IDEA.
  User: xhj224
  Date: 2016/12/15
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>员工列表</title>
    <link type="text/css" rel="stylesheet" href="${base}/style/style.css">
    <script type="text/javascript" src="${base}/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${base}/js/function.js"></script>
</head>
<body>
<h1>员工信息</h1>
<hr>
<h3>欢迎您的访问</h3>
<a href="${base}/user?action=logout">退出登录</a>
<hr>
<form method="post" action="${base}/permission/emp">
    按员工姓名查询：<input type="text" name="name" title="">
    <input type="hidden" name="action" value="selectByName">
    <input type="submit" value="查询">
</form>
<hr>
<form method="post" action="${base}/permission/emp">
    <select name="title" required title="">
        <option value="首席执行官" selected>首席执行官</option>
        <option value="总裁">总裁</option>
        <option value="总经理">总经理</option>
        <option value="经理">经理</option>
        <option value="设计总监">设计总监</option>
        <option value="设计师">设计师</option>
        <option value="设计助理">设计助理</option>
        <option value="后勤">后勤</option>
    </select>
    <input type="hidden" name="action" value="selectByTitle">
    <input type="submit" value="查询">
</form>
<hr>
<form method="post" action="${base}/permission/emp">
    按员工薪资查询：
    <input type="text" name="sSalary" onkeyup="value=value.replace(/[^\d.]/g,'')" title="">
    ~
    <input type="text" name="eSalary" onkeyup="value=value.replace(/[^\d.]/g,'')" title="">
    <input type="hidden" name="action" value="selectBySalary">
    <input type="submit" value="查询">
</form>
<hr/>
<form method="post" action="${base}/permission/emp">
    按员工入职日期查询：
    <input id="d4311" class="Wdate" type="text"
           onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'%y-%M-%d\'}'})" title=""/>
    <input id="d4312" class="Wdate" type="text"
           onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'%y-%M-%d'})" title=""/>
    <input type="hidden" name="action" value="selectByHiredate">
    <input type="submit" value="查询">
</form>
<hr/>
<%--<form method="post" action="${base}/permission/emp">--%>
<table class='register' id="myTable">
    <tr>
        <th></th>
        <th>姓名</th>
        <th>职称</th>
        <th>薪资</th>
        <th>入职时间</th>
        <th>操作</th>
    </tr>
    <c:if test="${pageBean.employees!=null && fn:length(pageBean.employees)>0}" var="verifyEmp">
        <c:forEach items="${pageBean.employees}" var="emp" varStatus="status">
            <tr id="${emp.id}">
                <td><input type="checkbox" name="deleteEmp" value="${emp.id}" title=""></td>
                <td><a href="${base}/permission/emp?action=updateEmp&id=${emp.id}">${emp.name}</a></td>
                <td>${emp.title}</td>
                <td onclick="editTb('${emp.id}',this)">${emp.salary}</td>
                <td><fmt:formatDate value="${emp.hiredate}"/></td>
                <td><a href="javascript:void(0);" onclick="deleteEmpById('${emp.id}')">删除</a></td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${!verifyEmp}">
        <tr>
            <th colspan='6'>暂无员工信息...</th>
        </tr>
    </c:if>
</table>
<input type="hidden" name="action" value="batchRemove">
<input type="button" value="批量删除" onclick="deleteEmps()">
<%--</form>--%>

<%--分页组件--%>
<c:set var="url" value="${base}/permission/emp?action=list"/>
<div>
    <c:if test="${pageBean.currentPage>1}">
        <a href="${url}&pageNow=1&pageSize=${pageBean.pageSize}">首页</a>
        <a href="${url}&pageNow=${pageBean.previousPage}&pageSize=${pageBean.pageSize}">上一页</a>
    </c:if>
    <c:choose>
        <c:when test="${(pageBean.currentPage-3)>=0}">
            <c:set var="begin" value="${pageBean.previousPage}"/>
        </c:when>
        <c:otherwise>
            <c:set var="begin" value="1"/>
        </c:otherwise>
    </c:choose>
    <c:forEach var="p" begin="${begin}" end="${begin+2}">
        <c:if test="${p <= pageBean.totalPage}">
            <a href="${url}&pageNow=${p}&pageSize=${pageBean.pageSize}">${p}</a>
        </c:if>
    </c:forEach>
    <c:if test="${pageBean.currentPage != pageBean.totalPage && pageBean.totalPage !=0 }">
        <a href="${url }&pageNow=${pageBean.nextPage}&pageSize=${pageBean.pageSize}">下一页</a>
        <a href="${url }&pageNow=${pageBean.totalPage}&pageSize=${pageBean.pageSize}">尾页</a>
    </c:if>
    当前<span style="color: red">
    <c:if test="${pageBean.currentPage == 0}" var="isEmpty">
        0/0
    </c:if>
    <c:if test="${!isEmpty}">
        ${pageBean.currentPage}/${pageBean.totalPage}
    </c:if>
    </span>
    跳转到第<input type="text" id="goPage" style="width: 30px; text-align: center" title="">页
    <input type="button" value="GO" onclick="go();">
    每页显示: <select name="toSize" onchange="to(this);" title="">
    <option value="2"
            <c:if test="${pageBean.pageSize==2 }">
                selected
            </c:if>
    >2
    </option>
    <option value="5"
            <c:if test="${pageBean.pageSize==5 }">
                selected
            </c:if>
    >5
    </option>
    <option value="10"
            <c:if test="${pageBean.pageSize==10 }">
                selected
            </c:if>
    >10
    </option>
</select>条
</div>

<input type="button" onclick="window.location='${base}/permission/emp?action=addEmp'" value="添加员工">
</body>
<script type="text/javascript">
    function go() {
        var count = parseInt(document.getElementById("goPage").value);
        var reg = /^[1-9]\d*$/;
        var pageCount = parseInt("${pageBean.totalPage}");
        if (reg.test(count)) {
            if (count > pageCount) {
                alert("不能超过最大页数！！！");
            } else {
                window.location = "${url}&pageSize=${pageBean.pageSize}&pageNow=" + count;
            }
        } else {
            alert("请输入最大正整数！！！");
        }
    }

    function to(obj) {
        var value = obj.value;
        window.location = "${url}&pageNow=1&pageSize=" + value;
    }

    function AjaxFunction() {
        var xmlHttp;  // 缓存XHR对象便于 Ajax 使用
        try {
            xmlHttp = new XMLHttpRequest(); // Opera 8.0+, Firefox, Safari
        } catch (e) {
            // Internet Explorer Browsers
            try {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e) {
                    // 错误处理
                    alert("Your browser broke!");
                }
            }
        }
        return xmlHttp;
    }

    function rowsNumber(empId) {
        var trs = document.getElementsByTagName("tr");
        for (var i = 0; i < trs.length; i++) {
            if (trs[i].id == empId) {
                return i;
            }
        }
    }

    function deleteEmpById(empId) {
        var table = document.getElementById("myTable");
        var xmlHttp = AjaxFunction();
        var url = "${base}/permission/emp?action=deleteEmpForAjax&id=" + empId;
        xmlHttp.open("GET", url, true);
        xmlHttp.send();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                // 获取响应中的文本
                var result = xmlHttp.responseText;
                if (result == 1) {
                    // table.deleteRow(rowsNumber(empId));
                    location.reload();
                }
            }
        }
    }

    var flag = true;
    function editTb(id, obj) {
        if (flag) {
            var tempValue = obj.innerHTML;
            obj.innerHTML = "";
            var input = document.createElement("input");
            input.type = 'text';
            input.style.width = "60px";
            input.value = tempValue;
            obj.appendChild(input);
            input.select();

            flag = false;

            input.onkeydown = function (event) {
                var ev = window.event || event;
                var kc = ev.keyCode;
                if (kc == 13) {
                    var sal = this.value;
                    var xmlHttp;
                    if (window.XMLHttpRequest) {
                        xmlHttp = new XMLHttpRequest();
                    } else {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    var url = "${base}/permission/emp?action=editSalaryForAjax&id=" + id + "&salary=" + sal;
                    xmlHttp.open("get", url, true);
                    xmlHttp.send();

                    xmlHttp.onreadystatechange = function () {
                        if (xmlHttp.readyState == 4
                            && xmlHttp.status == 200) {
                            var s = xmlHttp.responseText;
                            alert(s);
                            if (s == "1") {
                                alert(111);
                                flag = true;
                                obj.removeChild(input);
                                obj.innerHTML = sal;
                                alert("修改成功!");
                            } else {
                                alert(222);
                                flag = true;
                                obj.removeChild(input);
                                obj.innerHTML = tempValue;
                                alert("修改失败,请重新修改!");
                            }
                        }
                    };
                }
                if (kc == 27) {
                    flag = true;
                    obj.removeChild(input);
                    obj.innerHTML = tempValue;
                }
            };
        }
    }

    <%--function editTb(obj) {--%>
    <%--var id = obj.id;--%>
    <%--var td01 = document.getElementById(id);--%>
    <%--td01.onclick = "";--%>
    <%--var value = td01.innerHTML;--%>
    <%--td01.innerHTML = "";--%>
    <%--var txt = document.createElement("input");--%>
    <%--txt.value = value;--%>
    <%--td01.appendChild(txt);--%>
    <%--txt.select();--%>

    <%--td01.onkeydown = function (event) {--%>
    <%--var ev = event || window.event;--%>
    <%--var keyCode = ev.keyCode || ev.which;--%>
    <%--if (keyCode == 13) {--%>
    <%--var xmlHttp = AjaxFunction();--%>
    <%--var url = "${base}/permission/emp?action=editSalaryForAjax&id=" + id + "&salary=" + txt.value;--%>
    <%--xmlHttp.open("GET", url, true);--%>
    <%--xmlHttp.send();--%>

    <%--xmlHttp.onreadystatechange = function () {--%>
    <%--if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {--%>
    <%--// 获取响应中的文本--%>
    <%--var result = xmlHttp.responseText;--%>
    <%--if (result == 1) {--%>
    <%--td01.innerHTML = txt.value;--%>
    <%--} else {--%>
    <%--td01.innerHTML = value;--%>
    <%--}--%>
    <%--}--%>
    <%--};--%>
    <%--td01.onclick = editTb;--%>
    <%--}--%>
    <%--if (keyCode == 27) {--%>
    <%--td01.innerHTML = value;--%>
    <%--td01.onclick = editTb;--%>
    <%--}--%>
    <%--}--%>
    <%--}--%>

    function deleteEmps() {
        var table = document.getElementById("myTable");
        var checkedBoxs = document.getElementsByName("deleteEmp");
        var empids = "";
        for (var i = checkedBoxs.length - 1; i >= 0; i--) {
            if (checkedBoxs[i].checked) {
                empids += checkedBoxs[i].value + ":";
            }
        }
        empids = empids.substring(0, empids.length - 1);

        var xmlHttp = AjaxFunction();
        var url = "${base}/permission/emp?action=batchRemoveForAjax&deleteEmp=" + empids;
        xmlHttp.open("GET", url, true);
        xmlHttp.send();

        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                // 获取响应中的文本
                var result = xmlHttp.responseText;
                if (result == 1) {
                    for (var i = checkedBoxs.length - 1; i >= 0; i--) {
                        if (checkedBoxs[i].checked) {
                            checkedBoxs[i].checked = "";
                        }
                    }
                    location.reload();
                }
            }
        }
    }
</script>
</html>
