package com.apera.backend.dao;

import com.apera.backend.entity.Category;

import java.util.List;

public interface CategoryDao {
  /**
   * 插入分类
   *
   * @param category
   * @return
   */
  int insertCategory(Category category);

  /**
   * 更新分类信息
   *
   * @param category
   * @return
   */
  int updateCategory(Category category);

  /**
   * 删除分类
   *
   * @param categoryId
   * @return
   */
  int deleteCategory(String categoryId);

  /**
   * 查询分类列表
   *
   * @return
   */
  List<Category> queryCategoryList();
}
