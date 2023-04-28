package com.solponge.domain.admin;

import com.solponge.domain.member.MemberVo;
import com.solponge.domain.product.productVo;
import lombok.Data;

@Data
public class PaymentEntity {
    Payment payment;
    MemberVo member;
    productVo product;

    Delivery delivery;
    int visible;
    int success;



    public PaymentEntity() {
    }

    public PaymentEntity(Payment payment, MemberVo member, productVo product, Delivery delivery) {
        this.payment = payment;
        this.member = member;
        this.product = product;
        this.delivery = delivery;
    }
}
