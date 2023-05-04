package com.solponge.web.view.cart;

import com.solponge.domain.cart.*;
import com.solponge.domain.member.MemberVo;
import com.solponge.domain.member.impl.MemberServiceImpl;
import com.solponge.domain.product.productVo;
import com.solponge.web.view.login.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/com.solponge/member/{MEMBER_NO}/myPage/cart")
public class CartController {
    private final MemberServiceImpl memberService;
    private final com.solponge.domain.product.productService productService;
    private final CartService cartService;

    /**
     * 장바구니
     */
    @GetMapping
    public String getCart(Model model,
                       HttpServletRequest request){
        //세션을 받아 계정객체 받아옴
        MemberVo member = getLoginMember(request);
        //---------------------------------------------------------
        List<CartListVo> cartListVos = cartService.cartList(Math.toIntExact(member.getMEMBER_NO()));

        //회원의 카트 vo 조회
        CartVo myCart = cartService.getMyCart(Math.toIntExact(member.getMEMBER_NO()));
        //cart 객체 생성
        Cart cart = myCart.toCart(myCart, member);

        for (CartListVo cartListVo : cartListVos) {
            //cartListVo를 cartItem 으로 변환 //현재상태 : member=null, productVo=cartListVo 에서 받아온 기초 정보(cart_item_num, cart_item_title, cart_item_price), cart_item_stock
            CartItem cartItem = cartListVo.toCartItem();
            //cartItem 의 product 정보를 진짜 해당 product 정보로 변환
            cartItem.setProduct(productService.getproduct(cartItem.getProduct().getProduct_num()));
            cartItem.setMember(member);
            //cartItemVo에 담겨있는 cart_item_num 값을 받아와 만들어진 cartItem 에 적용
            cartItem.setCART_ITEM_NUM(cartListVo.getCART_ITEM_NUM());
            //변환시킨 cartItem 을 미리 만든 cartItems 에 넣기

            try{
                cart.addCartItem(cartItem);
            }catch (RuntimeException e){
                log.info("exception=",e);
                return "redirect:com.solponge/main";
            }

        }
        //----------------------------------------------------------
        //cartItem 을 넣은 cart 를 model 에 저장
        model.addAttribute("cart",cart);
        System.out.println(cart.getCartItems());
        return "member/cart";
    }


    /* 장바구니 추가*/
    @GetMapping("/{productId}/{quantityinput}")
    public String addCartItem(@PathVariable int productId,
                           @PathVariable int quantityinput,
                           HttpServletRequest request){

        MemberVo loginMember = getLoginMember(request);
        //받아온 상품번호로 상품객체 소환
        productVo getproduct = productService.getproduct(productId);
        //cartItem 객체를 생성하여 필요한 값을 cartItemVo로 전달
        CartItemVo cartItemVo = new CartItemVo(new CartItem(loginMember,getproduct, quantityinput));
        //받아온 상품정보를 CART_ITEM 에 저장
        int cart_Item_num = cartService.addItem(cartItemVo);
        log.info("장바구니에 추가된 상품정보={}",cartService.findItem(cart_Item_num));

        return "redirect:/com.solponge/product/"+productId;
    }



    /* 장바구니 아이템 삭제*/
    @PostMapping("/deleteCartItem")
    public String cartItemDelete(@RequestParam("cartItemNum") int cartItemNum,
                                 HttpServletRequest request){
        MemberVo loginMember = getLoginMember(request);
        log.info("delete.loginMember={}",loginMember);
        log.info("cartItem_num={}",cartItemNum);
        cartService.deleteItem(cartItemNum);

        return "redirect:/com.solponge/member/" + loginMember.getMEMBER_NO() + "/myPage/cart";
    }

    private MemberVo getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (MemberVo) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }
    
   
}
