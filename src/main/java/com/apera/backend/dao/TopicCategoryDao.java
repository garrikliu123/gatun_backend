package com.apera.backend.dao;

import com.apera.backend.entity.TopicCategory;

import java.util.List;

public interface TopicCategoryDao {
  /**
   * 插入商品分类配对
   *
   * @param topicCategoryList
   * @return
   */
  int insertTopicCategory(List<TopicCategory> topicCategoryList);

  /**
   * 删除商品分类配对，根据商品 ID
   *
   * @param topicId
   * @return
   */
  int deleteTopicCategoryByTopicId(String topicId);

  /**
   * 查询所有商品分类配对
   *
   * @return
   */
  List<TopicCategory> queryAllTopicCategory();
}
