package com.apera.backend.dto;

import com.apera.backend.entity.Topic;
import com.apera.backend.enums.TopicStateEnum;

import java.util.List;

public class TopicExecution {
  // 结果状态
  private int state;
  // 状态标识
  private String stateInfo;
  // 操作的 category（增删改分类的时候用）
  private Topic topic;

  // 获取的 category 列表(查询商品列表的时候用)
  private List<Topic> topicList;

  public TopicExecution() {}

  // 失败的构造器
  public TopicExecution(TopicStateEnum stateEnum) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
  }

  // 成功的构造器
  public TopicExecution(TopicStateEnum stateEnum, Topic topic) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.topic = topic;
  }

  // 成功的构造器
  public TopicExecution(TopicStateEnum stateEnum, List<Topic> topicList) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.topicList = topicList;
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

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  public List<Topic> getTopicList() {
    return topicList;
  }

  public void setTopicList(List<Topic> topicList) {
    this.topicList = topicList;
  }
}
