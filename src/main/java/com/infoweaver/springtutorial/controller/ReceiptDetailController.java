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
    private final ReceiptDetailServiceImpl receiptDetailService;

    @Autowired
    public ReceiptDetailController(ReceiptDetailServiceImpl receiptDetailService) {
        this.receiptDetailService = receiptDetailService;
    }

    @GetMapping("/receipt-detail")
    public List<ReceiptDetail> selectAllReceiptDetail() {
        return receiptDetailService.listReceiptDetails();
    }

    @GetMapping("/receipt-detail/{id}")
    public ReceiptDetail getReceiptDetailById(@PathVariable("id") String id) {
        return receiptDetailService.getReceiptDetailById(id);
    }


    @PostMapping("/receipt-detail")
    public int add(@RequestBody ReceiptDetail receiptDetail) {
        return receiptDetailService.saveReceiptDetail(receiptDetail);
    }

    @PutMapping("/receipt-detail")
    public int update(@RequestBody ReceiptDetail receiptDetail) {
        return receiptDetailService.updateReceiptDetail(receiptDetail);
    }

    @DeleteMapping("/receipt-detail/{id}")
    public int delete(@PathVariable("id") String id) {
        return receiptDetailService.removeReceiptDetail(id);
    }

    @PostMapping("/receipt-details-list/")
    public boolean addList(@RequestBody List<ReceiptDetail> receiptDetailList) {
        return receiptDetailService.saveBatch(receiptDetailList);
    }
}


