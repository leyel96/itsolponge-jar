package com.solponge.domain.jobinfo.impl;

import com.solponge.domain.jobinfo.JopInfoService;
import com.solponge.domain.jobinfo.JopInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("JopInfoService")
public class jobInfoServiceImpl implements JopInfoService {

    @Autowired
    private jobInfoDAO jobinfoDAO;

    @Override
    public void insertJopInfo(JopInfoVo vo){    }

    @Override
    public void updateJopInfo(JopInfoVo vo) {

    }

    @Override
    public void deleteJopInfo(int infonum) {

    }

    @Override
    public JopInfoVo getJopInfo(int infonum) {
        return jobinfoDAO.getJopInfo(infonum);
    }

    @Override
    public List<JopInfoVo> JopInfosearchlist_page(String SearchSelect, String SearchValue, int start, int end) {
        return jobinfoDAO.JopInfosearchlist_page(SearchSelect, SearchValue, start, end);
    }
    @Override
    public int JopInfosearchlist_count(String SearchSelect, String SearchValue) {
        return jobinfoDAO.JopInfosearchlist_count(SearchSelect, SearchValue);
    }


    @Override
    public List<JopInfoVo> getJopInfoListpage(int start, int end ) {
        return jobinfoDAO.getJopInfoListpage(start, end);
    }

    @Override
    public int getJopInfoList() {
        return jobinfoDAO.getJopInfoList_count();
    }

    @Override
    public List<JopInfoVo> getJopInfoNewTop8List() {
        return jobinfoDAO.getJopInfoNewTop8List();
    }

    @Override
    public List<JopInfoVo> getCompanyTojobinfoList(String companyname) {
        return jobinfoDAO.getCompanyTojobinfoList(companyname);
    }

    @Override
    public List<JopInfoVo> getCompanyInScrapList(String[] companynames) {
        return jobinfoDAO.getCompanyInScrapList(companynames);
    }

    @Override
    public List<JopInfoVo> getInfoInScrapList(String[] infonames) {
        return jobinfoDAO.getInfoInScrapList(infonames);
    }

    @Override
    public List<JopInfoVo> getInfoInScrapSearchList(String SearchSelect, String SearchValue, String[] infonames) {
        return jobinfoDAO.getInfoInScrapSearchList(SearchSelect, SearchValue, infonames);
    }
}
