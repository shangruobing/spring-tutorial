package com.infoweaver.springtutorial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infoweaver.springtutorial.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ruobing Shang 2022-09-02 10:30
 */

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
