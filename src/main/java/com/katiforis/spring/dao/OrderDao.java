package com.katiforis.spring.dao;

import com.katiforis.spring.model.Order;

import java.util.List;


public interface OrderDao {
    Order findById(int id);
    void save(Order order);
    void update(Order order);
    void saveOrUpdate(Order order);
    List<Order> findUserOrdersByTherapist(Integer userId, Integer therapistId);
    List<Order> findUserOrdersLiveOrders(Integer userId);
    List<Order> findUserOrdersCompletedOrders(Integer userId);
}