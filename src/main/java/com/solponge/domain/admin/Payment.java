package com.solponge.domain.admin;

import lombok.Data;

import java.sql.Date;

@Data
public class Payment {
    String paymentNum;
    int paymentStockNumber;
    Date paymentDate;
    String paymentPhone;
    String paymentEmail;
    String paymentAddress;

    public Payment() {
    }

    public Payment(String paymentNum, int paymentStockNumber, Date paymentDate, String paymentPhone, String paymentEmail, String paymentAddress) {
        this.paymentNum = paymentNum;
        this.paymentStockNumber = paymentStockNumber;
        this.paymentDate = paymentDate;
        this.paymentPhone = paymentPhone;
        this.paymentEmail = paymentEmail;
        this.paymentAddress = paymentAddress;
    }
}
