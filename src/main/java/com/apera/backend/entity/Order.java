package com.apera.backend.entity;

import java.util.Date;

public class Order {
  private String userId;
  private String orderId;
  private String orderDetail;
  private String orderShipping;
  private String orderStatus;
  private Date createTime;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getOrderDetail() {
    return orderDetail;
  }

  public void setOrderDetail(String orderDetail) {
    this.orderDetail = orderDetail;
  }

  public String getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getOrderShipping() {
    return orderShipping;
  }

  public void setOrderShipping(String orderShipping) {
    this.orderShipping = orderShipping;
  }
}
