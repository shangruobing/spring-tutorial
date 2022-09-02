package com.infoweaver.springtutorial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infoweaver.springtutorial.entity.ReceiptDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ruobing Shang 2022-09-02 10:31
 */

@Mapper
public interface ReceiptDetailMapper extends BaseMapper<ReceiptDetail> {
}
