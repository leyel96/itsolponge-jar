package com.solponge.web.view.login;

import com.solponge.domain.member.MemberVo;
import com.solponge.domain.member.login.LoginService;
import com.solponge.web.view.login.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/com.solponge")
public class LoginController {
    /**
     * 로그인 컨트롤러
     */
    private final LoginService loginService;

    @GetMapping("/login")
    String loginGet(Model model){
        model.addAttribute("LoginForm", new LoginForm());
        return "member/loginForm";
    }

    /*로그인 검증*/
    @PostMapping("/login")
    String loginPost(@Validated @ModelAttribute("loginForm")LoginForm form, BindingResult bindingResult, HttpServletRequest request, Model model,
                     @RequestParam(defaultValue = "/com.solponge/main")String redirectURL){
        System.out.println("loging");
        System.out.println(form.getMemberId()+", "+ form.getMemberPwd());
        if(bindingResult.hasErrors()){
            return "member/loginForm";
        }
        MemberVo loginMember = loginService.login(form.getMemberId(), form.getMemberPwd());

        if (loginMember==null){//회원을 못 찾을때
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            log.info("bindingResult={}",bindingResult);
            model.addAttribute("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "member/loginForm";
        }
        if (loginMember.getMEMBER_situation() == 1){//회원 탈퇴시
            bindingResult.reject("loginFail","해당 아이디는 회원 탈퇴 처리 되었습니다.");
            log.info("bindingResult={}",bindingResult);
            model.addAttribute("loginFail", "해당 아이디는 회원 탈퇴 처리 되었습니다.");
            return "member/loginForm";
        }
        //로그인 성공처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성 반환
        HttpSession session = request.getSession();
        //세션에 로그인 회원정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);


        String prevPage = (String) session.getAttribute("prevPage");


        // 이전 페이지 URL이 없는 경우, 그리고 현재 요청 url이 존재할 경우
        if (prevPage == null || prevPage.isEmpty()) {
            return "redirect:"+redirectURL;
        }

        // 로그인 성공 후 이전 페이지로 redirect
        return "redirect:" + prevPage;

    }

    /*로그아웃*/
    @GetMapping("/logout")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session!=null){
            session.invalidate();
        }

        return "redirect:/com.solponge/main";


    }


}
