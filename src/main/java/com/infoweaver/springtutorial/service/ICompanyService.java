package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.Company;
import com.infoweaver.springtutorial.vo.CompanyVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ruobing Shang 2023-10-12 18:18
 */
public interface ICompanyService extends IService<Company> {
    /**
     * 查询所有公司
     *
     * @param companyDto 查询条件
     * @return 分页查询结果
     */
    IPage<CompanyVo> listCompanies(com.infoweaver.springtutorial.dto.CompanyDto companyDto);

    /**
     * 新建公司
     * @param companyDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    CompanyVo addCompany(com.infoweaver.springtutorial.dto.CompanyDto companyDto);

    /**
     * 更新公司
     * @param companyDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    CompanyVo updateCompany(com.infoweaver.springtutorial.dto.CompanyDto companyDto);
}
