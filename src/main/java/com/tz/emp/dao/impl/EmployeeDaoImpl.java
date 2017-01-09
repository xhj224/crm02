package com.tz.emp.dao.impl;

import com.tz.emp.dao.IEmployeeDao;
import com.tz.entity.Employee;
import com.tz.util.HibernateTemplates;
import com.tz.util.IHibernateCallBack;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class EmployeeDaoImpl implements IEmployeeDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> selectAllEmps() {
        return (List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {
            @Override
            public Object execute(Session ses) throws HibernateException {
                return ses.createQuery("from Employee").list();
            }
        });
    }

    @Override
    public boolean deleteEmpById(final Long id) {
        return (boolean) HibernateTemplates.execute(new IHibernateCallBack() {
            @Override
            public Object execute(Session ses) throws HibernateException {
                boolean bool = false;
                Employee employee = (Employee) ses.get(Employee.class, id);
                if (employee != null) {
                    ses.delete(employee);
                    bool = true;
                }
                return bool;
            }
        });
    }

    @Override
    public boolean insertEmp(final Employee employee) {
        try {
            return (boolean) HibernateTemplates.execute(new IHibernateCallBack() {
                @Override
                public Object execute(Session ses) throws HibernateException {
                    if (employee != null) {
                        ses.saveOrUpdate(employee);
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public Employee selectEmpById(final Long id) {
        return (Employee) HibernateTemplates.execute(new IHibernateCallBack() {
            @Override
            public Object execute(Session ses) throws HibernateException {
                return ses.createQuery("from Employee where id=:id").setParameter("id", id).uniqueResult();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> selectEmpByName(final String name) {
        return (List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {

            @Override
            public Object execute(Session ses) throws HibernateException {
                return ses.createQuery("from Employee where name like :name").setParameter("name", "%" + name + "%")
                        .list();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> selectEmpByTitle(final String title) {
        return (List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {
            @Override
            public Object execute(Session ses) throws HibernateException {
                return ses.createQuery("from Employee where title=:title").setParameter("title", title).list();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> selectEmpBySalary(final double sSalary, final double eSalary) {
        return (List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {
            @Override
            public Object execute(Session ses) throws HibernateException {
                // TODO Auto-generated method stub
                return ses.createQuery("from Employee where salary between :sSalary and :eSalary")
                        .setParameter("sSalary", sSalary).setParameter("eSalary", eSalary).list();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> selectEmpByHiredate(final Date sHiredate, final Date eHiredate) {
        return (List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {
            @Override
            public Object execute(Session ses) throws HibernateException {
                return ses.createQuery("from Employee where hiredate between :sHiredate and :eHiredate")
                        .setParameter("sHiredate", sHiredate).setParameter("eHiredate", eHiredate).list();
            }
        });
    }

    @Override
    public boolean deleteEmps(final List<Long> idList) {
        try {
            return (boolean) HibernateTemplates.execute(new IHibernateCallBack() {
                @Override
                public Object execute(Session ses) throws HibernateException {
                    if (idList != null && idList.size() > 0) {
                        ses.createQuery("delete from Employee where id in (:ids)").setParameterList("ids", idList)
                                .executeUpdate();
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> pageByEmployees(final int start, final int rows) {
        return (List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {
            @Override
            public Object execute(Session ses) throws HibernateException {
                return ses.createQuery("from Employee").setFirstResult((start - 1) * rows).setMaxResults(rows).list();
            }
        });
    }

    @Override
    public Long getRowCount() {
        return (Long) HibernateTemplates.execute(new IHibernateCallBack() {
            @Override
            public Object execute(Session ses) throws HibernateException {
                return ses.createQuery("select count(*) from Employee").uniqueResult();
            }
        });
    }

    @Override
    public boolean updateSalaryById(Long id, double salary) {
        try {
            return (boolean) HibernateTemplates.execute(ses -> {
                ses.createQuery("update Employee set salary=:salary where id=:id").setParameter("salary", salary).setParameter("id", id).executeUpdate();
                return true;
            });
        } catch (Exception e) {
            return false;
        }
    }
}
