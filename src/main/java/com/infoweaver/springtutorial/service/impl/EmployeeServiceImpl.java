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
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<Employee> listEmployees() {
        return employeeMapper.selectList(null);
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public int saveEmployee(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateById(employee);
    }

    @Override
    public int removeEmployee(String id) {
        return employeeMapper.deleteById(id);
    }
}
