package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.entity.ReceiptDetail;
import com.infoweaver.springtutorial.service.impl.ReceiptDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@RestController
public class ReceiptDetailController {
    private ReceiptDetailServiceImpl ReceiptDetailService;

    @Autowired
    public void setReceiptDetailService(ReceiptDetailServiceImpl ReceiptDetailService) {
        this.ReceiptDetailService = ReceiptDetailService;
    }

    @GetMapping("/receiptDetail")
    public List<ReceiptDetail> selectAllReceiptDetail() {
        return ReceiptDetailService.getReceiptDetailList();
    }

    @GetMapping("/receiptDetail/{id}")
    public ReceiptDetail getReceiptDetailById(@PathVariable("id") long id) {
        return ReceiptDetailService.getReceiptDetailById(id);
    }


    @PostMapping("/receiptDetail")
    public int add(@RequestBody ReceiptDetail ReceiptDetail) {
        return ReceiptDetailService.addReceiptDetail(ReceiptDetail);
    }

    @PutMapping("/receiptDetail")
    public int update(@RequestBody ReceiptDetail ReceiptDetail) {
        return ReceiptDetailService.editReceiptDetail(ReceiptDetail);
    }

    @DeleteMapping("/receiptDetail/{id}")
    public int delete(@PathVariable("id") long id) {
        return ReceiptDetailService.removeReceiptDetail(id);
    }

    @PostMapping("/receiptDetailList/")
    public boolean addList(@RequestBody List<ReceiptDetail> receiptDetailList) {
        System.out.println(receiptDetailList);
        return ReceiptDetailService.saveBatch(receiptDetailList);
    }
}


