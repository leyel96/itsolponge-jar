package com.solponge.domain.product.impl;

import com.solponge.domain.product.productVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class productDAO {

    @Autowired
    SqlSessionTemplate sqlSession;

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
    public void updateproduct(productVo vo) {
        Map<String, Object> param = new HashMap<>();
        param.put("NUM", vo.getProduct_num());
        param.put("TITLE", vo.getProduct_title());
        param.put("SUB", vo.getProduct_subtitle());
        param.put("WRITER", vo.getProduct_writer());
        param.put("PRICE", vo.getProduct_price());
        param.put("IMG", vo.getProduct_img());
        param.put("DATE", vo.getProduct_date());
        param.put("PAGE", vo.getProduct_page());
        param.put("CODE", vo.getProduct_code());
        param.put("STOCK", vo.getProduct_stock());
        param.put("SALE", vo.getProduct_sale());
        param.put("VISIT", vo.getProduct_visit());
        System.out.println(vo.getProduct_code());
        System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
        System.out.println(vo.getProduct_code());
        sqlSession.update("collection_of_sql_statements.updatepro", param);
        sqlSession.update("collection_of_sql_statements.updateProductCode", param);
    }

    // 글 삭제
    public void deleteBoard(int product_num) {
        Map<String, Object> param = new HashMap<>();
        param.put("NUM", product_num);
        System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리");
        sqlSession.update("collection_of_sql_statements.deletepro", param);
    }



    // 글 목록 조회
    public List<productVo> productFindAll() {
        System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
        return sqlSession.selectList("collection_of_sql_statements.list");
    }

}
