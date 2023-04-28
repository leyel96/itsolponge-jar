package com.solponge.domain.admin;



import java.util.List;

public interface OrderService {

    // CRUD
    String insertOrder(AdminOrderVo vo);

    void updateOrder(String paymentNum,AdminOrderVo vo);

    void deleteOrder(String payment_num);

    AdminOrderVo getBoard(String paymentNum);

    List<AdminOrderVo> getBoardList();

    List<AdminOrderVo> ordersearchlist(String SearchSelect, String SearchValue);
}