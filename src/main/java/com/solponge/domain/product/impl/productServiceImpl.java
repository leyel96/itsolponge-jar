package com.solponge.domain.product.impl;

import com.solponge.domain.product.productService;
import com.solponge.domain.product.productVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class productServiceImpl implements productService {
    @Autowired
    private com.solponge.domain.product.impl.productDAO productDAO;
    @Override
    public String insertproduct(productVo vo) {
        return productDAO.insertproduct(vo);
    }

    @Override
    public void updateproduct(productVo vo) {
        productDAO.updateproduct(vo);
    }

    @Override
    public void deleteproduct(int product_num) {
        productDAO.deleteBoard(product_num);
    }

    @Override
    public productVo getproduct(int product_num) {
        return productDAO.getproduct(product_num);
    }

    @Override
    public List<productVo> produtsearchlist(String SearchSelect, String SearchValue) {
        return productDAO.produtsearchlist(SearchSelect, SearchValue);
    }

    @Override
    public List<productVo> getproductList() {
        return productDAO.getproductList();
    }

    @Override
    public List<productVo> getproductNewTop8List() {
        return productDAO.getproductNewTop8List();
    }

    @Override
    public List<productVo> getproductpopularTop8List() {
        return productDAO.getproductpopularTop8List();
    }

    public List<productVo> findAll() {
        System.out.println("ProductServiceImpl...");
        return productDAO.productFindAll();
    }
}
