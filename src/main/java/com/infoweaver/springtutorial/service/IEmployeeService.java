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
    List<Employee> listEmployees();

    /**
     * Retrieve an Employee by id.
     *
     * @param id employee id
     * @return an Employee instance
     */
    Employee getEmployeeById(String id);

    /**
     * Create an Employee instance.
     *
     * @param employee employee object
     * @return a status code
     */
    int saveEmployee(Employee employee);

    /**
     * Update an employee instance.
     *
     * @param employee employee object
     * @return a status code
     */
    int updateEmployee(Employee employee);

    /**
     * Delete a employee instance.
     *
     * @param id employee id
     * @return a status code
     */
    int removeEmployee(String id);

}
