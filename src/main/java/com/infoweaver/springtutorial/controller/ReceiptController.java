package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.entity.Receipt;
import com.infoweaver.springtutorial.service.impl.ReceiptServiceImpl;
import com.infoweaver.springtutorial.vo.ReceiptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@RestController
public class ReceiptController {
    private ReceiptServiceImpl receiptService;

    @Autowired
    public void setReceiptService(ReceiptServiceImpl receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/receipt")
    public List<Receipt> selectAllReceipt() {
        return receiptService.listReceipts();
    }

    @GetMapping("/receipt/{id}")
    public Receipt getReceiptById(@PathVariable("id") String id) {
        return receiptService.getReceiptById(id);
    }

    @PostMapping("/receipt")
    public int add(@RequestBody Receipt receipt) {
        return receiptService.saveReceipt(receipt);
    }

    @PutMapping("/receipt")
    public int update(@RequestBody Receipt receipt) {
        return receiptService.updateReceipt(receipt);
    }

    @DeleteMapping("/receipt/{id}")
    public int remove(@PathVariable("id") String id) {
        return receiptService.removeReceipt(id);
    }

    @GetMapping("/receipt_list/{id}")
    public ReceiptVo getReceiptVoById(@PathVariable("id") String id) {
        return receiptService.getReceiptVoById(id);
    }

    @GetMapping("/receipt_list")
    public List<ReceiptVo> selectAllReceiptInformation() {
        return receiptService.listReceiptVos();
    }

    @PostMapping("/receipt_with_details")
    public int addReceiptInformation(@RequestBody ReceiptVo receiptvo) {
        return receiptService.saveReceiptVo(receiptvo);
    }
}