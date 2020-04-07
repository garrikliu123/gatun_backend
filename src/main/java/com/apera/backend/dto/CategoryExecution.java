package com.apera.backend.dto;

import com.apera.backend.entity.Category;
import com.apera.backend.enums.CategoryStateEnum;

import java.util.List;

public class CategoryExecution {
  // 结果状态
  private int state;
  // 状态标识
  private String stateInfo;
  // 操作的 category（增删改分类的时候用）
  private Category category;

  // 获取的 category 列表(查询商品列表的时候用)
  private List<Category> categoryList;

  public CategoryExecution() {}

  // 失败的构造器
  public CategoryExecution(CategoryStateEnum stateEnum) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
  }

  // 成功的构造器
  public CategoryExecution(CategoryStateEnum stateEnum, Category category) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.category = category;
  }

  // 成功的构造器
  public CategoryExecution(CategoryStateEnum stateEnum, List<Category> categoryList) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.categoryList = categoryList;
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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public List<Category> getCategoryList() {
    return categoryList;
  }

  public void setCategoryList(List<Category> categoryList) {
    this.categoryList = categoryList;
  }
}
