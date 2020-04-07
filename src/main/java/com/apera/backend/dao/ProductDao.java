package com.apera.backend.dao;

import com.apera.backend.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

  /**
   * 查询商品列表
   *
   * @return
   */
  List<Product> queryProductList();

  /**
   * 通过 productId 查询唯一的商品信息
   *
   * @param productId
   * @return
   */
  Product queryProductById(@Param("productId") long productId);

  /**
   * 插入商品
   *
   * @param product
   * @return
   */
  int insertProduct(Product product);

  /**
   * 更新商品信息
   *
   * @param product
   * @return
   */
  int updateProduct(Product product);

  /**
   * 删除商品
   *
   * @param productId
   * @return
   */
  int deleteProduct(String productId);
}
