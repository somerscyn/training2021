package com.smbcgroup.training.jpa;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Employees")
public class Employee {
	
	@Id
	@Column
	private Integer id;
	
	@Column
	private String name;

	@Column
	private BigDecimal wage;

	@Column
	private Integer department_id;

	@ManyToOne
	@JoinColumn(name = "department_id", referencedColumnName = "id")

	@Column
	private Department department;

	
	public Employee() {
		
	}

	public Employee(Integer id, String name, BigDecimal wage, String department) {
		this.id = id;
		this.name = name;
		this.wage = wage;
		this.department_id = department_id
		this.department = department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public BigDecimal getWage() {
		return wage;
	}

	public void setWage(BigDecimal wage) {
		this.wage = wage;
	}

	public Integer getDepartment() {
		return department_id;
	}

	public void setDepartment(Integer department) {
		this.department_id = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
