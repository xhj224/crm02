package com.tz.emp.dao.impl;

import com.tz.emp.dao.IEmployeeDao;
import com.tz.entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class EmployeeDaoImpl implements IEmployeeDao {

    @Resource
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> selectAllEmps() {
        String hql = "from Employee";
        return getSession().createQuery(hql).list();
    }

    @Override
    public boolean deleteEmpById(final Long id) {
        boolean bool = false;
        Employee employee = (Employee) getSession().get(Employee.class, id);
        if (employee != null) {
            getSession().delete(employee);
            bool = true;
        }
        return bool;
    }

    @Override
    public boolean insertEmp(final Employee employee) {
        try {
            if (employee != null) {
                getSession().saveOrUpdate(employee);
                return true;
            } else {
                return false;
            }
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public Employee selectEmpById(final Long id) {
        String hql = "from Employee where id=:id";
        return (Employee) getSession().createQuery(hql).setParameter("id", id).uniqueResult();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> selectEmpByName(final String name) {
        String hql = "from Employee where name like :name";
        return getSession().createQuery(hql).setParameter("name", "%" + name + "%").list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> selectEmpByTitle(final String title) {
        String hql = "from Employee where title=:title";
        return getSession().createQuery(hql).setParameter("title", title).list();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> selectEmpBySalary(final double sSalary, final double eSalary) {
        String hql = "from Employee where salary between :sSalary and :eSalary";
        return getSession().createQuery(hql).setParameter("sSalary", sSalary).setParameter("eSalary", eSalary).list();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> selectEmpByHiredate(final Date sHiredate, final Date eHiredate) {
        String hql = "from Employee where hiredate between :sHiredate and :eHiredate";
        return getSession().createQuery(hql).setParameter("sHiredate", sHiredate).setParameter("eHiredate", eHiredate).list();

    }

    @Override
    public boolean deleteEmps(final List<Long> idList) {
        try {
            if (idList != null && idList.size() > 0) {
                String hql = "delete from Employee where id in (:ids)";
                getSession().createQuery(hql).setParameterList("ids", idList).executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> pageByEmployees(final int start, final int rows) {
        String hql = "from Employee";
        return getSession().createQuery(hql).setFirstResult((start - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public Long getRowCount() {
        String hql = "select count(*) from Employee";
        return (Long) getSession().createQuery(hql).uniqueResult();
    }

    @Override
    public boolean updateSalaryById(Long id, double salary) {
        try {
            String hql = "update Employee set salary=:salary where id=:id";
            getSession().createQuery(hql).setParameter("salary", salary).setParameter("id", id).executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
