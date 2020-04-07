package com.apera.backend.dao;

import com.apera.backend.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
  /**
   * 插入商品分类配对
   *
   * @param productCategoryList
   * @return
   */
  int insertProductCategory(List<ProductCategory> productCategoryList);

  /**
   * 删除商品分类配对，根据商品 ID
   *
   * @param productId
   * @return
   */
  int deleteProductCategoryByProductId(String productId);

  /**
   * 查询匹配的商品分类配对
   *
   * @return
   */
  List<String> queryProductCategory(List<String> categoryIdList);

  /**
   * 查询匹配的商品分类配对
   *
   * @return
   */
  List<ProductCategory> queryProductCategoryByProductId(String productId);

  /**
   * 查询所有商品分类配对
   *
   * @return
   */
  List<ProductCategory> queryAllProductCategory();
}
