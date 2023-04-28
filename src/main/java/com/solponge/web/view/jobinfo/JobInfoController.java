package com.solponge.web.view.jobinfo;


import com.solponge.domain.JobScrap.JobScrapService;
import com.solponge.domain.JobScrap.responseScrap;
import com.solponge.domain.jobinfo.JopInfoService;
import com.solponge.domain.jobinfo.JopInfoVo;
import com.solponge.domain.member.MemberVo;
import com.solponge.domain.pageing.pageing;
import com.solponge.web.view.login.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/com.solponge")
@SessionAttributes(names = SessionConst.LOGIN_MEMBER)
@RequiredArgsConstructor // 초기화 되지 않게 알아서 실행되는 녀석
public class JobInfoController {
    private final JobScrapService jobscrapService;
    private final JopInfoService jopinfoService;

    @GetMapping("/jobinfolist")
    public String jobinfolist(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                              Model model, HttpServletRequest request){
        model.addAttribute("member",loginMember);
//        model.addAttribute("getJopInfoList", jopinfoService.getJopInfoList());
        int data = jopinfoService.getJopInfoList();

        new pageing(20, request, data, model,"paginatedjobinfo",jopinfoService);

        try{
            Long userNo = loginMember.getMEMBER_NO();
            System.out.println(userNo);
            String id = loginMember.getMEMBER_ID();
            System.out.println(id);
            String grade = String.valueOf(loginMember.getMEMBER_GRADE());
            if(id !=null) {
                if(grade.equals("ADMIN")){
                    model.addAttribute("GRADE", "ADMIN");
                } else {
                    model.addAttribute("GRADE", "BASIC");
                }
                new responseScrap(model, userNo, jobscrapService, "JobScrap", "JobScrap2");
            }
        }catch (Exception e){
            System.out.println("오류발생");
        }
        return "jobinfo/jobinfotlist";
    }

    @GetMapping("/jobinfolist/search")
    public String jobinfosearchlist(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                                   Model model, HttpServletRequest request,
                                   @RequestParam("SearchSelect") String SearchSelec,
                                   @RequestParam("SearchValue") String SearchValue){
        model.addAttribute("member",loginMember);
        String url = request.getQueryString();
        int data = jopinfoService.JopInfosearchlist_count(SearchSelec, SearchValue);
        new pageing(20, request, data, model,"paginatedjobinfo",jopinfoService, SearchSelec, SearchValue, url);

        try{
            Long userNo = loginMember.getMEMBER_NO();
            System.out.println(userNo);
            String id = loginMember.getMEMBER_ID();
            System.out.println(id);
            String grade = String.valueOf(loginMember.getMEMBER_GRADE());
            if(id !=null) {
                if(grade.equals("ADMIN")){
                    model.addAttribute("GRADE", "ADMIN");
                } else {
                    model.addAttribute("GRADE", "BASIC");
                }
                new responseScrap(model, userNo, jobscrapService, "JobScrap", "JobScrap2");
            }
        }catch (Exception e){
            System.out.println("오류발생");
        }

        return "jobinfo/jobinfotlist";
    }

    @GetMapping("/jobinfolist/{companynum}")
    public String companynamejobinfolist(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                                   Model model, HttpServletRequest request,
                                   @PathVariable String companynum){
        System.out.println("회사모음");
        System.out.println(companynum);
        model.addAttribute("member",loginMember);
        List<JopInfoVo> data = jopinfoService.getCompanyTojobinfoList(companynum);
        String url = request.getRequestURI();
        new pageing(10, request, data, model,"paginatedjobinfo", url);

        try{
            Long userNo = loginMember.getMEMBER_NO();
            System.out.println(userNo);
            String id = loginMember.getMEMBER_ID();
            System.out.println(id);
            String grade = String.valueOf(loginMember.getMEMBER_GRADE());
            if(id !=null) {
                if(grade.equals("ADMIN")){
                    model.addAttribute("GRADE", "ADMIN");
                } else {
                    model.addAttribute("GRADE", "BASIC");
                }
                new responseScrap(model, userNo, jobscrapService, "JobScrap", "JobScrap2");
            }
        }catch (Exception e){
            System.out.println("오류발생");
        }

        return "jobinfo/companytlist";
    }



    @GetMapping("/jobinfo/{INFONUM}")
    public String jobinfopage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) MemberVo loginMember,
                              @PathVariable int INFONUM, Model model){
        System.out.println("INFONUM,"+INFONUM);
        JopInfoVo vo = jopinfoService.getJopInfo(INFONUM);
        model.addAttribute("member",loginMember);
        System.out.println(INFONUM);
        model.addAttribute("JopInfoVo", vo);
        try{
            Long userNo = loginMember.getMEMBER_NO();
            System.out.println(userNo);
            String id = loginMember.getMEMBER_ID();
            System.out.println(id);
            String grade = String.valueOf(loginMember.getMEMBER_GRADE());
            if(id !=null) {
                if(grade.equals("ADMIN")){
                    model.addAttribute("GRADE", "ADMIN");
                } else {
                    model.addAttribute("GRADE", "BASIC");
                }
                new responseScrap(model, userNo, jobscrapService, "JobScrap", "JobScrap2");
            }
        }catch (Exception e){
            System.out.println("오류발생");
        }

        return "jobinfo/jobinfopage";
    }

    private MemberVo getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (MemberVo) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }
}
