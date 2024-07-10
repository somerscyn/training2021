package com.smbcgroup.training.jpa;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JPATest {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("derby-entities");
	private EntityManager em = emf.createEntityManager();
	
	@Before
	public void setup() {
		em.getTransaction().begin();
		populateTables();
		em.flush();
		em.getTransaction().commit();
		em.close();
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	private void populateTables() {
		Department treasury = new Department();
		treasury.setId(1);
		treasury.setName("Treasury");
		Employee alisonSmith = new Employee();
		alisonSmith.setId(1452);
		alisonSmith.setName("Alison SMith");
		alisonSmith.setWage(new BigDecimal(70000));
		alisonSmith.setDepartment("Treasury");
		em.persist(alisonSmith);

		Employee bobsmith = new Employee();
		bobsmith.setId(1453);
		bobsmith.setName("Bob Smith");
		bobsmith.setWage(new BigDecimal(40000));
		bobsmith.setDepartment("Treasury");
		em.persist(bobsmith);


		//em.persist(new Employee(1452,  "Alison Smith"));
	}
	

	@Test
	public void testFind() {
		Employee employee = em.find(Employee.class, 1452);
		assertEquals("Alison Smith", employee.getName());
	}
	
	@After
	public void teardown() {
		em.getTransaction().rollback();
		em.close();
	}

}
