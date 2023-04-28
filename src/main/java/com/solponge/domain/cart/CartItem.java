package com.solponge.domain.cart;

import com.solponge.domain.member.MemberVo;
import com.solponge.domain.product.productVo;
import lombok.Data;

@Data
public class CartItem {
    private int CART_ITEM_NUM;
    private MemberVo member;
    private productVo product;
    private int CART_ITEM_STOCK;

    public CartItem() {
    }

    public CartItem(MemberVo member, productVo product, int CART_ITEM_STOCK) {
        this.member = member;
        this.product = product;
        this.CART_ITEM_STOCK = CART_ITEM_STOCK;
    }

    public CartItem(int CART_ITEM_NUM, MemberVo member, productVo product, int CART_ITEM_STOCK) {
        this.CART_ITEM_NUM = CART_ITEM_NUM;
        this.member = member;
        this.product = product;
        this.CART_ITEM_STOCK = CART_ITEM_STOCK;
    }


    public int getTotalPrice(){
        int totalPrice = 0;
        if (this.CART_ITEM_STOCK > 0) {
            totalPrice += this.CART_ITEM_STOCK * this.product.getProduct_price();
        }
        return totalPrice;
    }


}
