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
    private ReceiptDetailServiceImpl receiptDetailService;

    @Autowired
    public void setReceiptDetailService(ReceiptDetailServiceImpl receiptDetailService) {
        this.receiptDetailService = receiptDetailService;
    }

    @GetMapping("/receiptDetail")
    public List<ReceiptDetail> selectAllReceiptDetail() {
        return receiptDetailService.listReceiptDetails();
    }

    @GetMapping("/receiptDetail/{id}")
    public ReceiptDetail getReceiptDetailById(@PathVariable("id") String id) {
        return receiptDetailService.getReceiptDetailById(id);
    }


    @PostMapping("/receiptDetail")
    public int add(@RequestBody ReceiptDetail receiptDetail) {
        return receiptDetailService.saveReceiptDetail(receiptDetail);
    }

    @PutMapping("/receiptDetail")
    public int update(@RequestBody ReceiptDetail receiptDetail) {
        return receiptDetailService.updateReceiptDetail(receiptDetail);
    }

    @DeleteMapping("/receiptDetail/{id}")
    public int delete(@PathVariable("id") String id) {
        return receiptDetailService.removeReceiptDetail(id);
    }

    @PostMapping("/receiptDetailList/")
    public boolean addList(@RequestBody List<ReceiptDetail> receiptDetailList) {
        System.out.println(receiptDetailList);
        return receiptDetailService.saveBatch(receiptDetailList);
    }
}


