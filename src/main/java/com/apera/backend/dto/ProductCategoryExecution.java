package com.apera.backend.dto;

import com.apera.backend.entity.ProductCategory;
import com.apera.backend.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
  // 结果状态
  private int state;
  // 状态标识
  private String stateInfo;
  private List<String> productCategoryStringList;
  private List<ProductCategory> productCategoryList;

  public ProductCategoryExecution() {}

  // 失败的构造器
  public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
  }

  // 成功的构造器
  public ProductCategoryExecution(
      ProductCategoryStateEnum stateEnum,
      List<String> productCategoryStringList,
      Boolean isString) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.productCategoryStringList = productCategoryStringList;
  }
  // 成功的构造器
  public ProductCategoryExecution(
      ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.productCategoryList = productCategoryList;
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

  public List<String> getProductCategoryStringList() {
    return productCategoryStringList;
  }

  public void setProductCategoryStringList(List<String> productCategoryStringList) {
    this.productCategoryStringList = productCategoryStringList;
  }

  public List<ProductCategory> getProductCategoryList() {
    return productCategoryList;
  }

  public void setProductCategoryList(List<ProductCategory> productCategoryList) {
    this.productCategoryList = productCategoryList;
  }
}
