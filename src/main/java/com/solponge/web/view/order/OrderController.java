package com.solponge.web.view.order;

import com.solponge.domain.cart.CartItem;
import com.solponge.domain.cart.CartItemVo;
import com.solponge.domain.cart.CartService;
import com.solponge.domain.member.MemberService;
import com.solponge.domain.member.MemberVo;
import com.solponge.domain.order.OrderVo;
import com.solponge.domain.product.productService;
import com.solponge.domain.product.productVo;
import com.solponge.web.view.login.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@Controller
@Slf4j
@RequiredArgsConstructor

@RequestMapping("/com.solponge/member/{MEMBER_NO}")
public class OrderController {
    private final productService productService;
    private final MemberService memberService;
    private final CartService cartService;

    @GetMapping("/{productId}/{quantityinput}/true")
    public String addItem(@PathVariable int productId,
                              @PathVariable int quantityinput,
                              HttpServletRequest request){

        MemberVo loginMember = getLoginMember(request);
        //받아온 상품번호로 상품객체 소환
        productVo getproduct = productService.getproduct(productId);
        //cartItem 객체를 생성하여 필요한 값을 cartItemVo로 전달
        CartItemVo cartItemVo = new CartItemVo(new CartItem(loginMember,getproduct, quantityinput));
        //받아온 상품정보를 CART_ITEM 에 저장
        int cart_Item_num = cartService.addItem(cartItemVo);
        log.info("구매하기에 추가된 상품정보={}",cartService.findItem(cart_Item_num));

        return "redirect:/com.solponge/member/"+loginMember.getMEMBER_NO()+"/myPage/cart";
    }



    @PostMapping("/payments")
    public String postItem(HttpServletRequest request, @RequestParam("cartItems") List<String> cartItems, @RequestParam(value = "order", required = false) List<String> orderProductNums,
                           Model model) {

        MemberVo loginMember = getLoginMember(request);//사용자 확인

        List<OrderVo> orderList = new ArrayList<>();
        int oneItem=1;
        String a = cartItems.toString();
        String[] aa = a .split(",");
        if (cartItems.size() != oneItem && aa.length!=4) { // 하나의 상품만을 주문한것이 아닐때
            for (String cartItem : cartItems) {
                String[] cartItemArray = cartItem.split(",");
                int productNum = Integer.parseInt(cartItemArray[0]);
                int orderStock = Integer.parseInt(cartItemArray[1]);
                int memberNum = Integer.parseInt(cartItemArray[2]);
                int cartItemNum = Integer.parseInt(cartItemArray[3]);
                if (orderProductNums != null && orderProductNums.contains(Integer.toString(cartItemNum))) {
                    OrderVo order = new OrderVo();
                    order.setCART_ITEM_NUM(cartItemNum);
                    order.setMEMBER_NUM(memberNum);
                    order.setPRODUCT_NUM(productNum);
                    order.setORDER_STOCK(orderStock);
                    orderList.add(order);
                    log.info("order.getORDER_STOCK()={}",order.getORDER_STOCK());
                }
            }
            }else{
            int productNum = Integer.parseInt(cartItems.get(0));
            int orderStock = Integer.parseInt(cartItems.get(1));
            int memberNum = Integer.parseInt(cartItems.get(2));
            int cartItemNum = Integer.parseInt(cartItems.get(3));

            if (cartItems.contains(Integer.toString(cartItemNum))) {
                //확인된 아이템들은 orderVo에 설정되어 orderList 에 저장됨
                OrderVo order = new OrderVo();
                order.setCART_ITEM_NUM(cartItemNum);
                order.setMEMBER_NUM(memberNum);
                order.setPRODUCT_NUM(productNum);
                order.setORDER_STOCK(orderStock);
                orderList.add(order);
            }
        }


        List<OrderVo> data = orderList;
        Map<String, Object> param = new HashMap<>();
        int total_price = 0;
        for (int i = 0; i < data.size(); i++){
            productVo input_product = productService.getproduct(data.get(i).getPRODUCT_NUM());
            int input_num = input_product.getProduct_num();
            param.put("img_"+input_num, input_product.getProduct_img());
            param.put("title_"+input_num, input_product.getProduct_title());
            param.put("price_"+input_num, input_product.getProduct_price());
            param.put("stock_"+input_num, data.get(i).getORDER_STOCK());
            param.put("cartItem_"+input_num,data.get(i).getCART_ITEM_NUM());
            total_price += input_product.getProduct_price() * data.get(i).getORDER_STOCK()+2500;
        }


        model.addAttribute("pinfo", param);
        model.addAttribute("oinfo", data);
        model.addAttribute("minfo", memberService.findByNo(loginMember.getMEMBER_NO()));
        model.addAttribute("total_price",total_price);


        log.info("orderList={}", orderList);
        return "product/payments";

    }




    private MemberVo getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (MemberVo) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }

}
