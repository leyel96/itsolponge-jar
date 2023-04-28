package com.solponge.domain.product;



import java.util.List;

public interface productService {

    // CRUD
    String insertproduct(productVo vo);

    void updateproduct(productVo vo);

    void deleteproduct(int product_num);

    List<productVo> findAll();

    // CRUD
    productVo getproduct(int product_num);
    List<productVo> produtsearchlist(String SearchSelect, String SearchValue);
    List<productVo> getproductList();
    List<productVo> getproductNewTop8List();
    List<productVo> getproductpopularTop8List();
}
