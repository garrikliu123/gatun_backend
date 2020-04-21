package com.apera.backend.dto;

import com.apera.backend.entity.Order;
import com.apera.backend.enums.OrderStateEnum;

import java.util.List;

public class OrderExecution {
  // 结果状态
  private int state;

  // 状态标识
  private String stateInfo;

  // 操作的product（增删改商品的时候用）
  private Order order;

  // 获取的product列表(查询商品列表的时候用)
  private List<Order> orderList;

  public OrderExecution() {}

  // 失败的构造器
  public OrderExecution(OrderStateEnum stateEnum) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
  }

  // 成功的构造器
  public OrderExecution(OrderStateEnum stateEnum, Order order) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.order = order;
  }

  // 成功的构造器
  public OrderExecution(OrderStateEnum stateEnum, List<Order> orderList) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.orderList = orderList;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getStateInfo() {
    return stateInfo;
  }

  public void setStateInfo(String stateInfo) {
    this.stateInfo = stateInfo;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public List<Order> getOrderList() {
    return orderList;
  }

  public void setOrderList(List<Order> orderList) {
    this.orderList = orderList;
  }
}
