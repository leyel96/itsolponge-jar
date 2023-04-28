package com.solponge.domain.admin.impl;

import com.solponge.domain.admin.AdminOrderVo;
import com.solponge.domain.product.productVo;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class OrderDAO {
    @Autowired
    SqlSessionTemplate sqlSession;

    public List<AdminOrderVo> ordersearchlist(String SearchSelect, String SearchValue) {
        System.out.println("===> Spring JDBC로 produtsearchlist() 기능 처리");
        System.out.println(SearchSelect);
        System.out.println(SearchValue);
        Map<String, Object> param = new HashMap<>();
        switch (SearchSelect){
            case "payment_num":
                param.put("SearchValue", SearchValue);
                break;
            case "MEMBER_ID":
                param.put("SearchValue", SearchValue);
                break;
        }
        return sqlSession.selectList("order.Searchlist_"+SearchSelect, param);
    }

    public List<productVo> produtsearchlist(String SearchSelect, String SearchValue) {
        System.out.println("===> Spring JDBC로 produtsearchlist() 기능 처리");
        System.out.println(SearchSelect);
        System.out.println(SearchValue);
        Map<String, Object> param = new HashMap<>();
        switch (SearchSelect){
            case "all":
                param.put("SearchValue", SearchValue);
                break;
            case "product_title":
                param.put("SearchValue", SearchValue);
                break;
            case "product_writer":
                param.put("SearchValue", SearchValue);
                break;
        }
        return sqlSession.selectList("collection_of_sql_statements.Searchlist_"+SearchSelect, param);
    }

    //CRUD
    public List<productVo> getproductList() {
        System.out.println("===> Spring JDBC로 getproductList() 기능 처리");
        return sqlSession.selectList("collection_of_sql_statements.list");
    }
    public List<productVo> getproductNewTop8List() {
        System.out.println("===> Spring JDBC로 getproductNewTop8List() 기능 처리");
        return sqlSession.selectList("collection_of_sql_statements.list_new_8");
    }
    public List<productVo> getproductpopularTop8List() {
        System.out.println("===> Spring JDBC로 getproductpopularTop8List() 기능 처리");
        return sqlSession.selectList("collection_of_sql_statements.list_popular_6");
    }

    // 글 상세 조회
    public productVo getproduct(int product_num){
        System.out.println("===> Spring JDBC로 getproduct() 기능 처리");
        Map<String, Object> param = new HashMap<>();
        param.put("product_num", product_num);
        return sqlSession.selectOne("collection_of_sql_statements.product", param);
    }

    public String insertproduct(productVo vo) {
        Map<String, Object> param = new HashMap<>();
        param.put("TITLE", vo.getProduct_title());
        param.put("SUB", vo.getProduct_subtitle());
        param.put("WRITER", vo.getProduct_writer());
        param.put("PRICE", vo.getProduct_price());
        param.put("IMG", vo.getProduct_img());
        param.put("DATE", vo.getProduct_date());
        param.put("PAGE", vo.getProduct_page());
        param.put("STOCK", vo.getProduct_stock());
        param.put("CODE", vo.getProduct_code());
        System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
        sqlSession.insert("collection_of_sql_statements.insertpro", param);
        return param.get("PRODUCT_NUM_num").toString();
    }

    // 글 수정
    public void updateOrder(String paymentNum, AdminOrderVo vo) {
        Map<String, Object> param = new HashMap<>();
        param.put("payment_num",paymentNum);
        param.put("delivery_num", vo.getDelivery_num());
        log.info("vo.getDelivery_num()={}",vo.getDelivery_num());
        sqlSession.update("order.updateOrder", param);
    }

    // 글 삭제
    public void deleteBoard(int product_num) {
        Map<String, Object> param = new HashMap<>();
        param.put("NUM", product_num);
        System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리");
        sqlSession.delete("collection_of_sql_statements.deletepro", param);
    }

    public AdminOrderVo getMyOrder(String paymentNum){
        Map<String,Object> param=new HashMap<>();
        param.put("PAYMENT_NUM",paymentNum);
        return sqlSession.selectOne("order.getMyOrder",param);

    }



    // 글 목록 조회
    public List<AdminOrderVo> getBoardList() {
        System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
        return sqlSession.selectList("order.list");
    }

    public List<AdminOrderVo> getMemberOrders(String paymentNum) {
        System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
        Map<String,Object> param=new HashMap<>();
        param.put("PAYMENT_NUM",paymentNum);
        return sqlSession.selectList("order.orderList",param);
    }
}
