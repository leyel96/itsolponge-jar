package com.solponge.domain.payment;

import lombok.Data;

@Data
public class PaymentVO {

    private String payment_num;
    private Long member_No;
    private int product_num;
    private int payment_stock;
    private String payment_date;
    private String payment_phone;
    private  String payment_email;
    private String payment_address;
    private  String delivery_info;
    private long delivery_num;
    private int visible;
    private int success;

    public PaymentVO(String payment_num, Long member_No, int product_num, int payment_stock, String payment_date, String payment_phone, String payment_email, String payment_address, String delivery_info, long delivery_num, int visible, int success) {
        this.payment_num = payment_num;
        this.member_No = member_No;
        this.product_num = product_num;
        this.payment_stock = payment_stock;
        this.payment_date = payment_date;
        this.payment_phone = payment_phone;
        this.payment_email = payment_email;
        this.payment_address = payment_address;
        this.delivery_info = delivery_info;
        this.delivery_num = delivery_num;
        this.visible = visible;
        this.success = success;
    }
}
