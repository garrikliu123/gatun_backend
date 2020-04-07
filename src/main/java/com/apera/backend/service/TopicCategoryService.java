package com.apera.backend.service;

import com.apera.backend.dao.TopicCategoryDao;
import com.apera.backend.dto.TopicCategoryExecution;
import com.apera.backend.entity.TopicCategory;
import com.apera.backend.enums.TopicCategoryStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicCategoryService {
  @Autowired private TopicCategoryDao topicCategoryDao;

  /**
   * 查询分类列表
   *
   * @return
   */
  public TopicCategoryExecution getTopicCategoryList() {

    boolean success = false;
    List<TopicCategory> topicCategoryList = null;

    try {
      topicCategoryList = topicCategoryDao.queryAllTopicCategory();
      success = true;
    } catch (Exception e) {
      throw new RuntimeException("Get category failed - 获取商品列表失败: " + e.toString());
    }

    if (success) {
      return new TopicCategoryExecution(TopicCategoryStateEnum.SUCCESS, topicCategoryList);
    } else {
      return new TopicCategoryExecution(TopicCategoryStateEnum.ERROR);
    }
  }

  /**
   * 添加分类信息
   *
   * @param topicCategoryList
   * @return
   */
  @Transactional
  public TopicCategoryExecution addTopicCategory(List<TopicCategory> topicCategoryList)
      throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = topicCategoryDao.insertTopicCategory(topicCategoryList);
      if (effectedRows <= 0) {
        throw new RuntimeException("Add topic category failed - 添加主题分类配对失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Add topic category failed - 添加主题分类配对失败: " + e.toString());
    }

    if (success) {
      return new TopicCategoryExecution(TopicCategoryStateEnum.SUCCESS);
    } else {
      return new TopicCategoryExecution(TopicCategoryStateEnum.ERROR);
    }
  }

  /**
   * 删除商品分类配对
   *
   * @param topicId
   * @return
   */
  @Transactional
  public TopicCategoryExecution deleteTopicCategoryByTopicId(String topicId)
      throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = topicCategoryDao.deleteTopicCategoryByTopicId(topicId);
      if (effectedRows < 0) {
        throw new RuntimeException("Delete topic category failed - 删除主题类别配对失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Delete topic category failed - 删除主题分类配对失败: " + e.toString());
    }

    if (success) {
      return new TopicCategoryExecution(TopicCategoryStateEnum.SUCCESS);
    } else {
      return new TopicCategoryExecution(TopicCategoryStateEnum.ERROR);
    }
  }
}
