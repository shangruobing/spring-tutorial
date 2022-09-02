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
    public void setReceiptService(ReceiptServiceImpl ReceiptService) {
        this.receiptService = ReceiptService;
    }

    @GetMapping("/receipt")
    public List<Receipt> selectAllReceipt() {
        return receiptService.getReceiptList();
    }

    @GetMapping("/receipt/{id}")
    public Receipt getReceiptById(@PathVariable("id") long id) {
        return receiptService.getReceiptById(id);
    }


    @PostMapping("/receipt")
    public int add(@RequestBody Receipt Receipt) {
        return receiptService.addReceipt(Receipt);
    }

    @PutMapping("/receipt")
    public int update(@RequestBody Receipt Receipt) {
        return receiptService.editReceipt(Receipt);
    }

    @DeleteMapping("/receipt/{id}")
    public int delete(@PathVariable("id") long id) {
        return receiptService.removeReceipt(id);
    }

    @GetMapping("/receipt_list/{id}")
    public ReceiptVo getReceiptListById(@PathVariable("id") long id) {
        return receiptService.getReceiptInformationById(id);
    }

    @GetMapping("/receipt_list")
    public List<ReceiptVo> selectAllReceiptDetails() {
        return receiptService.getAllReceiptInformation();
    }

    @PostMapping("/receipt_with_details")
    public int addReceiptWithDetails(@RequestBody ReceiptVo receiptvo) {
        return receiptService.addReceiptVo(receiptvo);
    }

}