package com.smbcgroup.training.jpa;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Department")
public class Department {

    @Id
	@Column
	private Integer id;
	
	@Column
	private String name;

    @Column(name = "manager_name")
    private String managerName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private List<Employee> employees;
	
	public Department() {
		
	}

	public Department(Integer id, String name) {
		this.id = id;
		this.name = name;
        this.managerName = managerName;
	}

	public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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
