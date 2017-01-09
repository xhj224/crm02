package com.tz.emp.action;

import com.tz.emp.service.IEmployeeService;
import com.tz.entity.Employee;
import com.tz.entity.EmployeePageBean;
import com.tz.util.BeanFactory;
import com.tz.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Created by xhj224.
 * Date: 2016/12/15 21:32.
 * Project: crm1215.
 */
@WebServlet(
        name = "empAction",
        urlPatterns = {"/permission/emp"}
)
public class EmpAction extends HttpServlet {
    private static final String DEFAULT_ACTION = "list";
    private IEmployeeService employeeService = (IEmployeeService) BeanFactory.getBean("employeeService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String base = req.getContextPath();
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        if (action == null || action.length() == 0) {
            action = DEFAULT_ACTION;
        }
        PrintWriter out = resp.getWriter();
        switch (action) {
            case "addEmp":
                req.getRequestDispatcher("/WEB-INF/jsp/view/emp_add_view.jsp").forward(req, resp);
                break;
            case "updateEmp":
                String id = req.getParameter("id");
                Employee employee = employeeService.findEmpById(Long.parseLong(id));
                req.setAttribute("employee", employee);
                req.getRequestDispatcher("/WEB-INF/jsp/view/emp_add_view.jsp").forward(req, resp);
                break;
            case "deleteEmp":
                String id01 = req.getParameter("id");
                String pageSize01 = req.getParameter("pageSize");
                boolean result = employeeService.removeEmpById(Long.parseLong(id01));
                if (result) {
                    resp.sendRedirect(base + "/permission/emp?action=list&pageSize=" + pageSize01 + "");
                } else {
                    //发送错误码
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "删除失败...");
                    return;
                }
                break;
            case "deleteEmpForAjax":
                String id02 = req.getParameter("id");
                boolean result1 = employeeService.removeEmpById(Long.parseLong(id02));
                if (result1) {
                    out.println("1");
                } else {
                    out.println("0");
                }
                break;
            case "batchRemoveForAjax":
                String ids2 = req.getParameter("deleteEmp");
                System.out.println(ids2);
                List<Long> idsList = new ArrayList<>();
                String[] arrIds2 = ids2.split(":");
                for (String sid : arrIds2) {
                    System.out.println(sid);
                    idsList.add(Long.parseLong(sid));
                }
                System.out.println("idsList：" + idsList);
                boolean bool = employeeService.beachRemoveEmps(idsList);
                if (bool) {
                    out.println("1");
                } else {
                    out.println("0");
                }
                break;
            case "editSalaryForAjax":
                String id1 = req.getParameter("id");
                String salary = req.getParameter("salary");
                boolean bool1 = employeeService.editSalaryById(Long.parseLong(id1), Double.parseDouble(salary));
                if (bool1) {
                    out.print("1");
                } else {
                    out.print("0");
                }
                break;
            case "list":
            default:
                // List<Employee> employees = employeeService.findAllEmps();
                // req.setAttribute("employees", employees);
                // req.getRequestDispatcher("/WEB-INF/jsp/view/emp_list_view.jsp").forward(req, resp);
                String pageNow = req.getParameter("pageNow");
                String pageSize = req.getParameter("pageSize");
                System.out.println(pageNow + ":" + pageSize);
                if (pageNow == null || pageNow.length() == 0) {
                    pageNow = "1";
                }
                if (pageSize == null || pageSize.length() == 0) {
                    pageSize = "2";
                }
                EmployeePageBean pageBean = employeeService.pageBeanEmployees(Integer.parseInt(pageNow), Integer.parseInt(pageSize));
                req.setAttribute("pageBean", pageBean);
                req.getRequestDispatcher("/WEB-INF/jsp/view/emp_list_view.jsp").forward(req, resp);
                break;
        }
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees;
        String base = req.getContextPath();
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        if (action == null || action.length() == 0) {
            action = DEFAULT_ACTION;
        }
        PrintWriter out = resp.getWriter();
        switch (action) {
            case "selectByName":
                String name = req.getParameter("name");
                employees = employeeService.findEmployessByName(name);
                req.setAttribute("employees", employees);
                req.getRequestDispatcher("/WEB-INF/jsp/view/emp_select_view.jsp").forward(req, resp);
                break;
            case "selectByTitle":
                String title = req.getParameter("title");
                employees = employeeService.findEmployessByTitle(title);
                req.setAttribute("employees", employees);
                req.getRequestDispatcher("/WEB-INF/jsp/view/emp_select_view.jsp").forward(req, resp);
                break;
            case "selectBySalary":
                String sSalary;
                String eSalary;
                sSalary = req.getParameter("sSalary");
                eSalary = req.getParameter("eSalary");
                double dsSalary = 0;
                double deSalary = 0;
                if (sSalary == null || sSalary.length() == 0) {
                    dsSalary = 0;
                } else {
                    dsSalary = Double.parseDouble(sSalary);
                }
                if (eSalary == null || eSalary.length() == 0) {
                    deSalary = Integer.MAX_VALUE;
                } else {
                    deSalary = Double.parseDouble(eSalary);
                }
                employees = employeeService.findEmployessBySalary(dsSalary, deSalary);
                req.setAttribute("employees", employees);
                req.getRequestDispatcher("/WEB-INF/jsp/view/emp_select_view.jsp").forward(req, resp);
                break;
            case "selectByHiredate":
                String sHiredate = null;
                String eHiredate = null;
                sHiredate = req.getParameter("sHiredate");
                eHiredate = req.getParameter("eHiredate");
                System.out.println(sHiredate + ":" + eHiredate);
                Date dsHireDate;
                Date deHireDate = null;
                if (sHiredate == null || sHiredate.length() == 0) {
                    dsHireDate = DateUtil.parseString("yyyy-MM-dd", "1970-1-1");
                } else {
                    dsHireDate = DateUtil.parseString("yyyy-MM-dd", sHiredate);
                }
                if (eHiredate == null || eHiredate.length() == 0) {
                    deHireDate = new Date();
                } else {
                    deHireDate = DateUtil.parseString("yyyy-MM-dd", eHiredate);
                }
                System.out.println(dsHireDate + ":" + deHireDate);
                employees = employeeService.findEmployessByHiredate(dsHireDate, deHireDate);
                req.setAttribute("employees", employees);
                req.getRequestDispatcher("/WEB-INF/jsp/view/emp_select_view.jsp").forward(req, resp);
                break;
            case "addEmp":
                String name1 = req.getParameter("name");
                String salaryString = req.getParameter("salary");
                String hiredateString = req.getParameter("hiredate");
                String title1 = req.getParameter("title");
                // System.out.println(name + ":" + salaryString + ":" + hiredateString + ":" + title);
                if ((name1 != null && name1.length() > 0) && (salaryString != null && salaryString.length() > 0)
                        && (hiredateString != null && hiredateString.length() > 0)
                        && (title1 != null && title1.length() > 0)) {

                    double salary = Double.parseDouble(salaryString);
                    Date hiredate = DateUtil.parseString("yyyy-MM-dd", hiredateString);
                    Employee employee = new Employee(name1, title1, salary, hiredate);
                    System.out.println(employee);
                    String id = req.getParameter("id");
                    if (id != null && id.length() != 0) {
                        employee.setId(Long.parseLong(id));
                    }
                    Boolean bool = employeeService.addEmp(employee);
                    if (bool) {
                        resp.sendRedirect(base + "/succeed.html");
                    } else {
                        resp.sendRedirect(base + "/error.html");
                    }
                } else {
                    resp.sendRedirect(base + "/error.html");
                }
                break;
            case "batchRemove":
                String[] ids = req.getParameterValues("deleteEmp");
                if (ids == null || ids.length == 0) {
                    resp.sendRedirect(base + "/error.html");
                } else {
                    List<Long> idsList = new ArrayList<>();
                    for (String sid : ids) {
                        idsList.add(Long.parseLong(sid));
                    }
                    boolean bool = employeeService.beachRemoveEmps(idsList);
                    if (bool) {
                        resp.sendRedirect(base + "/succeed.html");
                    } else {
                        //发送错误码
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "删除失败...");
                        return;
                    }
                }
                break;
            case "list":
            default:
                // List<Employee> employees = employeeService.findAllEmps();
                // req.setAttribute("employees", employees);
                String pageNow = req.getParameter("pageNow");
                String pageSize = req.getParameter("pageSzie");
                pageNow = pageNow == null ? "1" : pageNow;
                pageSize = pageSize == null ? "2" : pageSize;
                EmployeePageBean pageBean = employeeService.pageBeanEmployees(Integer.parseInt(pageNow), Integer.parseInt(pageSize));
                req.setAttribute("pageBean", pageBean);
                req.getRequestDispatcher("/WEB-INF/jsp/view/emp_list_view.jsp").forward(req, resp);
                break;
        }
        out.flush();
        out.close();
    }
}
