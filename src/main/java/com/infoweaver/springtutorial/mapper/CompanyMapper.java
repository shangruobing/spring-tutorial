package com.infoweaver.springtutorial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infoweaver.springtutorial.common.MybatisPlusRedisCache;
import com.infoweaver.springtutorial.entity.Company;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ruobing Shang 2023-10-12 18:18
 */
@Mapper
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface CompanyMapper extends BaseMapper<Company> {
}
