package com.infoweaver.springtutorial.vo;

import com.infoweaver.springtutorial.entity.Company;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Ruobing Shang 2023-11-02 11:08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CompanyVo extends Company {
    public CompanyVo(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.type = company.getType();
        this.createTime = company.getCreateTime();
        this.createUserId = company.getCreateUserId();
        this.updateTime = company.getUpdateTime();
        this.updateUserId = company.getUpdateUserId();
    }
}
