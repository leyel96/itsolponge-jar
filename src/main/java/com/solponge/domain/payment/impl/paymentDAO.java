package com.solponge.domain.payment.impl;

import com.solponge.domain.order.OrderVo;
import com.solponge.domain.payment.OutOfStockException;
import com.solponge.domain.payment.PaymentVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class paymentDAO {

    @Autowired
    SqlSessionTemplate sqlSession;


    public List<PaymentVO> getPaymentList(Long member_No){
        System.out.print("paymentDAO의 getPaymentList입니다.\n");
        Map<String, Object> param = new HashMap<>();
        param.put("member_No", member_No);
        return sqlSession.selectList("payment.list", param);
    }

    public String insertPayment(String payment_num, int product_num,long member_No,int payment_stock,String payment_phone, String payment_email,String payment_address, String delivery_info){
        System.out.print("paymentDAO의 insertPayment.\n");
        Map<String, Object> param = new HashMap<>();
        System.out.println("payment_num: "+payment_num);
        System.out.println("product_num: "+product_num);
        System.out.println("member_No: "+member_No);
        System.out.println("payment_stock: "+payment_stock);
        System.out.println("payment_phone: "+payment_phone);
        System.out.println("payment_email: "+payment_email);
        System.out.println("payment_address: "+payment_address);
        System.out.println("delivery_info: "+delivery_info);

        param.put("payment_num", payment_num);
        param.put("member_No", member_No);
        param.put("product_num", product_num);
        param.put("payment_stock", payment_stock);
        param.put("payment_phone", payment_phone);
        param.put("payment_email", payment_email);
        param.put("payment_address", payment_address);
        param.put("deliver_info", delivery_info);
        sqlSession.insert("payment.insertPayment", param);
        sqlSession.update("collection_of_sql_statements.updateProductSale", param);
        return param.get("payment_num").toString();
    }

    public void notVisible(String payment_num, int product_num, Long member_No){
        Map<String, Object> param = new HashMap<>();
        param.put("payment_num", payment_num);
        param.put("product_num", product_num);
        param.put("member_No", member_No);
        sqlSession.update("payment.notVisible", param);
    }

    public void removeStock(int product_num, int product_stock,int payment_stock) {
        Map<String, Object> param = new HashMap<>();
        param.put("product_num", product_num);
        if((product_stock - payment_stock) < 0){
            throw new OutOfStockException("상품의 재고가 부족합니다.");
        }else{
            param.put("product_stock", (product_stock-payment_stock));
        }
        sqlSession.update("payment.removeStock", param);
    }

    public List<OrderVo> selectOrderList(Long member_No) {
        Map<String, Object> param = new HashMap<>();
        param.put("member_No", member_No);
        return sqlSession.selectList("payment.selectOrderList", param);
    }
    public void deleteOrderList(Long member_No, int product_num){
        Map<String, Object> param = new HashMap<>();
        param.put("member_No", member_No);
        param.put("product_num", product_num);
        sqlSession.delete("payment.deleteOrderList", param);
    }

}
