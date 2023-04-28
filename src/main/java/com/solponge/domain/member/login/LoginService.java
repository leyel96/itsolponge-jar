package com.solponge.domain.member.login;

import com.solponge.domain.member.MemberVo;
import com.solponge.domain.member.impl.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private  final MemberDAO memberRepository;

    /**
     *  return null 이면 실패
     */

    public MemberVo login(String loginId, String loginPwd){
       /* Optional<Member> byMemberId = memberRepository.findByMemberId(loginId);
        Member member = byMemberId.get();
        if (member.getMEMBER_PWD().equals(loginPwd)){
            return member;
        }else{
            return null;
        }*/

       return memberRepository.findByMemberId(loginId)
               .filter(m->m.getMEMBER_PWD().equals(loginPwd)).orElse(null);
    }



}
