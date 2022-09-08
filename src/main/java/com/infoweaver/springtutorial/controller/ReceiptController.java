package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.entity.Receipt;
import com.infoweaver.springtutorial.service.impl.ReceiptServiceImpl;
import com.infoweaver.springtutorial.entity.vo.ReceiptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@RestController
public class ReceiptController {
    private final ReceiptServiceImpl receiptService;

    @Autowired
    public ReceiptController(ReceiptServiceImpl receiptService) {
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

    @GetMapping("/receipt-with-details/{id}")
    public ReceiptVo getReceiptVoById(@PathVariable("id") String id) {
        return receiptService.getReceiptVoById(id);
    }

    @GetMapping("/receipt-with-details-list/{status}")
    public List<ReceiptVo> getReceiptVoByStatus(@PathVariable("status") Integer status) {
        return receiptService.getReceiptVoByStatus(status);
    }

    @GetMapping("/receipt-with-details-list")
    public List<ReceiptVo> selectAllReceiptInformation() {
        return receiptService.listReceiptVos();
    }

    @PostMapping("/receipt-with-details")
    public String addReceiptInformation(@RequestBody ReceiptVo receiptVo) {
        return receiptService.saveReceiptVo(receiptVo);
    }

    @PostMapping("/payment")
    public int paymentReceipt(@RequestBody Receipt receipt) {
        return receiptService.payment(receipt.getId());
    }

    @PostMapping("/outbound")
    public int outbound(@RequestBody Receipt receipt) {
        return receiptService.outbound(receipt.getId());
    }

    @PostMapping("/take-delivery")
    public int customerTakeDelivery(@RequestBody Receipt receipt) {
        return receiptService.customerTakeDelivery(receipt.getId());
    }
}