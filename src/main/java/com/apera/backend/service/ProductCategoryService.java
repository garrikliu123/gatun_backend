package com.apera.backend.service;

import com.apera.backend.dao.ProductCategoryDao;
import com.apera.backend.dto.ProductCategoryExecution;
import com.apera.backend.entity.ProductCategory;
import com.apera.backend.enums.ProductCategoryStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryService {
  @Autowired private ProductCategoryDao productCategoryDao;
  /**
   * 查询分类列表
   *
   * @return
   */
  public ProductCategoryExecution getMatchedProductCategoryList(List<String> categoryIdList) {

    boolean success = false;
    List<String> productCategoryList = null;

    try {
      productCategoryList = productCategoryDao.queryProductCategory(categoryIdList);
      success = true;
    } catch (Exception e) {
      throw new RuntimeException("Get category failed - 获取商品列表失败: " + e.toString());
    }

    if (success) {
      return new ProductCategoryExecution(
          ProductCategoryStateEnum.SUCCESS, productCategoryList, true);
    } else {
      return new ProductCategoryExecution(ProductCategoryStateEnum.ERROR);
    }
  }

  /**
   * 查询分类列表
   *
   * @return
   */
  public ProductCategoryExecution getProductCategoryList() {

    boolean success = false;
    List<ProductCategory> productCategoryList = null;

    try {
      productCategoryList = productCategoryDao.queryAllProductCategory();
      success = true;
    } catch (Exception e) {
      throw new RuntimeException("Get category failed - 获取商品列表失败: " + e.toString());
    }

    if (success) {
      return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS, productCategoryList);
    } else {
      return new ProductCategoryExecution(ProductCategoryStateEnum.ERROR);
    }
  }

  /**
   * 查询分类列表
   *
   * @return
   */
  public ProductCategoryExecution getProductCategoryListByProductId(String productId) {

    boolean success = false;
    List<ProductCategory> productCategoryList = null;

    try {
      productCategoryList = productCategoryDao.queryProductCategoryByProductId(productId);
      success = true;
    } catch (Exception e) {
      throw new RuntimeException("Get category failed - 获取商品列表失败: " + e.toString());
    }

    if (success) {
      return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS, productCategoryList);
    } else {
      return new ProductCategoryExecution(ProductCategoryStateEnum.ERROR);
    }
  }

  /**
   * 添加分类信息
   *
   * @param productCategoryList
   * @return
   */
  @Transactional
  public ProductCategoryExecution addProductCategory(List<ProductCategory> productCategoryList)
      throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = productCategoryDao.insertProductCategory(productCategoryList);
      if (effectedRows <= 0) {
        throw new RuntimeException("Add category failed - 添加商品分类配对失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Add category failed - 添加商品分类配对失败: " + e.toString());
    }

    if (success) {
      return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
    } else {
      return new ProductCategoryExecution(ProductCategoryStateEnum.ERROR);
    }
  }

  /**
   * 删除商品分类配对
   *
   * @param productId
   * @return
   */
  @Transactional
  public ProductCategoryExecution deleteProductCategoryByProductId(String productId)
      throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = productCategoryDao.deleteProductCategoryByProductId(productId);
      if (effectedRows < 0) {
        throw new RuntimeException("Delete category failed - 删除商品类别配对失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Delete category failed - 删除商品分类配对失败: " + e.toString());
    }

    if (success) {
      return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
    } else {
      return new ProductCategoryExecution(ProductCategoryStateEnum.ERROR);
    }
  }
}
