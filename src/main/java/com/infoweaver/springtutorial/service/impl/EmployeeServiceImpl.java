package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.Employee;
import com.infoweaver.springtutorial.mapper.EmployeeMapper;
import com.infoweaver.springtutorial.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    private EmployeeMapper employeeMapper;

    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> getEmployeeList() {
        return employeeMapper.selectList(null);
    }

    public Employee getEmployeeById(Long id) {
        return employeeMapper.selectById(id);
    }

    public int addEmployee(Employee employee) {
        return employeeMapper.insert(employee);
    }

    public int editEmployee(Employee employee) {
        return employeeMapper.updateById(employee);
    }

    public int removeEmployee(Long id) {
        return employeeMapper.deleteById(id);
    }
}
