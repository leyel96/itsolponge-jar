package com.solponge.domain.cart;

import com.solponge.domain.product.productVo;
import lombok.Data;

import java.util.Date;

@Data
public class CartListVo {
    private int MEMBER_NUM;
    private Date CART_DATE;
    private int CART_ITEM_NUM;
    private int PRODUCT_NUM;
    private int CART_ITEM_STOCK;
    private String PRODUCT_TITLE;
    private int PRODUCT_PRICE;

    public CartListVo(int MEMBER_NUM, Date CART_DATE, int CART_ITEM_NUM, int PRODUCT_NUM, int CART_ITEM_STOCK, String PRODUCT_TITLE, int PRODUCT_PRICE) {
        this.MEMBER_NUM = MEMBER_NUM;
        this.CART_DATE = CART_DATE;
        this.CART_ITEM_NUM = CART_ITEM_NUM;
        this.PRODUCT_NUM = PRODUCT_NUM;
        this.CART_ITEM_STOCK = CART_ITEM_STOCK;
        this.PRODUCT_TITLE = PRODUCT_TITLE;
        this.PRODUCT_PRICE = PRODUCT_PRICE;
    }

    public CartItem toCartItem(){
        CartItem cartItem = new CartItem();
        cartItem.setProduct(new productVo(PRODUCT_NUM,PRODUCT_TITLE,PRODUCT_PRICE));
        cartItem.setCART_ITEM_STOCK(CART_ITEM_STOCK);
        return cartItem;

    }
}
