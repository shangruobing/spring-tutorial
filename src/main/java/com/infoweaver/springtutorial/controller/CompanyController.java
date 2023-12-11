package com.infoweaver.springtutorial.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.infoweaver.springtutorial.annotation.Debounce;
import com.infoweaver.springtutorial.dto.CompanyDto;
import com.infoweaver.springtutorial.dto.ValidatedGroup;
import com.infoweaver.springtutorial.service.impl.CompanyServiceImpl;
import com.infoweaver.springtutorial.vo.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ruobing Shang 2023-10-12 18:18
 */
@RestController
public class CompanyController {
    private final CompanyServiceImpl companyService;

    @Autowired
    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/company")
    public IPage<CompanyVo> selectCompanies(@Validated @RequestBody CompanyDto companyDto) {
        return companyService.listCompanies(companyDto);
    }

    @Debounce(1000)
    @PostMapping("company/add")
    public CompanyVo addCompany(@Validated({ValidatedGroup.Create.class}) @RequestBody CompanyDto companyDto) {
        return companyService.addCompany(companyDto);
    }

    @Debounce(1000)
    @PutMapping("/company")
    public CompanyVo updateCompany(@Validated({ValidatedGroup.Update.class}) @RequestBody CompanyDto companyDto) {
        return companyService.updateCompany(companyDto);
    }

    @Debounce(1000)
    @GetMapping("company/export")
    public ResponseEntity<?> exportCompany() {
        return companyService.exportCompany();
    }
}
