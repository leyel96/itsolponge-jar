package com.solponge.web.view.product;

import com.solponge.domain.member.MemberVo;
import com.solponge.domain.pageing.pageing;
import com.solponge.domain.product.productService;
import com.solponge.domain.product.productVo;
import com.solponge.web.view.login.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/com.solponge")
@RequiredArgsConstructor // 초기화 되지 않게 알아서 실행되는 녀석
public class produtController {
    private final productService productService;


    @GetMapping("/productList")
    public String produtslist(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                              Model model, HttpServletRequest request){
        model.addAttribute("member",loginMember);
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
        List<productVo> data = productService.getproductList();
        new pageing(20, request, data, model, "paginatedProducts");

        return "product/productlist";
    }
    @GetMapping("/productList/search")
    public String produtsearchlist(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                                   Model model, HttpServletRequest request,
                                   @RequestParam("SearchSelect") String SearchSelec,
                                   @RequestParam("SearchValue") String SearchValue){
        model.addAttribute("member",loginMember);
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
        List<productVo> data = productService.produtsearchlist(SearchSelec, SearchValue);
        for(productVo d: data){
            System.out.println("책이름"+d.getProduct_title());
        }
        System.out.println(data.size());
        String url = request.getQueryString();
        new pageing(20, request, data, model,"paginatedProducts", url);

        return "product/productlist";

    }

    @GetMapping("/product/{productId}")
    public String produtpage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                             @PathVariable int productId, Model model){
        model.addAttribute("member",loginMember);
        productVo vo = productService.getproduct(productId);
        System.out.println(productId);
        model.addAttribute("productVo", vo);
        return "product/productpage";
    }

    @PostMapping("/product/{productId}")
    public String produtprocess(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                                @PathVariable int productId, Model model,
                                @RequestParam Map<String,String> requestParams,
                                @RequestParam("quantityinput") String quantityinput){
        System.out.println("productId,"+productId);
        System.out.println("quantityinput,"+quantityinput);
        boolean check = requestParams.containsKey("button1");
        model.addAttribute("product_num",productId);
        model.addAttribute("quantityinput",quantityinput);
        model.addAttribute("member",loginMember);
        model.addAttribute("check",check);
        if (check) {
            return "redirect:/com.solponge/member/"+loginMember.getMEMBER_NO()+"/"+ productId + "/" + quantityinput + "/true";
        } else {
            return "redirect:/com.solponge/member/"+loginMember.getMEMBER_NO()+"/myPage/cart/"+ productId + "/" + quantityinput;
        }

    }
}


