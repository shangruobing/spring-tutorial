package com.infoweaver.springtutorial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infoweaver.springtutorial.config.MybatisPlusRedisCache;
import com.infoweaver.springtutorial.entity.MoneyTaking;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ruobing Shang 2022-09-07 9:49
 */

@Mapper
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface MoneyTakingMapper extends BaseMapper<MoneyTaking> {
}
