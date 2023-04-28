package com.solponge.domain.JobScrap.impl;

import com.solponge.domain.JobScrap.InfScrapVO;
import com.solponge.domain.JobScrap.JobScrapService;
import com.solponge.domain.JobScrap.companyScrapVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("JobScrapService")
public class JobScrapServiceImpl implements JobScrapService {
    @Autowired
    private com.solponge.domain.JobScrap.impl.JobScrapDAO JobScrapDAO;

    @Override
    public void insertJobScrap_company(companyScrapVO vo) {
        JobScrapDAO.insertJobScrap_company(vo);
    }

    @Override
    public void insertJobScrap_infoname(InfScrapVO vo) {
        JobScrapDAO.insertJobScrap_infoname(vo);
    }

    @Override
    public void deleteJobScrap_company(Long MEMBER_NO, String companyName) {
        JobScrapDAO.deleteJobScrap_company(MEMBER_NO, companyName);
    }

    @Override
    public void deleteJobScrap_infoname(Long MEMBER_NO, String infoname) {
        JobScrapDAO.deleteJobScrap_infoname(MEMBER_NO, infoname);
    }

    @Override
    public List<companyScrapVO> getcompanyScrapVOScrapList(Long MEMBER_NO) {
        return JobScrapDAO.getcompanyScrapVOScrapList(MEMBER_NO);
    }

    @Override
    public List<InfScrapVO> getinfoScrapVOScrapList(Long MEMBER_NO) {
        return JobScrapDAO.getinfoScrapVOScrapList(MEMBER_NO);
    }


}
