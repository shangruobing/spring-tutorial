package com.infoweaver.springtutorial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infoweaver.springtutorial.entity.MoneyAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ruobing Shang 2022-09-02 10:33
 */

@Mapper
public interface MoneyAccountMapper extends BaseMapper<MoneyAccount> {
}
