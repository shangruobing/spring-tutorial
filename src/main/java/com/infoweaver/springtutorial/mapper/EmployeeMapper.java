package com.infoweaver.springtutorial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infoweaver.springtutorial.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ruobing Shang 2022-09-02 9:42
 */

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
