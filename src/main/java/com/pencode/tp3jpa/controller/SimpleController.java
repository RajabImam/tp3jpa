package com.pencode.tp3jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pencode.tp3jpa.entities.Emp;
import com.pencode.tp3jpa.repository.EmpRepository;


@RestController
public class SimpleController {
	@Autowired
	   private EmpRepository empRepository;

@RequestMapping(value="/", method= RequestMethod.GET)
public String hello(@RequestParam(value = "name", required = false) String name) {
    return "Hello " + name;
}


@RequestMapping(value="/employees", method= RequestMethod.GET)
public List<Emp> getEmployees() {
    return empRepository.findAll();
}

@RequestMapping(value="/employees/{id}", method= RequestMethod.GET)
public Optional<Emp> getEmployeeById(@PathVariable(value = "id") long id) {
    //Emp emp = empRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with this ID not found " + id));
    return empRepository.findById(id);
}


@PostMapping(value="/employees")
public Emp addEmployee(@RequestBody Emp emp) {
  return empRepository.save(emp);
}

@PutMapping(value="/employees{id}")
public Emp updateEmployee(@PathVariable long id, @RequestBody Emp emp) {
	Emp updatedEmp = empRepository.findById(id).get();
	updatedEmp.setEname(emp.getEname());
	updatedEmp.setEfirst(emp.getEfirst());
	updatedEmp.setJob(emp.getJob());
	updatedEmp.setMgr(emp.getMgr());
	updatedEmp.setSal(emp.getSal());
	return empRepository.save(updatedEmp);
}

@DeleteMapping(value="/employees/{id}")
public String deleteEmployee(@PathVariable long id) {
	Emp deleteEmp = empRepository.findById(id).get();
	 empRepository.delete(deleteEmp);
	 return "Record with ID Deleted " + id;
}


}
