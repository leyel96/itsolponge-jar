package com.solponge.web.view.member;

import com.solponge.domain.JobScrap.InfScrapVO;
import com.solponge.domain.JobScrap.JobScrapService;
import com.solponge.domain.JobScrap.companyScrapVO;
import com.solponge.domain.JobScrap.responseScrap;
import com.solponge.domain.jobinfo.JopInfoService;
import com.solponge.domain.jobinfo.JopInfoVo;
import com.solponge.domain.member.MemberVo;
import com.solponge.domain.member.impl.MemberServiceImpl;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/com.solponge/member")
public class MemberController {
    @Autowired
    private final MemberServiceImpl memberService;
    @Autowired
    private final JobScrapService jobscrapService;
    @Autowired
    private final JopInfoService jobinfoService;

    /**
     * 마이페이지
     */

    @GetMapping("/{MEMBER_NO}/myPage")
    public String getMyPage(Model model, HttpServletRequest request) {
        MemberVo loginMember = getLoginMember(request);
        try{
            String id = Objects.requireNonNull(loginMember).getMEMBER_ID();
            System.out.println(id);
            System.out.println(loginMember.getMEMBER_GRADE());
            String grade = String.valueOf(loginMember.getMEMBER_GRADE());
            if(id !=null) {
                if(grade.equals("ADMIN")){
                    model.addAttribute("GRADE", "ADMIN");
                } else {
                    model.addAttribute("GRADE", "BASIC");
                }
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        //회원 정보 조회
        model.addAttribute("member", loginMember);
        return "member/updateForm";
    }
    @PostMapping("/{MEMBER_NO}/myPage")
    public String updateMember(HttpSession Session,
                               RedirectAttributes redirectAttributes,
                               @RequestParam String member_pwd,
                               @RequestParam String email1,
                               @RequestParam String email2,
                               @RequestParam String sample6_postcode,
                               @RequestParam String sample6_address,
                               @RequestParam String sample6_detailAddress,
                               @RequestParam String firstnum,
                               @RequestParam String secnum,
                               @RequestParam String thrnum,
                               Model model,
                               HttpServletRequest request) {
        MemberVo loginMember = getLoginMember(request);
        //회원 정보 업데이트
        updateMember(loginMember, member_pwd, email1, email2,
                sample6_postcode, sample6_address, sample6_detailAddress,
                firstnum, secnum, thrnum);
        //업데이트한 회원정보를 세션에 저장
        sessionSave(loginMember, Session);
        return "redirect:/com.solponge/main";
    }

    @GetMapping("/{MEMBER_NO}/myPage/delete")
    public String deleteMember(@PathVariable Long MEMBER_NO, HttpServletRequest request) {
        getLoginMember(request);
        MemberVo member = memberService.findByNo(MEMBER_NO);



        memberService.withdrawal(MEMBER_NO);//멤버삭제


        HttpSession session = request.getSession(false);
        if (session!=null){
            session.invalidate();
        }
        return "redirect:/com.solponge/main";
    }

    @GetMapping("/{MEMBER_NO}/myPage/jobScrap")
    public String getjobScrap(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberVo loginMember, Model model,HttpServletRequest request) {
        if(loginMember == null) {
            return "redirect:/com.solponge/main";
        }
        model.addAttribute("member", loginMember);
        List<companyScrapVO> cvo = jobscrapService.getcompanyScrapVOScrapList(loginMember.getMEMBER_NO());
        List<InfScrapVO> ivo = jobscrapService.getinfoScrapVOScrapList(loginMember.getMEMBER_NO());
        String[] company_names;
        String[] info_names;
        if(cvo.size() == 0){
            System.out.println("company_names 비어있음");
            company_names = new String[1];
            company_names[0] = "N o n e";
            model.addAttribute("company_state", "NO");
        } else {
            company_names = new String[cvo.size()];
        }
        if(ivo.size() == 0){
            System.out.println("info_names 비어있음");
            info_names = new String[1];
            info_names[0] = "N o n e";
            model.addAttribute("jobinfo_state", "NO");
        } else {
            info_names = new String[ivo.size()];
        }

        int index = 0;
        for(companyScrapVO c :cvo){
            company_names[index] = c.getCompanyName();
            index++;
        }
        index = 0;
        for(InfScrapVO c :ivo){
            info_names[index] = c.getInfoname();
            index++;
        }

        List<JopInfoVo> response_c_info = jobinfoService.getCompanyInScrapList(company_names);
        Map<String, Map<String, Object>> responseData = new HashMap<>();
        List<JopInfoVo> data = jobinfoService.getInfoInScrapList(info_names);
        int div_company_count = 1;
        if (company_names.length%8!=0){
            div_company_count = (company_names.length/8)+1;
        }
        System.out.println(div_company_count);

        for(JopInfoVo vo : response_c_info){
            String companyName= vo.getCompanyname();
            if (responseData.containsKey(companyName)) {
                int before_count = (int) responseData.get(companyName).get(companyName+"count");
                responseData.computeIfAbsent(companyName, k-> new HashMap<>()).put(companyName+"count", before_count+1);
            } else {
                responseData.computeIfAbsent(companyName, k-> new HashMap<>()).put(companyName+"count", 1);
            }
            responseData.computeIfAbsent(companyName, k-> new HashMap<>()).put(companyName+"OneInfoName", vo.getInfoname());
            responseData.computeIfAbsent(companyName, k-> new HashMap<>()).put(companyName+"OneInfoNum", vo.getInfonum());
        }
        for(int i = 1; i <= (div_company_count*8)-company_names.length; i++){
            responseData.computeIfAbsent("none"+i, k-> new HashMap<>());
        }

        model.addAttribute("div_company_count", div_company_count);
        model.addAttribute("company_names", company_names);
        model.addAttribute("responseData", responseData);

        System.out.println(data);
        int pageSize = 10; // number of items per page
        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, data.size());
        int totalPages = (int) Math.ceil((double) data.size() / pageSize);
        List<JopInfoVo> paginatedProducts = data.subList(start, end);

        model.addAttribute("paginatedjobinfo", paginatedProducts);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("url", "?");
        try{
            Long userNo = loginMember.getMEMBER_NO();
            System.out.println(userNo);
            String id = loginMember.getMEMBER_ID();
            System.out.println(id);
            if(id !=null) {
                System.out.println("비교시작");
                Map<String, String> params_company = new HashMap<>();
                Map<String, String> params_info = new HashMap<>();
                for(companyScrapVO c :cvo){
                    params_company.put("response_"+c.getCompanyName(), c.getCompanyName());
                }
                for(InfScrapVO c :ivo){
                    params_info.put("response_"+c.getInfoname(), c.getInfoname());
                }
                model.addAttribute("JobScrap", params_company);
                model.addAttribute("JobScrap2", params_info);
            }
        }catch (Exception e){
            System.out.println("오류발생");
        }
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
        return "member/scrap";
    }

    @GetMapping("/{MEMBER_NO}/myPage/jobScrap/search")
    public String getjobScrapSearch(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberVo loginMember,
                                    Model model,
                                    HttpServletRequest request,
                                    @RequestParam("SearchSelect") String SearchSelec,
                                    @RequestParam("SearchValue") String SearchValue) {
        if(loginMember == null) {
            return "redirect:/com.solponge/main";
        }
        model.addAttribute("member", loginMember);

        List<companyScrapVO> cvo = jobscrapService.getcompanyScrapVOScrapList(loginMember.getMEMBER_NO());
        List<InfScrapVO> ivo = jobscrapService.getinfoScrapVOScrapList(loginMember.getMEMBER_NO());

        String[] company_names;
        String[] info_names;
        if(cvo.size() == 0){
            System.out.println("company_names 비어있음");
            company_names = new String[1];
            company_names[0] = "N o n e";
            model.addAttribute("company_state", "NO");
        } else {
            company_names = new String[cvo.size()];
        }
        if(ivo.size() == 0){
            System.out.println("info_names 비어있음");
            info_names = new String[1];
            info_names[0] = "N o n e";
            model.addAttribute("jobinfo_state", "NO");
        } else {
            info_names = new String[ivo.size()];
        }

        int index = 0;
        for(companyScrapVO c :cvo){
            company_names[index] = c.getCompanyName();
            index++;
        }
        index = 0;
        for(InfScrapVO c :ivo){
            info_names[index] = c.getInfoname();
            index++;
        }
        List<JopInfoVo> response_c_info = jobinfoService.getCompanyInScrapList(company_names);
        Map<String, Map<String, Object>> responseData = new HashMap<>();


        int div_company_count = 1;
        if (company_names.length%8!=0){
            div_company_count = (company_names.length/8)+1;
        }
        System.out.println(div_company_count);

        for(JopInfoVo vo : response_c_info){
            String companyName= vo.getCompanyname();
            if (responseData.containsKey(companyName)) {
                int before_count = (int) responseData.get(companyName).get(companyName+"count");
                responseData.computeIfAbsent(companyName, k-> new HashMap<>()).put(companyName+"count", before_count+1);
            } else {
                responseData.computeIfAbsent(companyName, k-> new HashMap<>()).put(companyName+"count", 1);
            }
            responseData.computeIfAbsent(companyName, k-> new HashMap<>()).put(companyName+"OneInfoName", vo.getInfoname());
            responseData.computeIfAbsent(companyName, k-> new HashMap<>()).put(companyName+"OneInfoNum", vo.getInfonum());
        }
        for(int i = 1; i <= (div_company_count*8)-company_names.length; i++){
            responseData.computeIfAbsent("none"+i, k-> new HashMap<>());
        }

        model.addAttribute("div_company_count", div_company_count);
        model.addAttribute("company_names", company_names);
        model.addAttribute("responseData", responseData);
//        model.addAttribute("response_i_info", response_i_info);

        List<JopInfoVo> data = jobinfoService.getInfoInScrapSearchList(SearchSelec, SearchValue, info_names);

        System.out.println(data.size());
        int pageSize = 10; // number of items per page
        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, data.size());
        int totalPages = (int) Math.ceil((double) data.size() / pageSize);
        List<JopInfoVo> paginatedProducts = data.subList(start, end);

        model.addAttribute("paginatedjobinfo", paginatedProducts);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("url", "?");
        try{
            Long userNo = loginMember.getMEMBER_NO();
            System.out.println(userNo);
            String id = loginMember.getMEMBER_ID();
            System.out.println(id);
            if(id !=null) {
                System.out.println("비교시작");
                Map<String, String> params_company = new HashMap<>();
                Map<String, String> params_info = new HashMap<>();
                for(companyScrapVO c :cvo){
                    params_company.put("response_"+c.getCompanyName(), c.getCompanyName());
                }
                for(InfScrapVO c :ivo){
                    params_info.put("response_"+c.getInfoname(), c.getInfoname());
                }
                model.addAttribute("JobScrap", params_company);
                model.addAttribute("JobScrap2", params_info);
            }
        }catch (Exception e){
            System.out.println("오류발생");
        }
        String url = request.getQueryString();
        System.out.println(url);
        if (url.contains("&page=")){
            int idx = url.indexOf("&page=");
            url = url.substring(0, idx);
        }

        String inputurl ="";
        if (url.contains("SearchSelect")){
            inputurl += "search?"+url+"&";
        } else {
            inputurl += "?";
        }
        System.out.println("들어가는 url: "+inputurl);
        model.addAttribute("url", inputurl);
        model.addAttribute("status", "Yes");

//        return "member/myscrap";
        return "member/scrap";
    }

    /**
     * 메서드
     */

    /*회원 정보 수정 시 해당 정보 세션에 저장*/
    private void sessionSave(MemberVo loginMember, HttpSession Session) {
        // 업데이트된 멤버 객체 찾기
        MemberVo updateMemeber = memberService.findByNo(loginMember.getMEMBER_NO());
        log.info("updatedMember={}",updateMemeber);
        //세션에 업데이트된 찾은 회원 정보 저장
        Session.setAttribute(SessionConst.LOGIN_MEMBER,updateMemeber);
    }

    /*문자열 합치기*/
    private void updateMember(MemberVo loginMember, String member_pwd, String email1, String email2, String sample6_postcode, String sample6_address, String sample6_detailAddress, String firstnum, String secnum, String thrnum) {
        String email = email1 + "@" + email2;
        String address = sample6_postcode + "/" + sample6_address + "/" + sample6_detailAddress;
        String phone = firstnum + "-" + secnum + "-" + thrnum;
        //멤버 수정정보 생성
        MemberVo memberVo = new MemberVo(member_pwd,address,email,phone);
        //멤버 업데이트
        memberService.update(loginMember.getMEMBER_NO(),memberVo);
    }

    /*회원정보 받음*/
    private MemberVo getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (MemberVo) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }
}