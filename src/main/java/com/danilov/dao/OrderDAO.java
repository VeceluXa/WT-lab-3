package com.danilov.dao;

import com.danilov.entity.orders.Orders;
import com.danilov.entity.rooms.Rooms;

import java.util.List;

public interface OrderDAO {

    void addOrder(Orders orders, long userId, long roomId);
    void cancelOrders(Long id);
    void cancelOrdersFromBannedUser(Long id);
    List<Orders> findAllOrdersOfUser(Long id);

    void updateOrderStatus(Long id, String status);
    List<Orders> findAllOrders();

}
