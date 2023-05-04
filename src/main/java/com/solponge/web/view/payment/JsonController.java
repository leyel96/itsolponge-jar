package com.solponge.web.view.payment;


import com.solponge.domain.cart.CartService;
import com.solponge.domain.member.MemberVo;
import com.solponge.domain.payment.OutOfStockException;
import com.solponge.domain.payment.PaymentService;
import com.solponge.domain.payment.payVO;
import com.solponge.domain.product.productService;
import com.solponge.domain.product.productVo;
import com.solponge.web.view.login.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/com.solponge/member/{MEMBER_NO}")
@SessionAttributes(names = SessionConst.LOGIN_MEMBER)
@RequiredArgsConstructor // 초기화 되지 않게 알아서 실행되는 녀석
@RestController
@Slf4j
public class JsonController {

    private final PaymentService paymentService;
    private final productService productService;
    private final CartService cartService;

    @PostMapping(value = "/payments/su")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertPayment(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                                @RequestBody(required = false) payVO payVO, HttpServletRequest request) {
        System.out.println("ajax/json 넘기는곳");
        System.out.println(payVO.getPayment_num());
        log.info("payVO={}",payVO);
        productVo productVo;

        for (int i=0; i < payVO.getProduct_num().length; i++){
            productVo = productService.getproduct(payVO.getProduct_num()[i]);
            for (int j = 0; j < payVO.getPayment_stock().length; j++){
                if(productVo.getProduct_stock() - payVO.getPayment_stock()[i] < 0){
                    throw new OutOfStockException("상품의 재고가 부족합니다.");
                }else {
                    paymentService.removeStock(productVo.getProduct_num(), productVo.getProduct_stock(), payVO.getPayment_stock()[i]);
                }
            }
            System.out.println(payVO.getPayment_num()+ payVO.getProduct_num()[i]+ loginMember.getMEMBER_NO()+ payVO.getPayment_stock()[i]+ payVO.getPhone()+ payVO.getEmail()+ payVO.getAddress()+ payVO.getDelivery_info());
            paymentService.insertPayment(payVO.getPayment_num(), payVO.getProduct_num()[i], loginMember.getMEMBER_NO(), payVO.getPayment_stock()[i], payVO.getPhone(), payVO.getEmail(), payVO.getAddress(), payVO.getDelivery_info());
            int cartItemNum = payVO.getCartItem_num()[i];
            log.info("json.cartItemNum={}",cartItemNum);
            cartService.deleteItem(cartItemNum);
        }

    }


}
