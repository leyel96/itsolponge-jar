package com.solponge.domain.payment;

import lombok.Data;

@Data
public class payVO {

    private String payment_num;
    private int[] product_num;
    private int[] payment_stock;
    private String delivery_info;
    private String email;
    private String phone;
    private String address;
    private int[] cartItem_num;
}
