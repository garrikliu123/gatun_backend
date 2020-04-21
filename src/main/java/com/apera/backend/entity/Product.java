package com.apera.backend.entity;

import java.util.Date;

public class Product {
  private String productId;
  private String productName;
  private String productDesc;
  private String productSpecs;
  private String productWarranty;
  private String productFeatures;
  private String productVideo;
  private String productImages;
  private String productFiles;
  private String productOptions;
  private Double productPrice;
  private Date createTime;

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductDesc() {
    return productDesc;
  }

  public void setProductDesc(String productDesc) {
    this.productDesc = productDesc;
  }

  public String getProductSpecs() {
    return productSpecs;
  }

  public void setProductSpecs(String productSpecs) {
    this.productSpecs = productSpecs;
  }

  public String getProductWarranty() {
    return productWarranty;
  }

  public void setProductWarranty(String productWarranty) {
    this.productWarranty = productWarranty;
  }

  public String getProductFeatures() {
    return productFeatures;
  }

  public void setProductFeatures(String productFeatures) {
    this.productFeatures = productFeatures;
  }

  public String getProductVideo() {
    return productVideo;
  }

  public void setProductVideo(String productVideo) {
    this.productVideo = productVideo;
  }

  public Double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(Double productPrice) {
    this.productPrice = productPrice;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getProductImages() {
    return productImages;
  }

  public void setProductImages(String productImages) {
    this.productImages = productImages;
  }

  public String getProductFiles() {
    return productFiles;
  }

  public void setProductFiles(String productFiles) {
    this.productFiles = productFiles;
  }

  public String getProductOptions() {
    return productOptions;
  }

  public void setProductOptions(String productOptions) {
    this.productOptions = productOptions;
  }

  @Override
  public String toString() {

    return "productId: "
        + productId
        + "\n\n"
        + "productName: "
        + productName
        + "\n\n"
        + "productPrice: "
        + productPrice
        + "\n\n"
        + "productDesc: "
        + productDesc
        + "\n\n"
        + "productSpecs: "
        + productSpecs
        + "\n\n"
        + "productWarranty: "
        + productWarranty
        + "\n\n"
        + "productFeatures: "
        + productFeatures
        + "\n\n"
        + "productVideo: "
        + productVideo
        + "\n\n"
        + "productImages: "
        + productWarranty
        + "\n\n"
        + "createTime: "
        + createTime
        + "\n\n";
  }
}
