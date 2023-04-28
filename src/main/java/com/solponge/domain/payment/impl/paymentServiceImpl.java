package com.solponge.domain.payment.impl;

import com.solponge.domain.order.OrderVo;
import com.solponge.domain.payment.PaymentService;
import com.solponge.domain.payment.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("paymentService")
public class paymentServiceImpl implements PaymentService {

    @Autowired
    private paymentDAO paymentDAO;

    @Override
    public String insertPayment(String payment_num, int product_num, long member_num, int payment_stock, String payment_phone, String payment_email, String payment_address, String delivery_info) {
        System.out.print("paymentServiceImpl의 insertPayment\n");
        return paymentDAO.insertPayment(payment_num, product_num, member_num, payment_stock, payment_phone, payment_email, payment_address, delivery_info);
    }

    @Override
    public List<PaymentVO> getPaymentList(Long member_No) {
        System.out.print("paymentServiceImpl의 getPaymentList\n");
        return paymentDAO.getPaymentList(member_No);
    }

    @Override
    public void notVisible(String payment_num, int product_num, Long member_No) {
        System.out.print("paymentServiceImpl의 getPaymentList\n");
        paymentDAO.notVisible(payment_num,product_num,member_No);
    }

    @Override
    public void removeStock(int product_num, int product_stock, int payment_stock) {
        paymentDAO.removeStock(product_num,product_stock,payment_stock);
    }

    @Override
    public List<OrderVo> selectOrderList(Long member_No) {
        return paymentDAO.selectOrderList(member_No);
    }

    @Override
    public void deleteOrderList(long member_No, int product_num) {
        paymentDAO.deleteOrderList(member_No,product_num);
    }

}
