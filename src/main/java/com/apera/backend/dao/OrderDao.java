package com.apera.backend.dao;

import com.apera.backend.entity.Order;

import java.util.List;

public interface OrderDao {

  /**
   * 查询商品列表
   *
   * @return
   */
  List<Order> queryOrderListByUserId(String userId);

  /**
   * 插入商品
   *
   * @param order
   * @return
   */
  int insertOrder(Order order);

  /**
   * 更新商品信息
   *
   * @param order
   * @return
   */
  int updateOrder(Order order);

  /**
   * 删除商品
   *
   * @param orderId
   * @return
   */
  int deleteOrder(String orderId);
}
