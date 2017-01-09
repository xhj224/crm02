package com.tz.emp.service.impl;

import com.tz.emp.dao.IEmployeeDao;
import com.tz.emp.service.IEmployeeService;
import com.tz.entity.Employee;
import com.tz.entity.EmployeePageBean;
import com.tz.util.BeanFactory;

import java.util.Date;
import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService {

    private IEmployeeDao employeeDao = (IEmployeeDao) BeanFactory.getBean("employeeDao");

    @Override
    public List<Employee> findAllEmps() {
        return employeeDao.selectAllEmps();
    }

    @Override
    public boolean removeEmpById(Long id) {
        return employeeDao.deleteEmpById(id);
    }

    @Override
    public boolean addEmp(Employee employee) {
        return employeeDao.insertEmp(employee);
    }

    @Override
    public Employee findEmpById(Long id) {
        return employeeDao.selectEmpById(id);
    }

    @Override
    public List<Employee> findEmployessByName(String name) {
        return employeeDao.selectEmpByName(name);
    }

    @Override
    public List<Employee> findEmployessByTitle(String title) {
        return employeeDao.selectEmpByTitle(title);
    }

    @Override
    public List<Employee> findEmployessBySalary(double sSalary, double eSalary) {
        return employeeDao.selectEmpBySalary(sSalary, eSalary);
    }

    @Override
    public List<Employee> findEmployessByHiredate(Date sHiredate, Date eHiredate) {
        return employeeDao.selectEmpByHiredate(sHiredate, eHiredate);
    }

    @Override
    public boolean beachRemoveEmps(List<Long> idList) {
        return employeeDao.deleteEmps(idList);
    }

    @Override
    public Long getRowCounts() {
        return employeeDao.getRowCount();
    }

    @Override
    public EmployeePageBean pageBeanEmployees(int start, int rows) {
        EmployeePageBean employeePageBean = new EmployeePageBean();
        employeePageBean.setEmployees(employeeDao.pageByEmployees(start, rows));
        employeePageBean.setPageSize(rows);
        employeePageBean.setRowCount(getRowCounts());
        employeePageBean.setTotalPage(getRowCounts());
        employeePageBean.setCurrentPage(start);
        employeePageBean.setNextPage(start);
        employeePageBean.setPreviousPage(start);
        return employeePageBean;
    }

    @Override
    public boolean editSalaryById(Long id, double salary) {
        return employeeDao.updateSalaryById(id, salary);
    }


}
