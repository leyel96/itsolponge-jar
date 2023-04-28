package com.solponge.domain.JobScrap;


import lombok.Data;

import java.sql.Date;

@Data
public class InfScrapVO {
//    private Long MEMBER_NO;
    private Long member_No;
    private String infoname;
    private Date scrap_time;
}
