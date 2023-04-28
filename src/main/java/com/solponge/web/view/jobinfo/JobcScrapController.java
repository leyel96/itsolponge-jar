package com.solponge.web.view.jobinfo;

import com.solponge.domain.JobScrap.InfScrapVO;
import com.solponge.domain.JobScrap.JobScrapService;
import com.solponge.domain.JobScrap.companyScrapVO;
import com.solponge.web.view.login.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@Slf4j
@RequiredArgsConstructor
@RestController
@SessionAttributes(names = SessionConst.LOGIN_MEMBER)
public class JobcScrapController {
    /**
     * 공고 스크랩
     */
    private final JobScrapService jobscrapService;
    /*회사 스크랩*/
    @PostMapping("/scrap/company")
    public void scrapCompanyInsert(@RequestBody companyScrapVO companyscrapvO) {

        System.out.println("컨트롤러: " + companyscrapvO.getCompanyName());
        System.out.println("컨트롤러: " + companyscrapvO.getMember_No());
        jobscrapService.insertJobScrap_company(companyscrapvO);
    }

    /*회사 스크랩 제거*/
    @PostMapping("/scrap/company/delete")
    public void scrapCompanyDelete(@RequestBody companyScrapVO companyscrapvO) {
        System.out.println("컨트롤러: " + companyscrapvO.getCompanyName());
        System.out.println("컨트롤러: " + companyscrapvO.getMember_No());
        Long meber_no = companyscrapvO.getMember_No();
        String companyName = companyscrapvO.getCompanyName();
        jobscrapService.deleteJobScrap_company(meber_no, companyName);
    }

    /*마이페이지 내 회사 스크랩*/
    @PostMapping("/scrap/company/delete/mypage")
    public String scrapCompanyDeleteMypage(@RequestBody companyScrapVO companyscrapvO) {
        System.out.println("컨트롤러: " + companyscrapvO.getCompanyName());
        System.out.println("컨트롤러: " + companyscrapvO.getMember_No());
        Long meber_no = companyscrapvO.getMember_No();
        String companyName = companyscrapvO.getCompanyName();
        jobscrapService.deleteJobScrap_company(meber_no, companyName);
        return "redirect:./";
    }

    /*공고 찜*/
    @PostMapping("/scrap/job")
    public void scrapJobInsert(@RequestBody InfScrapVO infScrapVO) {
        System.out.println("컨트롤러: " + infScrapVO.getInfoname());
        System.out.println("컨트롤러: " + infScrapVO.getMember_No());
        jobscrapService.insertJobScrap_infoname(infScrapVO);
    }

    /*공고 삭제*/
    @PostMapping("/scrap/job/delete")
    public void scrapJobDelete(@RequestBody InfScrapVO infScrapVO) {
        System.out.println("컨트롤러_삭제: " + infScrapVO.getInfoname());
        System.out.println("컨트롤러_삭제: " + infScrapVO.getMember_No());
        Long meber_no = infScrapVO.getMember_No();
        String infoname = infScrapVO.getInfoname();
        jobscrapService.deleteJobScrap_infoname(meber_no, infoname);
    }

}

