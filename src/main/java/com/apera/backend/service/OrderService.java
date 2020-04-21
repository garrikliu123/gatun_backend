package com.apera.backend.service;

import com.apera.backend.dao.OrderDao;
import com.apera.backend.dto.OrderExecution;
import com.apera.backend.entity.Order;
import com.apera.backend.enums.OrderStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
  @Autowired private OrderDao orderDao;

  /**
   * 查询商品列表
   *
   * @return
   */
  public OrderExecution getOrderListByUserId(String userId) {
    boolean success = false;
    List<Order> orderList = null;

    try {
      orderList = orderDao.queryOrderListByUserId(userId);
      success = true;
    } catch (Exception e) {
      throw new RuntimeException("Get category failed - 获取订单列表失败: " + e.toString());
    }

    if (success) {
      return new OrderExecution(OrderStateEnum.SUCCESS, orderList);
    } else {
      return new OrderExecution(OrderStateEnum.ERROR);
    }
  }

  /**
   * 添加商品信息
   *
   * @param order
   * @return
   */
  @Transactional
  public OrderExecution addOrder(Order order) throws RuntimeException {
    order.setCreateTime(new Date());

    try {
      // 创建商品信息
      int effectedRows = orderDao.insertOrder(order);
      if (effectedRows <= 0) {
        throw new RuntimeException("Add product failed - 添加订单失败");
      }
    } catch (Exception e) {
      throw new RuntimeException("Add product failed - 添加订单失败: " + e.toString());
    }

    return new OrderExecution(OrderStateEnum.SUCCESS, order);
  }

  /**
   * 删除商品信息
   *
   * @param orderId
   * @return
   */
  @Transactional
  public OrderExecution deleteOrder(String orderId) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = orderDao.deleteOrder(orderId);
      if (effectedRows <= 0) {
        throw new RuntimeException("Delete category failed - 删除订单失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Delete category failed - 删除订单失败: " + e.toString());
    }

    if (success) {
      return new OrderExecution(OrderStateEnum.SUCCESS);
    } else {
      return new OrderExecution(OrderStateEnum.ERROR);
    }
  }
}
