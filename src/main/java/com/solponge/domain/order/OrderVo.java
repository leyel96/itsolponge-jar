package com.solponge.domain.order;

import lombok.Data;

/**
 * 장바구니에서 전송한 정보를 받는 vo 입니다.
 */
@Data
public class OrderVo {
    int ORDER_STOCK;
    int MEMBER_NUM;
    int PRODUCT_NUM;
    int CART_ITEM_NUM;
}
