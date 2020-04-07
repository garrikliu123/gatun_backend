package com.apera.backend.dto;

import com.apera.backend.entity.User;
import com.apera.backend.enums.UserStateEnum;

import java.util.List;

public class UserExecution {
  // 结果状态
  private int state;
  // 状态标识
  private String stateInfo;
  // 操作的 category（增删改分类的时候用）
  private User user;

  // 获取的 category 列表(查询商品列表的时候用)
  private List<User> userList;

  public UserExecution() {}

  // 失败的构造器
  public UserExecution(UserStateEnum stateEnum) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
  }

  // 成功的构造器
  public UserExecution(UserStateEnum stateEnum, User user) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.user = user;
  }

  // 成功的构造器
  public UserExecution(UserStateEnum stateEnum, List<User> userList) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.userList = userList;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<User> getUserList() {
    return userList;
  }

  public void setUserList(List<User> userList) {
    this.userList = userList;
  }
}
