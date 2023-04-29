package com.solponge.domain.cart;


import lombok.Data;

@Data
public class CartItemVo {
    private int CART_ITEM_NUM;
    private int MEMBER_NUM;
    private int PRODUCT_NUM;
    private int CART_ITEM_STOCK;


    public CartItemVo(int CART_ITEM_STOCK) {
        this.CART_ITEM_STOCK = CART_ITEM_STOCK;
    }

    public CartItemVo(CartItem cartItem) {
        this.MEMBER_NUM = cartItem.getMember().getMEMBER_NO().intValue();
        this.PRODUCT_NUM = cartItem.getProduct().getProduct_num();
        this.CART_ITEM_STOCK = cartItem.getCART_ITEM_STOCK();
    }
    //select 를 위한 생성자
    public CartItemVo(int CART_ITEM_NUM, int MEMBER_NUM, int PRODUCT_NUM, int CART_ITEM_STOCK) {
        this.CART_ITEM_NUM = CART_ITEM_NUM;
        this.MEMBER_NUM = MEMBER_NUM;
        this.PRODUCT_NUM = PRODUCT_NUM;
        this.CART_ITEM_STOCK = CART_ITEM_STOCK;
    }

}
