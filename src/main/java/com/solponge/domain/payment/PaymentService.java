package com.solponge.domain.payment;

import com.solponge.domain.order.OrderVo;

import java.util.List;

public interface PaymentService {

    String insertPayment(String payment_num, int product_num,long member_num,int payment_stock,String payment_phone, String payment_email,String payment_address, String delivery_info);
    List<PaymentVO> getPaymentList(Long member_No);
    void notVisible(String payment_num, int product_num, Long member_No);
    void removeStock(int product_num, int product_stock, int payment_stock);
    List<OrderVo> selectOrderList(Long member_No);
    void deleteOrderList(long member_No, int product_num);
}
