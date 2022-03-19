package com.arunish.springdata.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.arunish.springdata.employee.entities.Employee;
import com.arunish.springdata.employee.repos.EmployeeRepository;

@SpringBootTest
class EmployeeApplicationTests {

	@Autowired
	EmployeeRepository repository;
	
	@Test
	void contextLoads() {
	}
    
	//Q3
	@Test
	void testCreate()
	{
		Employee emp=new Employee();
		emp.setId(1);
		emp.setName("Arunish");
		emp.setAge(21);
		emp.setLocation("Delhi");
		
		repository.save(emp);
	}
	
	@Test
	void testRead()
	{
		Employee emp=repository.findById(1).get();
		assertNotNull(emp);
		assertEquals("Arunish",emp.getName());
	}
	
	@Test
	void testUpdate()
	{
		Employee emp=repository.findById(1).get();
		emp.setLocation("Noida");
		repository.save(emp);
	}
	
	@Test
	void testDelete()
	{
		Employee emp=repository.findById(1).get();
		repository.delete(emp);
	}
	
	@Test
	void testExists()
	{
		boolean val=repository.existsById(1);
		assertTrue(val);
	}
	
	@Test
	void testCount()
	{
		System.out.println("Total Number of Employees are:  "+ repository.count());
	}
	
	@Test
	void testFindByName()
	{
		List<Employee> emp=new ArrayList<>();
		emp=repository.findByName("Arunish");
	    emp.forEach(e->System.out.println(e.getName()));	
	}
	
	@Test
	void doPagingAndSorting()
	{
		Pageable pageable=PageRequest.of(0, 2, Direction.ASC, "Age");
		repository.findAll(pageable).forEach(emp->System.out.println(emp.getName()+" "+emp.getAge()));
	}
	
	@Test
	void testFindByNameLike()
	{
		List<Employee> emp=new ArrayList<>();
		emp=repository.findByNameLike("A%");
	    emp.forEach(e->System.out.println(e.getName()));	
	}
	
	@Test
	void testFindByAgeBetween()
	{
		List<Employee> emp=new ArrayList<>();
		emp=repository.findByAgeBetween(28,32);
	    emp.forEach(e->System.out.println(e.getName()));	
	}
	
	
	/*@Test
	void findAllPaging()
	{
		Pageable pageable=PageRequest.of(0, 2);
		
		Page<Employee> emp=repository.findAll(pageable);
		emp.forEach(e->System.out.println(e.getName()));
	}
	
	@Test
	void findAllSorting()
	{
		repository.findAll(Sort.by(new Order(null,"name"))).forEach(p->System.out.println(p.getName()));
	}*/
}
