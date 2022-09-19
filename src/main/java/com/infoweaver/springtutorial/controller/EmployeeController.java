package com.infoweaver.springtutorial.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.infoweaver.springtutorial.entity.Employee;
import com.infoweaver.springtutorial.service.impl.EmployeeServiceImpl;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Ruobing Shang 2022-09-02 9:51
 */

@RestController
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/employee")
    public Page<Employee> selectAllEmployee(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer currentPage,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size
    ) {
        Page<Employee> page = new Page<>(currentPage, size);
        return employeeService.page(page);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable("id") String id) throws NotFoundException {
        return Optional.ofNullable(employeeService.getEmployeeById(id))
                .orElseThrow(() -> new NotFoundException("Employee " + id + " NOT_FOUND"));
    }

    @PostMapping("/employee")
    public int add(@RequestBody @Valid Employee employee) {
        employee.setLastLogin(LocalDateTime.now());
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employee")
    public int update(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employee/{id}")
    public int delete(@PathVariable("id") String id) {
        return employeeService.removeEmployee(id);
    }
}
