package com.apera.backend.service;

import com.apera.backend.dao.TopicDao;
import com.apera.backend.dto.TopicExecution;
import com.apera.backend.entity.Topic;
import com.apera.backend.enums.TopicStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicService {
  @Autowired private TopicDao topicDao;

  /**
   * 查询分类列表
   *
   * @return
   */
  public TopicExecution getTopicList() {
    boolean success = false;
    List<Topic> list = null;

    try {
      list = topicDao.queryTopicList();
      success = true;
    } catch (Exception e) {
      throw new RuntimeException("Get category failed - 获取主题列表失败: " + e.toString());
    }

    if (success) {
      return new TopicExecution(TopicStateEnum.SUCCESS, list);
    } else {
      return new TopicExecution(TopicStateEnum.ERROR);
    }
  }

  /**
   * 添加分类信息
   *
   * @param topic
   * @return
   */
  @Transactional
  public TopicExecution addTopic(Topic topic) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = topicDao.insertTopic(topic);
      if (effectedRows <= 0) {
        throw new RuntimeException("Add category failed - 添加主题失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Add category failed - 添加主题失败: " + e.toString());
    }

    if (success) {
      return new TopicExecution(TopicStateEnum.SUCCESS, topic);
    } else {
      return new TopicExecution(TopicStateEnum.ERROR);
    }
  }

  /**
   * 更新分类信息
   *
   * @param topic
   * @return
   */
  @Transactional
  public TopicExecution updateTopic(Topic topic) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = topicDao.updateTopic(topic);
      if (effectedRows <= 0) {
        throw new RuntimeException("Update category failed - 更新主题失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Update category failed - 更新主题失败: " + e.toString());
    }

    if (success) {
      return new TopicExecution(TopicStateEnum.SUCCESS, topic);
    } else {
      return new TopicExecution(TopicStateEnum.ERROR);
    }
  }

  /**
   * 删除分类信息
   *
   * @param topicId
   * @return
   */
  @Transactional
  public TopicExecution deleteTopic(String topicId) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = topicDao.deleteTopic(topicId);
      if (effectedRows <= 0) {
        throw new RuntimeException("Delete category failed - 删除主题失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Delete category failed - 删除主题失败: " + e.toString());
    }

    if (success) {
      return new TopicExecution(TopicStateEnum.SUCCESS);
    } else {
      return new TopicExecution(TopicStateEnum.ERROR);
    }
  }
}
