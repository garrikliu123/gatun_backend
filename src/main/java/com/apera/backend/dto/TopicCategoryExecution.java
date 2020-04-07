package com.apera.backend.dto;

import com.apera.backend.entity.TopicCategory;
import com.apera.backend.enums.TopicCategoryStateEnum;

import java.util.List;

public class TopicCategoryExecution {
  // 结果状态
  private int state;
  // 状态标识
  private String stateInfo;
  private List<String> topicCategoryStringList;
  private List<TopicCategory> topicCategoryList;

  public TopicCategoryExecution() {}

  // 失败的构造器
  public TopicCategoryExecution(TopicCategoryStateEnum stateEnum) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
  }

  // 成功的构造器
  public TopicCategoryExecution(
      TopicCategoryStateEnum stateEnum, List<String> topicCategoryStringList, Boolean isString) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.topicCategoryStringList = topicCategoryStringList;
  }
  // 成功的构造器
  public TopicCategoryExecution(
      TopicCategoryStateEnum stateEnum, List<TopicCategory> topicCategoryList) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.topicCategoryList = topicCategoryList;
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

  public List<String> getTopicCategoryStringList() {
    return topicCategoryStringList;
  }

  public void setTopicCategoryStringList(List<String> topicCategoryStringList) {
    this.topicCategoryStringList = topicCategoryStringList;
  }

  public List<TopicCategory> getTopicCategoryList() {
    return topicCategoryList;
  }

  public void setTopicCategoryList(List<TopicCategory> topicCategoryList) {
    this.topicCategoryList = topicCategoryList;
  }
}
