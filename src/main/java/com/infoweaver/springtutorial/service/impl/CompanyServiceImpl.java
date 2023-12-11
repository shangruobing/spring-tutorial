package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.common.ExcelExporter;
import com.infoweaver.springtutorial.dto.CompanyDto;
import com.infoweaver.springtutorial.entity.Company;
import com.infoweaver.springtutorial.mapper.CompanyMapper;
import com.infoweaver.springtutorial.po.ExcelMapping;
import com.infoweaver.springtutorial.security.SecurityUtils;
import com.infoweaver.springtutorial.service.ICompanyService;
import com.infoweaver.springtutorial.vo.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Ruobing Shang 2023-10-12 18:18
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    @Override
    public IPage<CompanyVo> listCompanies(CompanyDto companyDto) {
        IPage<Company> page = new Page<>(companyDto.getPage(), companyDto.getPageSize());
        LambdaQueryWrapper<Company> wrapper = Wrappers.lambdaQuery(Company.class);
        wrapper.orderByDesc(Company::getId);
        Optional.ofNullable(companyDto.getId()).ifPresent(id -> wrapper.eq(Company::getId, id));
        Optional.ofNullable(companyDto.getName()).ifPresent(name -> wrapper.like(Company::getName, name));
        Optional.ofNullable(companyDto.getType()).ifPresent(type -> wrapper.eq(Company::getType, type));
        return companyMapper.selectPage(page, wrapper).convert(CompanyVo::new);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CompanyVo addCompany(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName())
                .setType(companyDto.getType())
                .setCreateUserId(SecurityUtils.getCurrentUserId());
        companyMapper.insert(company);
        return new CompanyVo(company);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CompanyVo updateCompany(CompanyDto companyDto) {
        Integer id = Optional.ofNullable(companyDto.getId()).orElseThrow(() -> new RuntimeException("ID不能为空"));
        Company existCompany = Optional.ofNullable(companyMapper.selectById(id)).orElseThrow(() -> new RuntimeException("公司不存在"));
        Optional.ofNullable(companyDto.getName()).ifPresent(existCompany::setName);
        Optional.ofNullable(companyDto.getType()).ifPresent(existCompany::setType);
        companyMapper.updateById(existCompany);
        return new CompanyVo(existCompany);
    }

    public ResponseEntity<?> exportCompany() {
        ExcelExporter<Company> excelExporter = new ExcelExporter<>(getRequiredColumns(), "公司信息");
        return excelExporter.exportToExcel(companyMapper.selectList(Wrappers.emptyWrapper()));
    }

    /**
     * 所需要预检的列
     */
    private List<ExcelMapping> getRequiredColumns() {
        return List.of(
                new ExcelMapping("公司编号", "id"),
                new ExcelMapping("公司名称", "name"),
                new ExcelMapping("类型", "type")
        );
    }
}
