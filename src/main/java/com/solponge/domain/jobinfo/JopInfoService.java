package com.solponge.domain.jobinfo;


import java.util.List;

public interface JopInfoService {
    // CRUD
    void insertJopInfo(JopInfoVo vo);

    void updateJopInfo(JopInfoVo vo);

    void deleteJopInfo(int infonum);

    JopInfoVo getJopInfo(int infonum);

    int JopInfosearchlist_count(String SearchSelect, String SearchValue);

    List<JopInfoVo> JopInfosearchlist_page(String SearchSelect, String SearchValue, int start, int end);

    List<JopInfoVo> getJopInfoListpage(int start, int end);

    int getJopInfoList();

    List<JopInfoVo> getJopInfoNewTop8List();

    List<JopInfoVo> getCompanyTojobinfoList(String companyname);

    List<JopInfoVo> getCompanyInScrapList(String[] companynames);

    List<JopInfoVo> getInfoInScrapList(String[] infonames);

    List<JopInfoVo> getInfoInScrapSearchList(String SearchSelect, String SearchValue, String[] infonames);
}
