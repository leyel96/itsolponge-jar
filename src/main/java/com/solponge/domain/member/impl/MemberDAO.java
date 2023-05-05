package com.solponge.domain.member.impl;

import com.solponge.domain.member.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class MemberDAO {
    SqlSessionTemplate sqlSession;

    public MemberDAO(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
    // CRUD 기능의 메소드 구현
    // 회원가입
    public List<MemberVo> membersearchlist(String SearchSelect, String SearchValue) {
        System.out.println("===> Spring JDBC로 membersearchlist() 기능 처리");
        System.out.println(SearchSelect);
        System.out.println(SearchValue);
        Map<String, Object> param = new HashMap<>();
        if(SearchValue.length()==0){
            SearchValue = "N o n e";
        }
        switch (SearchSelect){
            case "ALL":
                param.put("SearchValue", SearchValue);
                break;
            case "MEMBER_NAME":
                param.put("SearchValue", SearchValue);
                break;
            case "MEMBER_ID":
                param.put("SearchValue", SearchValue);
                break;
        }
        return sqlSession.selectList("member.Searchlist_"+SearchSelect, param);
    }


    public String memberSave(MemberVo member) {
        Map<String,Object> param = new HashMap<>();
        param.put("member_grade",member.getMEMBER_GRADE());
        param.put("member_id",member.getMEMBER_ID());
        param.put("member_pwd",member.getMEMBER_PWD());
        param.put("member_address",member.getMEMBER_ADDRESS());
        param.put("member_email",member.getMEMBER_EMAIL());
        param.put("member_phone",member.getMEMBER_PHONE());
        param.put("member_name",member.getMEMBER_NAME());
        log.info("member={}",member);
        sqlSession.insert("member.insertMember",param);
        return param.get("MEMBER_NO").toString();
    }


    public void memberUpdate(Long memberNo, MemberVo updateMember) {
        Map<String,Object> param = new HashMap<>();
        param.put("member_no",memberNo);
        param.put("member_pwd",updateMember.getMEMBER_PWD());
        param.put("member_address",updateMember.getMEMBER_ADDRESS());
        param.put("member_email",updateMember.getMEMBER_EMAIL());
        param.put("member_phone",updateMember.getMEMBER_PHONE());
        sqlSession.update("member.updateMember",param);
    }


    public void memberDelete(MemberVo member) {
        Map<String,Object> param = new HashMap<>();
        param.put("member_no",member.getMEMBER_NO());
        sqlSession.delete("member.deleteMember",param);
    }
    public void memberDelete(Long MEMBER_NO) {
        Map<String,Object> param = new HashMap<>();
        System.out.println("계정 삭제 시작");
        param.put("member_no",MEMBER_NO);
        sqlSession.delete("member.delete_member_num",param);
        sqlSession.delete("member.delete_CART_ITEM",param);
        sqlSession.delete("member.delete_member_No",param);
    }


    public MemberVo memberFindByNo(Long memberNo) {
        Map<String,Object> param = new HashMap<>();
        param.put("member_no",memberNo);
        log.info("OracleMemberDAO.memberNo={}",memberNo);

        MemberVo member = (MemberVo) sqlSession.selectOne("member.selectMember", param);
        log.info("OracleMemberDAO.member={}",member);
        return member;
    }


    public List<MemberVo> memberFindAll() {
        return sqlSession.selectList("member.list");
    }

    public Optional<MemberVo> findByMemberId(String memberId){
        /*List<Member> all = memberFindAll();
        for (Member member : all) {

            if(member.getMEMBER_ID().equals(memberId)){
                return Optional.of(member);
            }
        }

        return Optional.empty();*/

        return memberFindAll().stream()
                .filter(member -> member.getMEMBER_ID().equals(memberId)).findAny();
    }

}
