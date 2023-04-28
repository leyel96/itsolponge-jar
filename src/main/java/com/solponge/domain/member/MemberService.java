package com.solponge.domain.member;



import java.util.List;

public interface MemberService {
    Long join(MemberVo member);
    void update(Long memberNo, MemberVo member);
    void withdrawal(MemberVo member);//탈퇴
    MemberVo findByNo(Long memberNo);

    List<MemberVo> findAll();

    List<MemberVo> membersearchlist(String SearchSelect, String SearchValue);
}
