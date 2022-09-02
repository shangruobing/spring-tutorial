package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.entity.Employee;
import com.infoweaver.springtutorial.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-02 9:51
 */

@RestController
public class EmployeeController {
    private EmployeeServiceImpl employeeService;

    @Autowired
    public void setEmployeeService(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public List<Employee> selectAllEmployee() {
        return employeeService.getEmployeeList();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable("id") long id) {
        return employeeService.getEmployeeById(id);
    }


    @PostMapping("/employee")
    public int add(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/employee")
    public int update(@RequestBody Employee employee) {
        return employeeService.editEmployee(employee);
    }

    @DeleteMapping("/employee/{id}")
    public int delete(@PathVariable("id") long id) {
        return employeeService.removeEmployee(id);
    }
}
