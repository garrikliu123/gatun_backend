package com.apera.backend.dao;

import com.apera.backend.entity.Topic;

import java.util.List;

public interface TopicDao {
  /**
   * 插入文章
   *
   * @param topic
   * @return
   */
  int insertTopic(Topic topic);

  /**
   * 更新文章
   *
   * @param topic
   * @return
   */
  int updateTopic(Topic topic);

  /**
   * 删除文章
   *
   * @param topicId
   * @return
   */
  int deleteTopic(String topicId);

  /**
   * 查询文章
   *
   * @return
   */
  List<Topic> queryTopicList();
}
