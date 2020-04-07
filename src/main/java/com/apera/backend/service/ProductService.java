package com.apera.backend.service;

import com.apera.backend.dao.ProductDao;
import com.apera.backend.dto.ProductExecution;
import com.apera.backend.entity.Product;
import com.apera.backend.enums.ProductStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
  @Autowired private ProductDao productDao;

  /**
   * 查询商品列表
   *
   * @return
   */
  public ProductExecution getProductList() {
    boolean success = false;
    List<Product> productList = null;

    try {
      productList = productDao.queryProductList();
      success = true;
    } catch (Exception e) {
      throw new RuntimeException("Get category failed - 获取商品列表失败: " + e.toString());
    }

    if (success) {
      return new ProductExecution(ProductStateEnum.SUCCESS, productList);
    } else {
      return new ProductExecution(ProductStateEnum.ERROR);
    }
  }

  /**
   * 添加商品信息
   *
   * @param product
   * @return
   */
  @Transactional
  public ProductExecution addProduct(Product product) throws RuntimeException {
    product.setCreateTime(new Date());

    try {
      // 创建商品信息
      int effectedRows = productDao.insertProduct(product);
      if (effectedRows <= 0) {
        throw new RuntimeException("Add product failed - 添加商品失败");
      }
    } catch (Exception e) {
      throw new RuntimeException("Add product failed - 添加商品失败: " + e.toString());
    }

    return new ProductExecution(ProductStateEnum.SUCCESS, product);
  }

  /**
   * 更新商品信息
   *
   * @param product
   * @return
   */
  @Transactional
  public ProductExecution updateProduct(Product product) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = productDao.updateProduct(product);
      if (effectedRows <= 0) {
        throw new RuntimeException("Update category failed - 更新商品失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Update category failed - 更新商品失败: " + e.toString());
    }

    if (success) {
      return new ProductExecution(ProductStateEnum.SUCCESS, product);
    } else {
      return new ProductExecution(ProductStateEnum.ERROR);
    }
  }

  /**
   * 删除商品信息
   *
   * @param productId
   * @return
   */
  @Transactional
  public ProductExecution deleteProduct(String productId) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = productDao.deleteProduct(productId);
      if (effectedRows <= 0) {
        throw new RuntimeException("Delete category failed - 删除商品失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Delete category failed - 删除商品失败: " + e.toString());
    }

    if (success) {
      return new ProductExecution(ProductStateEnum.SUCCESS);
    } else {
      return new ProductExecution(ProductStateEnum.ERROR);
    }
  }
}
