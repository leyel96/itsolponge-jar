package com.solponge.domain.cart.impl;

import com.solponge.domain.cart.CartItemVo;
import com.solponge.domain.cart.CartListVo;
import com.solponge.domain.cart.CartService;
import com.solponge.domain.cart.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class CartServiceImpl implements CartService {
    private final CartItemDAO cartItemDAO;
    private final CartDAO cartDAO;

    public CartServiceImpl(CartItemDAO cartItemDAO, CartDAO cartDAO) {
        this.cartItemDAO = cartItemDAO;
        this.cartDAO = cartDAO;
    }


    @Override
    public int addItem(CartItemVo cartItem) {
        String cart_item_num = cartItemDAO.cartItemInsert(cartItem);
        return Integer.parseInt(cart_item_num);
    }

    @Override
    public void updateItem(int cart_item_num, CartItemVo cartItem) {
        cartItemDAO.cartItemUpdate(cart_item_num,cartItem);
    }

    @Override
    public void deleteItem(int cartItem_num) {
        cartItemDAO.cartItemDelete(cartItem_num);
    }

    @Override
    public CartItemVo findItem(int cart_item_num) {
        return cartItemDAO.cartItemFindByNum(cart_item_num);
    }

    @Override
    public int createCart(CartVo cartVo) {
        cartDAO.cartInsert(cartVo);
        return cartVo.getCART_NUM();
    }

    @Override
    public CartVo getMyCart(int member_num) {
        return cartDAO.cartFindByNum(member_num);
    }

    @Override
    public List<CartListVo> cartList(int member_num) {
        return cartDAO.showMemberCart(member_num);
    }

    @Override
    public void deleteCartItemByMember(int MEMBER_NO) {
        cartItemDAO.cartItemDeleteFromMember(MEMBER_NO);
    }

    @Override
    public void deleteCartByMember(int MEMBER_NUM) {
        cartDAO.cartDeleteByMember(MEMBER_NUM);
    }
}
