package com.apera.backend.dto;

import com.apera.backend.entity.HomeSection;
import com.apera.backend.enums.HomeSectionStateEnum;

import java.util.List;

public class HomeSectionExecution {
  // 结果状态
  private int state;
  // 状态标识
  private String stateInfo;
  // 操作的 category（增删改分类的时候用）
  private HomeSection homeSection;

  // 获取的 category 列表(查询商品列表的时候用)
  private List<HomeSection> homeSectionList;

  public HomeSectionExecution() {}

  // 失败的构造器
  public HomeSectionExecution(HomeSectionStateEnum stateEnum) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
  }

  // 成功的构造器
  public HomeSectionExecution(HomeSectionStateEnum stateEnum, HomeSection homeSection) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.homeSection = homeSection;
  }

  // 成功的构造器
  public HomeSectionExecution(HomeSectionStateEnum stateEnum, List<HomeSection> homeSectionList) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.homeSectionList = homeSectionList;
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

  public HomeSection getHomeSection() {
    return homeSection;
  }

  public void setHomeSection(HomeSection homeSection) {
    this.homeSection = homeSection;
  }

  public List<HomeSection> getHomeSectionList() {
    return homeSectionList;
  }

  public void setHomeSectionList(List<HomeSection> homeSectionList) {
    this.homeSectionList = homeSectionList;
  }
}
