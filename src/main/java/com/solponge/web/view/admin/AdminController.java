package com.solponge.web.view.admin;


import com.solponge.domain.admin.*;
import com.solponge.domain.admin.impl.OrderServiceImpl;
import com.solponge.domain.member.Grade;
import com.solponge.domain.member.MemberService;
import com.solponge.domain.member.MemberVo;
import com.solponge.domain.member.impl.MemberServiceImpl;
import com.solponge.domain.pageing.pageing;
import com.solponge.domain.product.productService;
import com.solponge.domain.product.productVo;
import com.solponge.web.view.login.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/com.solponge/admin")
public class AdminController {

    @Autowired
    OrderService os;
    @Autowired
    MemberService ms;

   private final MemberServiceImpl memberService;
   private final productService productService;
   private final OrderServiceImpl orderService;



    /**
     * 회원관리 정보
     */

    @GetMapping("/member/search")
    public String produtslist(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                              Model model,
                              @RequestParam("SearchSelect") String SearchSelect,
                              @RequestParam("SearchValue") String SearchValue){
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

        model.addAttribute("member",loginMember);
        List<MemberVo> data = ms.membersearchlist(SearchSelect, SearchValue);
        System.out.println("Page request received at /member/search");

        System.out.println("SearchSelect: " + SearchSelect);
        System.out.println("SearchValue: " + SearchValue);

        System.out.println(data);

        System.out.println("검색후 페이지 이동중");
        model.addAttribute("members",data);
        return "admin/member";
    }
    @GetMapping("/member")
    public String member(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                         Model model) {
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
        model.addAttribute("members", memberService.findAll());
        log.info("findAll={}", memberService.findAll());
        return "admin/member";
    }


    /*회원정보 수정*/

    @GetMapping("/member/{member_No}/update")
    public String editMember(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
            @PathVariable Long member_No, Model model,HttpServletRequest request) {
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

        MemberVo memberVo = getLoginMember(request);
        if (memberVo == null || memberVo.getMEMBER_GRADE() != Grade.ADMIN) {
            return "redirect:/com.solponge/main";
        }
        MemberVo member = memberService.findByNo(member_No);
        log.info("adminMember={}",member);
        model.addAttribute("member", member);
        return "admin/updateForm";
    }

    @PostMapping("/member/{member_No}/update")
    public String edit(HttpServletRequest request,
                       @PathVariable Long member_No, @ModelAttribute MemberVo member,
                       @RequestParam String member_Pwd,
                       @RequestParam String member_Address,
                       @RequestParam String member_Phone,
                       @RequestParam String member_Email,
                       RedirectAttributes redirectAttributes) {
        MemberVo memberVo = getLoginMember(request);
        if (memberVo == null || memberVo.getMEMBER_GRADE() != Grade.ADMIN) {
            return "redirect:/com.solponge/main";
        }
        log.info("member_No={}",member_No);
        log.info("beforeUpdate={}",member);
        MemberVo updateMember = new MemberVo(member_Pwd,member_Address,member_Email,member_Phone);
        memberService.update(member_No,updateMember);
        log.info("updateMember={}",memberService.findByNo(member_No));
        return "redirect:/com.solponge/admin/member";
    }

    /*회원 삭제*/

    @GetMapping("/member/{member_No}/delete")
    public String deleteMember(@PathVariable Long member_No) {
//        MemberVo member = memberService.findByNo(member_No);
        memberService.withdrawal(member_No);
        return "redirect:/com.solponge/admin/member";
    }

    /**
     * 상품관리 정보
     */

    @GetMapping("/product/search")
    public String inqprodutsearchlist(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                                   Model model, HttpServletRequest request,
                                   @RequestParam("SearchSelect") String SearchSelec,
                                   @RequestParam("SearchValue") String SearchValue){
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
        model.addAttribute("member",loginMember);

        List<productVo> data = productService.produtsearchlist(SearchSelec, SearchValue);

        String url = request.getQueryString();
        new pageing(20, request, data, model,"paginatedProducts", url);

        return "admin/inqProduct";

    }

    @GetMapping("/product") //수정완료
    public String product(Model model, HttpServletRequest request,
                          @SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember) {
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
        return "admin/inqProduct";
    }

    /*상품 상세*/
    @GetMapping("/product/{productNum}")
    public String deProduct(@PathVariable int productNum, Model model) {
        productVo product = productService.getproduct(productNum);
        System.out.println(product.getProduct_num());
        System.out.println(productNum);
        model.addAttribute("product", product);
        return "admin/deProduct";
    }

    /*상품 추가*/
    @GetMapping("/product/add")
    public String addProduct() {
        return "admin/addProduct";
    }
    @PostMapping("/product/add")
    public String addProduct(productVo product, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        System.out.println("컨트롤러");
        String productcode = request.getParameter("myInput");
        System.out.println("addProduct_"+productcode);
        product.setProduct_code(productcode);
        String productNum = productService.insertproduct(product);
        redirectAttributes.addAttribute("productNum", productNum);
        redirectAttributes.addAttribute("status", "add");

        return "redirect:/com.solponge/admin/product";
    }

    /*상품 수정*/
    @GetMapping("/product/{productNum}/edit") //수정중
    public String editProduct(@PathVariable int productNum, Model model, HttpServletRequest request) {
        System.out.println(productNum);
        MemberVo memberVo = getLoginMember(request);
        if (memberVo == null || memberVo.getMEMBER_GRADE() != Grade.ADMIN) {
            return "redirect:/com.solponge/main";
        }
        productVo product = productService.getproduct(productNum);
        log.info("product={}",product);
        System.out.println(product.getProduct_code());
        model.addAttribute("product", product);
        return "admin/editProduct";
    }

    @PostMapping("/product/{productNum}/edit") //수정중
    public String edit(@PathVariable int productNum, @ModelAttribute productVo product, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        System.out.println("컨트롤러");
        MemberVo memberVo = getLoginMember(request);
        if (memberVo == null || memberVo.getMEMBER_GRADE() != Grade.ADMIN) {
            return "redirect:/com.solponge/main";
        }
        log.info("productNum={}",productNum);
        log.info("product={}",product);
        String productcode = request.getParameter("myInput"); //, @RequestBody Map<String, String> request
//        productcode = productcode.replace("<p><br data-cke-filler=\"true\"></p>", "");
//        productcode = productcode.replace("<p><br data-cke-filler=\"true\"></p>", "");
//        productcode = ""+ productcode + "";
        System.out.println(productcode);
        product.setProduct_code(productcode);
        productService.updateproduct(product);
//        redirectAttributes.addAttribute("status", "edit");
        System.out.println("Call");
        return "redirect:";
    }

    /*상품 삭제*/

    @GetMapping("/product/{productNum}/delete")
    public String deleteProduct(@PathVariable int productNum) {
        System.out.println(productNum);
        productService.deleteproduct(productNum);
        return "redirect:/com.solponge/admin/product";
    }

    @PostMapping("/product/{productNum}/delete")
    public String delete(@PathVariable int productNum) {
        System.out.println(productNum);
        productService.deleteproduct(productNum);
        return "redirect:/com.solponge/admin/product";
    }

    /**
     * 주문 관리 정보
     */
    @GetMapping("/order/search")
    public String ordersearchlist(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                                  Model model, HttpServletRequest request,
                                  @RequestParam("SearchSelect") String SearchSelec,
                                  @RequestParam("SearchValue") String SearchValue){
        model.addAttribute("member",loginMember);
        List<AdminOrderVo> data = os.ordersearchlist(SearchSelec, SearchValue);
        List<PaymentEntity> paymentEntities = new ArrayList<>();
        for (AdminOrderVo datum : data) {
            String paymentNum = datum.getPayment_num();
            int paymentStock = datum.getPayment_stock();
            String paymentEmail = datum.getPayment_email();
            Date paymentDate = datum.getPayment_date();
            String paymentAddress = datum.getPayment_address();
            String paymentPhone = datum.getPayment_phone();

            productVo getProduct = productService.getproduct(datum.getProduct_num());
            MemberVo getMember = memberService.findByNo(datum.getMember_no());

            Payment payment = new Payment(paymentNum,
                    paymentStock,
                    paymentDate,
                    paymentPhone,
                    paymentEmail,
                    paymentAddress);



            Delivery delivery = new Delivery(datum.getDelivery_info(),datum.getDelivery_num());

            PaymentEntity paymentEntity = new PaymentEntity(payment, getMember, getProduct,delivery);
            paymentEntity.setVisible(datum.getVisible());
            paymentEntity.setSuccess(datum.getSuccess());
            paymentEntities.add(paymentEntity);
        }

        model.addAttribute("paymentEntities",paymentEntities);
        System.out.println("data" + data.toString());
        int pageSize = 20; // number of items per page
        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, data.size());
        int totalPages = (int) Math.ceil((double) data.size() / pageSize);
        List<AdminOrderVo> paginatedProducts = data.subList(start, end); // get the current page of products
        model.addAttribute("paginatedProducts", paginatedProducts);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        String url = request.getQueryString();
        System.out.println(SearchSelec);
        System.out.println(SearchValue);
        url = url.replaceAll("&page=[0-9]", "");
        String inputurl ="";
        if (url.contains("SearchSelect")){
            inputurl += "search?"+url+"&";
        } else {
            inputurl += "?";
        }
        inputurl = inputurl.substring(0, inputurl.length()-1);
        model.addAttribute("url", inputurl);
        model.addAttribute("status", "Yes");

        System.out.println(inputurl);
        return "admin/orderManager";
    }

    @GetMapping("/order")
    public String order(Model model, HttpServletRequest request) {
        List<AdminOrderVo> data = orderService.getBoardList();
        List<PaymentEntity> paymentEntities = new ArrayList<>();
        for (AdminOrderVo datum : data) {
            String paymentNum = datum.getPayment_num();
            int paymentStock = datum.getPayment_stock();
            String paymentEmail = datum.getPayment_email();
            Date paymentDate = datum.getPayment_date();
            String paymentAddress = datum.getPayment_address();
            String paymentPhone = datum.getPayment_phone();

            productVo getProduct = productService.getproduct(datum.getProduct_num());
            MemberVo getMember = memberService.findByNo(datum.getMember_no());

            Payment payment = new Payment(paymentNum,
                    paymentStock,
                    paymentDate,
                    paymentPhone,
                    paymentEmail,
                    paymentAddress);



            Delivery delivery = new Delivery(datum.getDelivery_info(),datum.getDelivery_num());

            PaymentEntity paymentEntity = new PaymentEntity(payment, getMember, getProduct,delivery);
            paymentEntity.setVisible(datum.getVisible());
            paymentEntity.setSuccess(datum.getSuccess());
            paymentEntities.add(paymentEntity);
        }

        model.addAttribute("paymentEntities",paymentEntities);
        new pageing(10, request, data, model, "paginatedProducts");

        return "admin/orderManager";
    }
    /*송장번호 입력, 주문업데이트*/
    @PostMapping("/order/{paymentNum}/update")
    public String update(@PathVariable String paymentNum,
                         @RequestParam String deliveryNum,
                         RedirectAttributes redirectAttributes) {
        System.out.println("update 불러옴");
        log.info("paymentNum={}",paymentNum);
        log.info("deliveryNum={}",deliveryNum);
        List<AdminOrderVo> memberOrders = orderService.getMemberOrders(paymentNum);
        log.info("memberOrders={}",memberOrders);
        for (AdminOrderVo memberOrder : memberOrders) {
            memberOrder.setPayment_num(paymentNum);
            memberOrder.setDelivery_num(Long.valueOf(deliveryNum));
            orderService.updateOrder(paymentNum,memberOrder);
        }
        log.info("memberOrders={}",memberOrders);
        redirectAttributes.addAttribute("status", "edit");
        System.out.println("호출됨");
        return "redirect:/com.solponge/admin/order";
    }

    /**
     * 로그인 메서드
     */
    private MemberVo getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (MemberVo) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }

}
