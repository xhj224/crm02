package com.tz.emp.dao;

import com.tz.entity.Employee;

import java.util.Date;
import java.util.List;

public interface IEmployeeDao {

    /**
     * 查询所有员工信息
     */
    List<Employee> selectAllEmps();

    boolean deleteEmpById(final Long id);

    boolean insertEmp(Employee employee);

    Employee selectEmpById(Long id);

    List<Employee> selectEmpByName(String name);

    List<Employee> selectEmpByTitle(String title);

    List<Employee> selectEmpBySalary(double sSalary, double eSalary);

    List<Employee> selectEmpByHiredate(Date sHiredate, Date eHiredate);

    boolean deleteEmps(List<Long> idList);

    List<Employee> pageByEmployees(int start, int rows);

    Long getRowCount();

    boolean updateSalaryById(Long id, double salary);
}
