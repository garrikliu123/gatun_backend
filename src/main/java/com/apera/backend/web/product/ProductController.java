package com.apera.backend.web.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apera.backend.dto.ProductCategoryExecution;
import com.apera.backend.dto.ProductExecution;
import com.apera.backend.entity.Product;
import com.apera.backend.entity.ProductCategory;
import com.apera.backend.enums.ProductCategoryStateEnum;
import com.apera.backend.enums.ProductStateEnum;
import com.apera.backend.service.ProductCategoryService;
import com.apera.backend.service.ProductService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
  @Autowired private ProductService productService;
  @Autowired private ProductCategoryService productCategoryService;

  // 添加商品
  @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> addProduct(@RequestBody String productJson) {
    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    Product product = null;
    List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();

    try {
      JSONObject obj = JSON.parseObject(productJson);

      JSONArray productCategoryJSON = obj.getJSONArray("productCategory");
      JSONArray productApplicationJSON = obj.getJSONArray("productApplication");
      JSONArray productParameterJSON = obj.getJSONArray("productParameter");

      List<String> productCategory = productCategoryJSON.toJavaList(String.class);
      List<String> productApplication = productApplicationJSON.toJavaList(String.class);
      List<String> productParameter = productParameterJSON.toJavaList(String.class);

      product = mapper.readValue(productJson, Product.class);

      for (String categoryId : productCategory) {
        String productId = product.getProductId();
        ProductCategory pc = new ProductCategory(productId, categoryId, "Product");
        productCategoryList.add(pc);
      }

      for (String categoryId : productApplication) {
        String productId = product.getProductId();
        ProductCategory pc = new ProductCategory(productId, categoryId, "Applications");
        productCategoryList.add(pc);
      }

      for (String categoryId : productParameter) {
        String productId = product.getProductId();
        ProductCategory pc = new ProductCategory(productId, categoryId, "Parameters");
        productCategoryList.add(pc);
      }

    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      ProductExecution pe = productService.addProduct(product);
      ProductCategoryExecution pce = productCategoryService.addProductCategory(productCategoryList);

      if (pe.getState() == ProductStateEnum.SUCCESS.getState()
          && pce.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 更新商品
  @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> updateProduct(@RequestBody String productJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    Product product = null;
    List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
    try {
      JSONObject obj = JSON.parseObject(productJson);
      product = mapper.readValue(productJson, Product.class);

      JSONArray productCategoryJSON = obj.getJSONArray("productCategory");
      JSONArray productApplicationJSON = obj.getJSONArray("productApplication");
      JSONArray productParameterJSON = obj.getJSONArray("productParameter");

      List<String> productCategory = productCategoryJSON.toJavaList(String.class);
      List<String> productApplication = productApplicationJSON.toJavaList(String.class);
      List<String> productParameter = productParameterJSON.toJavaList(String.class);

      for (String categoryId : productCategory) {
        String productId = product.getProductId();
        ProductCategory pc = new ProductCategory(productId, categoryId, "Product");
        productCategoryList.add(pc);
      }

      for (String categoryId : productApplication) {
        String productId = product.getProductId();
        ProductCategory pc = new ProductCategory(productId, categoryId, "Applications");
        productCategoryList.add(pc);
      }

      for (String categoryId : productParameter) {
        String productId = product.getProductId();
        ProductCategory pc = new ProductCategory(productId, categoryId, "Parameters");
        productCategoryList.add(pc);
      }
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      String productId = product.getProductId();
      ProductExecution pe = productService.updateProduct(product);
      ProductCategoryExecution pce1 =
          productCategoryService.deleteProductCategoryByProductId(productId);
      ProductCategoryExecution pce2 =
          productCategoryService.addProductCategory(productCategoryList);

      if (pe.getState() == ProductStateEnum.SUCCESS.getState()
          && pce1.getState() == ProductCategoryStateEnum.SUCCESS.getState()
          && pce2.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 删除商品
  @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> deleteProduct(@RequestBody String productIdJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();

    try {
      JSONObject obj = JSON.parseObject(productIdJson);
      String productId = (String) obj.get("productId");
      ProductCategoryExecution pce =
          productCategoryService.deleteProductCategoryByProductId(productId);
      ProductExecution pe = productService.deleteProduct(productId);

      if (pe.getState() == ProductStateEnum.SUCCESS.getState()
          && pce.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 获取商品列表
  @RequestMapping(value = "/getProductList", method = RequestMethod.GET)
  @ResponseBody
  private Map<String, Object> getProductList() {
    Map<String, Object> modelMap = new HashMap<String, Object>();
    ProductExecution pe = productService.getProductList();

    if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
      modelMap.put("success", true);
      modelMap.put("data", pe.getProductList());
    } else {
      modelMap.put("success", false);
      modelMap.put("errMsg", "get product list failed");
    }

    return modelMap;
  }
}
