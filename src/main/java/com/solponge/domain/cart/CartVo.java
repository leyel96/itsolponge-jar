package com.solponge.domain.cart;

import com.solponge.domain.member.MemberVo;
import lombok.Data;

@Data
public class CartVo {
    private int CART_NUM; // 카트 번호
    private int MEMBER_NUM; // 카트 소유자
    private String CART_DATE; //카트 생성일



    public CartVo(int MEMBER_NUM) {
        this.MEMBER_NUM = MEMBER_NUM;
    }

    public CartVo(int CART_NUM, int MEMBER_NUM, String CART_DATE) {
        this.CART_NUM = CART_NUM;
        this.MEMBER_NUM = MEMBER_NUM;
        this.CART_DATE = CART_DATE;
    }

    public Cart toCart(CartVo cartVo, MemberVo member){
        Cart cart=new Cart();
        cart.setCart_num(cartVo.getCART_NUM());
        cart.setMember(member);
        cart.setCart_date(cartVo.getCART_DATE());
        return cart;
    }
}
