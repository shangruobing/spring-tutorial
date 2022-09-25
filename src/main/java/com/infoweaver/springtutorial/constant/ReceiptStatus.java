package com.infoweaver.springtutorial.constant;

import lombok.Getter;

/**
 * @author Ruobing Shang 2022-09-02 13:55
 */

@Getter
public enum ReceiptStatus {
    /**
     * Receipt Custom Status
     */
    NEW_ORDER(1, "New Order"),
    PAID(2, "Paid"),
    DELIVERED(3, "Delivered"),
    FINISHED(4, "Finished");

    private final int code;
    private final String message;

    ReceiptStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}