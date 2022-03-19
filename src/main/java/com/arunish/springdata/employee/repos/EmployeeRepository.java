package com.arunish.springdata.employee.repos;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.arunish.springdata.employee.entities.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

	List<Employee> findByName(String name);

	List<Employee> findByNameLike(String string);

	List<Employee> findByAgeBetween(int num1, int num2);




	
}
