package com.tz.emp.service;

import com.tz.entity.Employee;
import com.tz.entity.EmployeePageBean;

import java.util.Date;
import java.util.List;

public interface IEmployeeService {
    /**
     * 查找所有员工信息
     */
    List<Employee> findAllEmps();

    boolean removeEmpById(Long id);

    boolean addEmp(Employee employee);

    Employee findEmpById(Long id);

    List<Employee> findEmployessByName(String name);

    List<Employee> findEmployessByTitle(String title);

    List<Employee> findEmployessBySalary(double sSalary, double eSalary);

    List<Employee> findEmployessByHiredate(Date sHiredate, Date eHiredate);

    boolean beachRemoveEmps(List<Long> idList);

    Long getRowCounts();

    EmployeePageBean pageBeanEmployees(int start, int rows);

    boolean editSalaryById(Long id, double salary);
}
