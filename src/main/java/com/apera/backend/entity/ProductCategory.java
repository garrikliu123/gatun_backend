package com.apera.backend.entity;

public class ProductCategory {
  private String productId;
  private String categoryId;
  private String categoryType;

  public ProductCategory(String productId, String categoryId, String categoryType) {
    this.productId = productId;
    this.categoryId = categoryId;
    this.categoryType = categoryType;
  }

  public String getCategoryType() {
    return categoryType;
  }

  public void setCategoryType(String categoryType) {
    this.categoryType = categoryType;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  @Override
  public String toString() {
    return "productId: " + productId + " categoryId: " + categoryId;
  }
}
