package com.solponge.domain.cart.impl;


import com.solponge.domain.cart.CartListVo;
import com.solponge.domain.cart.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class CartDAO {
    @Autowired
    private SqlSession sqlSession;

    /**
     * 기본 CRUD 구현
     */

    public CartVo cartFindByNum(int MEMBER_NUM){
        Map<String,Object> param=new HashMap<>();
        param.put("MEMBER_NUM",MEMBER_NUM);
        CartVo cart = sqlSession.selectOne("cart.selectCart",param);
      //  log.info("CartDAO.cartFindByNum.cart.member_num={}",cart.getMEMBER_NUM());
        return cart;
    }

    public List<CartVo> cartFindAll(){
        return sqlSession.selectList("cart.list");
    }

    /*UPDATE 미구현*/


    public String cartInsert(CartVo cartVo){
        sqlSession.insert("cart.insertCart",cartVo);
        return String.valueOf(cartVo.getMEMBER_NUM());
    }

    public void cartDelete(int CART_NUM){
        Map<String,Object> param=new HashMap<>();
        param.put("CART_NUM",CART_NUM);
        sqlSession.delete("cart.deleteCart",param);
    }


    /**
     * 서비스 CRUD 구현
     */

    /*해당 맴버의 장바구니 상품 확인*/
    public List<CartListVo> showMemberCart(int MEMBER_NUM){
        return sqlSession.selectList("cart.cartList", MEMBER_NUM);
    }

    public void cartDeleteByMember(int MEMBER_NUM){
        Map<String,Object> param=new HashMap<>();
        param.put("MEMBER_NUM",MEMBER_NUM);
        sqlSession.delete("cart.deleteCartByMember",param);
    }
}
