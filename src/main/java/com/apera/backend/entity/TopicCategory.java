package com.apera.backend.entity;

public class TopicCategory {
  private String topicId;
  private String categoryId;

  public TopicCategory(String topicId, String categoryId) {
    this.topicId = topicId;
    this.categoryId = categoryId;
  }

  public String getTopicId() {
    return topicId;
  }

  public void setTopicId(String topicId) {
    this.topicId = topicId;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }
}
