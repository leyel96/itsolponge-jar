package com.solponge.domain.member.impl;


import com.solponge.domain.member.MemberService;
import com.solponge.domain.member.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;

    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public Long join(MemberVo member) {
        String memberNo = memberDAO.memberSave(member);
        log.info("joinedMember={}",member.toString());
        return Long.valueOf(memberNo);
    }

    @Override
    public void update(Long memberNo, MemberVo member) {
        memberDAO.memberUpdate(memberNo,member);
        log.info("updatedMember={}->{}", findByNo(memberNo).toString(),member.toString());
    }

    @Override
    public void withdrawal(MemberVo member) {
        memberDAO.memberDelete(member);
    }

    public void withdrawal(Long MEMBER_NO) {
        memberDAO.memberDelete(MEMBER_NO);
    }
    @Override
    public MemberVo findByNo(Long memberNo) {
        return memberDAO.memberFindByNo(memberNo);
    }

    @Override
    public List<MemberVo> findAll() {
        return memberDAO.memberFindAll();
    }

    @Override
    public List<MemberVo> membersearchlist(String SearchSelect, String SearchValue) {
        return memberDAO.membersearchlist(SearchSelect, SearchValue);
    }

}
