package com.solponge.web.view.payment;

import com.solponge.domain.JobScrap.responseScrap;
import com.solponge.domain.member.MemberVo;
import com.solponge.domain.payment.OutOfStockException;
import com.solponge.domain.payment.PaymentService;
import com.solponge.domain.payment.PaymentVO;
import com.solponge.domain.product.productService;
import com.solponge.domain.product.productVo;
import com.solponge.web.view.login.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/com.solponge/member/{MEMBER_NO}")
@SessionAttributes(names = SessionConst.LOGIN_MEMBER)
@RequiredArgsConstructor // 초기화 되지 않게 알아서 실행되는 녀석
public class PaymentController {
    private final PaymentService paymentService;
    private final productService productService;

    @GetMapping("/paymentList")
    public String produtslist(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                              Model model, HttpServletRequest request){
        List<PaymentVO> data = paymentService.getPaymentList(loginMember.getMEMBER_NO());

        Map<String, Object> param = new HashMap<>();
        for(int i = 0; i < data.size(); i++){
            productVo pvo = productService.getproduct(data.get(i).getProduct_num());
            int nn = pvo.getProduct_num();
            param.put("title"+nn, pvo.getProduct_title());
            param.put("stock"+nn, data.get(i).getPayment_stock());
            param.put("price"+nn, data.get(i).getPayment_stock() * pvo.getProduct_price());
            param.put("img"+nn, pvo.getProduct_img());
            param.put("date"+nn, data.get(i).getPayment_date());
        }
        model.addAttribute("member",loginMember);
        int pageSize = 15; // number of items per page
        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, data.size());
        int totalPages = (int) Math.ceil((double) data.size() / pageSize);
        List<PaymentVO> paginatedProducts = data.subList(start, end); // get the current page of products
        System.out.println(paginatedProducts.size());
        model.addAttribute("paginatedProducts", paginatedProducts);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("url", "?");
        model.addAttribute("member_No", loginMember.getMEMBER_NO());
        model.addAttribute("productinfo", param);
        model.addAttribute("payinfo", data);
        try{
            String id = loginMember.getMEMBER_ID();
            String grade = String.valueOf(loginMember.getMEMBER_GRADE());
            if(id !=null) {
                if(grade.equals("ADMIN")){
                    model.addAttribute("GRADE", "ADMIN");
                } else {
                    model.addAttribute("GRADE", "BASIC");
                }
            }
        }catch (Exception e){
            System.out.println("오류발생");
        }
        return "member/paymentList";
    }

    @PostMapping("/payments/pay")
    public String payinfo(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                          @RequestParam String payment_num,
                          @RequestParam int total_price,
                          @RequestParam String firstnum2,
                          @RequestParam String secnum2,
                          @RequestParam String thrnum2,
                          @RequestParam String m_name,
                          @RequestParam String sample6_postcode,
                          @RequestParam String sample6_address,
                          @RequestParam String sample6_detailAddress,
                          @RequestParam String delivery_info,
                          @RequestParam String email1,
                          @RequestParam String email2,
                          Model model, HttpServletRequest request){
        System.out.println("결제 컨트롤러");
        String address = sample6_postcode + "/" + sample6_address + "/" + sample6_detailAddress;
        String phone = firstnum2 + "-" + secnum2 + "-" + thrnum2;
        String email = email1 + "@" + email2;
        String[] product_num = request.getParameterValues("product_num");
        String[] payment_stock = request.getParameterValues("payment_stock");
        String[] cartItem_num = request.getParameterValues("cartItem_num");

        for (int i = 0; i < product_num.length; i++){
            int product_stock = productService.getproduct(Integer.parseInt(product_num[i])).getProduct_stock();
            try{
                if(product_stock - Integer.parseInt(payment_stock[i]) < 0){
                    throw new OutOfStockException("상품의 재고가 부족합니다.");
                }
            }catch (Exception e){
                return "product/stockfail";
            }
        }
        System.out.println(Arrays.toString(product_num));
        System.out.println(loginMember.getMEMBER_NO());
        System.out.println("payment_num_"+payment_num);
        Map<String, Object> map = new HashMap<>();
        map.put("member_No", loginMember.getMEMBER_NO());
        map.put("payment_num", payment_num);
        map.put("total_price", total_price);
        map.put("address", address);
        map.put("phone", phone);
        map.put("email", email);
        map.put("m_name", m_name);
        map.put("delivery_info", delivery_info);
        map.put("product_num", product_num);
        map.put("payment_stock", payment_stock);
        map.put("cartItem_num", cartItem_num);
        model.addAttribute("map", map);
        return "product/pay";
    }

    @GetMapping(value = "/payments/success")
    public String successmove(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                              Model model) {
        model.addAttribute("member_No",loginMember.getMEMBER_NO());
        return "product/success";
    }

    @GetMapping(value = "/payments/fail")
    public String failmove(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                           Model model) {
        model.addAttribute("member_No",loginMember.getMEMBER_NO());
        return "product/fail";
    }

    @GetMapping("/paymentList/delete")
    public String notVisible(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                             @RequestParam String payment_num,@RequestParam int product_num
            ,Model model, HttpServletRequest request){
        paymentService.notVisible(payment_num,product_num, loginMember.getMEMBER_NO());
        return "redirect:/com.solponge/member/"+loginMember.getMEMBER_NO()+"/paymentList";
    }


}