package com.tz.entity;

import com.tz.util.DateUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SERVLET_EMPLOYEE")
public class Employee {
	private Long id;
	private String name;
	private String title;
	private double salary;
	private Date hiredate;

	public Employee() {
	}

	public Employee(String name, String title, double salary, Date hiredate) {
		super();
		this.name = name;
		this.title = title;
		this.salary = salary;
		this.hiredate = hiredate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "emp_id")
	@SequenceGenerator(name = "emp_id", sequenceName = "servlet_emp_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", title=" + title + ", salary=" + salary + ", hiredate="
				+ DateUtil.formatDate(hiredate, "yyyy-MM-dd") + "]";
	}

}
