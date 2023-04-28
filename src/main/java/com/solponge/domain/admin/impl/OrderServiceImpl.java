package com.solponge.domain.admin.impl;


import com.solponge.domain.admin.AdminOrderVo;
import com.solponge.domain.admin.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    @Autowired
    private OrderDAO OrderDAO;



    @Override
    public String insertOrder(AdminOrderVo vo) {
        return null;
    }

    @Override
    public void updateOrder(String paymentNum,AdminOrderVo vo) {
        OrderDAO.updateOrder(paymentNum,vo);
    }

    @Override
    public void deleteOrder(String payment_num) {

    }

    @Override
    public AdminOrderVo getBoard(String paymentNum) {
        return OrderDAO.getMyOrder(paymentNum);
    }

    public List<AdminOrderVo> getBoardList() {
        System.out.println("OrderServiceImpl...");
        return OrderDAO.getBoardList();
    }

    @Override
    public List<AdminOrderVo> ordersearchlist(String SearchSelect, String SearchValue) {
        return OrderDAO.ordersearchlist(SearchSelect, SearchValue);
    }

    public List<AdminOrderVo> getMemberOrders(String paymentNum) {
        System.out.println("OrderServiceImpl...");
        return OrderDAO.getBoardList();
    }



}
