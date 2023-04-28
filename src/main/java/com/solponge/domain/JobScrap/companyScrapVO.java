package com.solponge.domain.JobScrap;


import lombok.Data;

import java.sql.Date;

@Data
public class companyScrapVO {
//    private Long MEMBER_NO;
    private Long member_No;
    private String companyName;
    private Date scrap_time;
}
