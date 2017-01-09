package com.tz.test;

import com.tz.emp.dao.IEmployeeDao;
import com.tz.entity.Employee;
import com.tz.util.DateUtil;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(value = "classpath:applicationContext.xml")
public class TestEmployeeDaoImpl extends AbstractTestNGSpringContextTests {

    @Resource
    private IEmployeeDao employeeDao;

    @Test
    public void testSelectAllEmps() {
        List<Employee> employees = employeeDao.selectAllEmps();
        if (employees != null && employees.size() > 0) {
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }

    @Test
    public void testDeleteEmpById() {
        boolean bool = employeeDao.deleteEmpById(1L);
        System.out.println(bool);
    }

    @Test
    public void testInsertEmp() {
        Employee employee = new Employee("jack", "总经理", 18000, DateUtil.parseString("yyyy-MM-dd", "2012-1-1"));
        boolean bool = employeeDao.insertEmp(employee);
        System.out.println(bool);
    }

    @Test
    public void testSelectEmpById() {
        Employee employee = employeeDao.selectEmpById(1L);
        System.out.println(employee);
    }

    @Test
    public void testSelectEmpByName() {
        List<Employee> employees = employeeDao.selectEmpByName("j");
        System.out.println(employees);
    }

    @Test
    public void testSelectEmpByTitle() {
        List<Employee> employees = employeeDao.selectEmpByTitle("总经理");
        System.out.println(employees);
    }

    @Test
    public void testSelectEmpBySalary() {
        List<Employee> employees = employeeDao.selectEmpBySalary(8500, 15000);
        System.out.println(employees);
    }

    @Test
    public void testSelectEmpByHiredate() {
        List<Employee> employees = employeeDao.selectEmpByHiredate(DateUtil.parseString("yyyy-MM-dd", "2013-1-1"),
                DateUtil.parseString("yyyy-MM-dd", "2016-1-1"));
        System.out.println(employees);
    }

    @Test
    public void testDeleteEmps() {
        List<Long> ids = new ArrayList<Long>();
        ids.add(43L);
        ids.add(45L);
        boolean bool = employeeDao.deleteEmps(ids);
        System.out.println(bool);
    }

    @Test
    public void testPageByEmployees() {
        List<Employee> employees = employeeDao.pageByEmployees(2, 5);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void testGetRowCount() {
        Long count = employeeDao.getRowCount();
        System.out.println(count);
    }

    @Test
    public void testUpdateSalaryById() {
        Boolean bool = employeeDao.updateSalaryById(4L, 50);
        System.out.println(bool);
    }
}
