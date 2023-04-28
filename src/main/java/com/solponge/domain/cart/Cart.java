package com.solponge.domain.cart;

import com.solponge.domain.member.MemberVo;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Cart {
    private int cart_num; // 카트 번호
    private MemberVo member; // 카트 소유자
    private Map<Long, CartItem> cartItems = new HashMap<>(); // 카트 아이템 리스트
    private String cart_date;

   // private Long cartItemNumCounter = 1L;// 카트 아이템 번호 시퀀스

    public Cart(int memberNo) {
        this.cartItems = new HashMap<>();
    }

    public Cart() {
    }

    public Cart(int cart_num, MemberVo member) {
        this.cart_num = cart_num;
        this.member = member;
    }

    public void addCartItem(CartItem cartItem) throws RuntimeException {
        int orderStock = cartItem.getCART_ITEM_STOCK();
        int cartItemNum = cartItem.getCART_ITEM_NUM();

        if (cartItems.containsKey(cartItemNum)) { // 같은 상품이 이미 장바구니에 있을 경우
            /*CartItem existingCartItem = cartItems.get(productNum);
            int stock = existingCartItem.getProduct().getProduct_stock(); // 기존 재고량
            int existingStock = existingCartItem.getCART_ITEM_STOCK(); // 기존 주문량
            if (stock < orderStock + existingStock) { // 재고보다 주문량 합이 많을 경우 예외 발생
                throw new Exception("not enough stock");
            }
            existingCartItem.setCART_ITEM_STOCK(existingStock + orderStock); // 기존 상품 주문량 증가*/
        } else { // 새로운 상품을 장바구니에 추가
            int stock = cartItem.getProduct().getProduct_stock(); // 재고량
            if (stock < orderStock) { // 재고보다 주문량이 많을 경우 예외 발생
                throw new RuntimeException("not enough stock");
            }
           // cartItem.setCartItem_num(cartItemNumCounter++); // 카트 아이템 번호 부여
            cartItems.put((long) cartItemNum, cartItem); // 카트에 새로운 상품을 추가
        }
    }


    /*카트에서 상품 제거*/
    public void removeCartItem(Long productNum) {
        if (cartItems.containsKey(productNum)) { // 카트에서 상품 제거
            cartItems.remove(productNum);
        }
    }

    /**
     * 조회로직
     */
    /*전체 주문 가격 조회*/
    public int getTotalPrice() {
        int totalPrice = 0;
        for (CartItem cartItem : this.cartItems.values()) {
            if (cartItem.getCART_ITEM_STOCK() > 0) {
                totalPrice += cartItem.getTotalPrice();
            }
        }
        return totalPrice;
    }
}
