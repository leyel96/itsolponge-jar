package com.solponge.domain.admin;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
@Setter
@Getter
public class AdminOrderVo {
    private String payment_num;
    private Long member_no;
    private int product_num;
    private int payment_stock;
    private Date payment_date;
    private String payment_phone;
    private String payment_email;
    private String payment_address;
    private String delivery_info;
    private Long delivery_num;
    private int visible;
    private int success;

    public AdminOrderVo(String payment_num, Long member_no, int product_num, int payment_stock, Date payment_date, String payment_phone, String payment_email, String payment_address, String delivery_info, Long delivery_num, int visible, int success) {
        this.payment_num = payment_num;
        this.member_no = member_no;
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

    public AdminOrderVo() {
    }
}
