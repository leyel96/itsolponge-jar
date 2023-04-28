package com.solponge.domain.admin;

import lombok.Data;

@Data
public class Delivery {
    String deliveryInfo;
    Long deliveryNum;

    public Delivery() {
    }

    public Delivery(String deliveryInfo, Long deliveryNum) {
        this.deliveryInfo = deliveryInfo;
        this.deliveryNum = deliveryNum;
    }
}
