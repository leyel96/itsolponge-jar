package com.solponge.domain.JobScrap;

import java.util.List;

public interface JobScrapService {
    // CRUD
    void insertJobScrap_company(companyScrapVO vo);

    void insertJobScrap_infoname(InfScrapVO vo);

    void deleteJobScrap_company(Long MEMBER_NO, String companyName);

    void deleteJobScrap_infoname(Long MEMBER_NO, String infoname);

    List<companyScrapVO> getcompanyScrapVOScrapList(Long MEMBER_NO);

    List<InfScrapVO> getinfoScrapVOScrapList(Long MEMBER_NO);
}
