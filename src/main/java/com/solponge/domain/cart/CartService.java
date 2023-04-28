package com.solponge.domain.cart;

import java.util.List;

public interface CartService {
    /**
     * cart_item
     * 상품 추가, 수정, 삭제, 확인
     */
    int addItem(CartItemVo cartItem);
    void updateItem(int cart_item_num, CartItemVo cartItem);
    void deleteItem(int cartItem_num);
    CartItemVo findItem(int cart_item_num);

    /**
     * cart
     * 상품 리스트 확인
     */
    int createCart(CartVo cartVo);

    CartVo getMyCart(int member_num);

    List<CartListVo> cartList(int member_num);

    /**
     * 서비스
     */
    void deleteCartItemByMember(int MEMBER_NO);
    void deleteCartByMember(int MEMBER_NUM);
}
