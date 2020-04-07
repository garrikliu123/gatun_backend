package com.apera.backend.service;

import com.apera.backend.dao.CategoryDao;
import com.apera.backend.dto.CategoryExecution;
import com.apera.backend.entity.Category;
import com.apera.backend.enums.CategoryStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
  @Autowired private CategoryDao categoryDao;

  /**
   * 查询分类列表
   *
   * @return
   */
  public CategoryExecution getCategoryList() {
    boolean success = false;
    List<Category> categoryList = null;

    try {
      categoryList = categoryDao.queryCategoryList();
      success = true;
    } catch (Exception e) {
      throw new RuntimeException("Get category failed - 获取分类列表失败: " + e.toString());
    }

    if (success) {
      return new CategoryExecution(CategoryStateEnum.SUCCESS, categoryList);
    } else {
      return new CategoryExecution(CategoryStateEnum.ERROR);
    }
  }

  /**
   * 添加分类信息
   *
   * @param category
   * @return
   */
  @Transactional
  public CategoryExecution addCategory(Category category) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = categoryDao.insertCategory(category);
      if (effectedRows <= 0) {
        throw new RuntimeException("Add category failed - 添加分类失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Add category failed - 添加分类失败: " + e.toString());
    }

    if (success) {
      return new CategoryExecution(CategoryStateEnum.SUCCESS, category);
    } else {
      return new CategoryExecution(CategoryStateEnum.ERROR);
    }
  }

  /**
   * 更新分类信息
   *
   * @param category
   * @return
   */
  @Transactional
  public CategoryExecution updateCategory(Category category) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = categoryDao.updateCategory(category);
      if (effectedRows <= 0) {
        throw new RuntimeException("Update category failed - 更新分类失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Update category failed - 更新分类失败: " + e.toString());
    }

    if (success) {
      return new CategoryExecution(CategoryStateEnum.SUCCESS, category);
    } else {
      return new CategoryExecution(CategoryStateEnum.ERROR);
    }
  }

  /**
   * 删除分类信息
   *
   * @param categoryId
   * @return
   */
  @Transactional
  public CategoryExecution deleteCategory(String categoryId) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = categoryDao.deleteCategory(categoryId);
      if (effectedRows <= 0) {
        throw new RuntimeException("Delete category failed - 删除分类失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Delete category failed - 删除分类失败: " + e.toString());
    }

    if (success) {
      return new CategoryExecution(CategoryStateEnum.SUCCESS);
    } else {
      return new CategoryExecution(CategoryStateEnum.ERROR);
    }
  }
}
