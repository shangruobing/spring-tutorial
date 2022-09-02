package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.Employee;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-02 10:37
 */

public interface IEmployeeService extends IService<Employee> {
    /**
     * Retrieve All Employee.
     *
     * @return Employee List
     */
    List<Employee> getEmployeeList();

    /**
     * Retrieve a Employee by id.
     *
     * @param id employee id
     * @return a Employee instance
     */
    Employee getEmployeeById(Long id);

    /**
     * Create a Employee instance.
     *
     * @param employee employee object
     * @return a status code
     */
    int addEmployee(Employee employee);

    /**
     * Update a employee instance.
     *
     * @param employee employee object
     * @return a status code
     */
    int editEmployee(Employee employee);

    /**
     * Delete a employee instance.
     *
     * @param id employee id
     * @return a status code
     */
    int removeEmployee(Long id);

}
