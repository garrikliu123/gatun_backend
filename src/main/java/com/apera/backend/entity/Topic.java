package com.apera.backend.entity;

public class Topic {
  private String topicId;
  private String topicName;
  private String topicType;
  private String topicImage;

  public String getTopicId() {
    return topicId;
  }

  public void setTopicId(String topicId) {
    this.topicId = topicId;
  }

  public String getTopicName() {
    return topicName;
  }

  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }

  public String getTopicType() {
    return topicType;
  }

  public void setTopicType(String topicType) {
    this.topicType = topicType;
  }

  public String getTopicImage() {
    return topicImage;
  }

  public void setTopicImage(String topicImage) {
    this.topicImage = topicImage;
  }
}
