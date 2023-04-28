package com.solponge.domain.cart;


import lombok.Data;

@Data
public class CartItemVo {
    private int cart_item_num;
    private int member_num;
    private int product_num;
    private int cart_item_stock;

    public CartItemVo(int CART_ITEM_STOCK) {
        this.cart_item_stock = CART_ITEM_STOCK;
    }

    public CartItemVo(CartItem cartItem) {
        this.member_num = cartItem.getMember().getMEMBER_NO().intValue();
        this.product_num = cartItem.getProduct().getProduct_num();
        this.cart_item_stock = cartItem.getCART_ITEM_STOCK();
    }
    //select 를 위한 생성자
    public CartItemVo(int CART_ITEM_NUM, int MEMBER_NUM, int PRODUCT_NUM, int CART_ITEM_STOCK) {
        this.cart_item_num = CART_ITEM_NUM;
        this.member_num = MEMBER_NUM;
        this.product_num = PRODUCT_NUM;
        this.cart_item_stock = CART_ITEM_STOCK;
    }

}
