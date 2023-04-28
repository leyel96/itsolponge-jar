package com.solponge.domain.product;

import lombok.Data;

import java.sql.Date;

@Data
public class productVo {
    private int product_num;
    private String product_title;
    private String product_subtitle;
    private String product_writer;
    private int product_price;
    private String product_img;
    private Date product_date;
    private String product_page;
    private String product_code;
    private int product_stock;
    private int product_sale;
    private int product_visit;

    private int PRODUCT_GARBAGE;

    //기본 생성자
    public productVo(){}

    //생성자 오버로딩
    public productVo(int PRODUCT_GARBAGE, int product_num, String product_title, String product_subtitle, String product_writer, int product_price, String product_img, Date product_date, int product_stock, int product_sale, int product_visit, String product_page, String product_code) {
        this.product_num = product_num;
        this.product_title = product_title;
        this.product_subtitle = product_subtitle;
        this.product_writer = product_writer;
        this.product_price = product_price;
        this.product_img = product_img;
        this.product_date = product_date;
        this.product_stock = product_stock;
        this.product_sale = product_sale;
        this.product_visit = product_visit;
        this.product_page = product_page;
        this.product_code = product_code;
        this.PRODUCT_GARBAGE = PRODUCT_GARBAGE;
    }

    public productVo(int product_num, String product_title, int product_price, int product_stock) {
        this.product_num = product_num;
        this.product_title = product_title;
        this.product_price = product_price;
        this.product_stock = product_stock;
    }

    /**
     * db에서 받아온 정보를 productVo 객체로 만드는 생성자
     * @param product_num
     * @param product_title
     * @param product_price
     */
    public productVo(int product_num, String product_title, int product_price) {
        this.product_num = product_num;
        this.product_title = product_title;
        this.product_price = product_price;
    }


}
