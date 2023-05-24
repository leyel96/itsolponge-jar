package com.solponge.domain.jobinfo.impl;

import com.solponge.domain.jobinfo.JopInfoVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class jobInfoDAO {

    @Autowired
    SqlSessionTemplate sqlSession;

    public int JopInfosearchlist_count(String SearchSelect, String SearchValue) {
        System.out.println("===> Spring JDBC로 JopInfosearchlist_count() 기능 처리");
        System.out.println(SearchSelect);
        System.out.println(SearchValue);
        Map<String, Object> param = new HashMap<>();
        switch (SearchSelect){
            case "all":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_COMPANYNAME":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_INFONAME":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_FTAGLIST":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_WORKTYPE":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_QUALIFICAT":
                param.put("SearchValue", SearchValue);
                break;
        }
        return sqlSession.selectOne("collection_of_sql_job_info.Searchlist_count_"+SearchSelect, param);
    }

    public List<JopInfoVo> JopInfosearchlist_page(String SearchSelect, String SearchValue, int start, int end) {
        System.out.println("===> Spring JDBC로 JopInfosearchlist_page() 기능 처리");
        System.out.println("SearchSelect_"+SearchSelect);
        System.out.println("SearchValue_"+SearchValue);
        System.out.println("start_"+start);
        System.out.println("end_"+end);
        Map<String, Object> param = new HashMap<>();
        param.put("start",start);
        param.put("end",end);
        switch (SearchSelect){
            case "all":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_COMPANYNAME":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_INFONAME":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_FTAGLIST":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_WORKTYPE":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_QUALIFICAT":
                param.put("SearchValue", SearchValue);
                break;
        }
        return sqlSession.selectList("collection_of_sql_job_info.Searchlist_"+SearchSelect, param);
    }

    //CRUD
    public List<JopInfoVo> getJopInfoListpage(int start, int end) {
        System.out.println("===> Spring JDBC로 getJopInfoListpage() 기능 처리");
        Map<String, Object> param = new HashMap<>();
        param.put("start",start);
        param.put("end",end);
        System.out.println(start);
        System.out.println(end);
        return sqlSession.selectList("collection_of_sql_job_info.list_page", param);
    }

    public int getJopInfoList_count() {
        System.out.println("===> Spring JDBC로 getJopInfoList() 기능 처리");
        return sqlSession.selectOne("collection_of_sql_job_info.list_count");
    }

    public List<JopInfoVo> getJopInfoNewTop8List() {
        System.out.println("===> Spring JDBC로 getJopInfoNewTop8List() 기능 처리");
        return sqlSession.selectList("collection_of_sql_job_info.list_new_8");
    }

    public List<JopInfoVo> getCompanyTojobinfoList(String companyname) {
        System.out.println("===> Spring JDBC로 getCompanyTojobinfoList() 기능 처리");
        Map<String, Object> param = new HashMap<>();
        param.put("companyname", companyname);
        return sqlSession.selectList("collection_of_sql_job_info.ompanyTojobinfoList", param);
    }

    public JopInfoVo getJopInfo(int infonum){
        System.out.println("===> Spring JDBC로 getJopInfo() 기능 처리");
        Map<String, Object> param = new HashMap<>();
        param.put("infonum", infonum);
        return sqlSession.selectOne("collection_of_sql_job_info.job_info", param);
    }

    public List<JopInfoVo> getCompanyInScrapList(String[] companynames) {
        System.out.println("===> Spring JDBC로 getCompanyInScrapList() 기능 처리");
        Map<String, Object> param = new HashMap<>();
        param.put("companynames", companynames);
        System.out.println(Arrays.toString(companynames));
        return sqlSession.selectList("collection_of_sql_job_info.companyInScrap", param);
    }
    public List<JopInfoVo> getInfoInScrapList(String[] infonames) {
        System.out.println("===> Spring JDBC로 getInfoInScrapList() 기능 처리");
        Map<String, Object> param = new HashMap<>();
        param.put("infonames", infonames);
        return sqlSession.selectList("collection_of_sql_job_info.infoInScrap", param);
    }

    public List<JopInfoVo> getInfoInScrapSearchList(String SearchSelect, String SearchValue, String[] infonames) {
        System.out.println("===> Spring JDBC로 getInfoInScrapSearchList() 기능 처리");
        System.out.println(SearchSelect);
        System.out.println(SearchValue);
        Map<String, Object> param = new HashMap<>();
        param.put("infonames", infonames);
        switch (SearchSelect){
            case "all":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_COMPANYNAME":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_INFONAME":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_FTAGLIST":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_QUALIFICAT":
                param.put("SearchValue", SearchValue);
                break;
            case "JopInfo_WORKTYPE":
                param.put("SearchValue", SearchValue);
                break;
        }
        return sqlSession.selectList("collection_of_sql_job_info.Mypage_Searchlist_"+SearchSelect, param);
    }

}
