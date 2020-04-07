package com.apera.backend.web.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apera.backend.dto.CategoryExecution;
import com.apera.backend.entity.Category;
import com.apera.backend.enums.CategoryStateEnum;
import com.apera.backend.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryController {
  @Autowired private CategoryService categoryService;

  // 添加分类
  @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> addCategory(@RequestBody String categoryJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    Category category = null;

    try {
      category = mapper.readValue(categoryJson, Category.class);
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      CategoryExecution ce = categoryService.addCategory(category);

      if (ce.getState() == CategoryStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 更新分类
  @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> updateCategory(@RequestBody String categoryJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    Category category = null;

    try {
      category = mapper.readValue(categoryJson, Category.class);
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      CategoryExecution ce = categoryService.updateCategory(category);

      if (ce.getState() == CategoryStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 删除分类
  @RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> deleteCategory(@RequestBody String categoryIdJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();

    JSONObject obj = JSON.parseObject(categoryIdJson);
    String categoryId = (String) obj.get("categoryId");

    try {
      CategoryExecution ce = categoryService.deleteCategory(categoryId);

      if (ce.getState() == CategoryStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 获取分类列表
  @RequestMapping(value = "/getCategoryList", method = RequestMethod.GET)
  @ResponseBody
  private Map<String, Object> getProductList() {
    Map<String, Object> modelMap = new HashMap<String, Object>();
    CategoryExecution ce = categoryService.getCategoryList();

    if (ce.getState() == CategoryStateEnum.SUCCESS.getState()) {
      modelMap.put("success", true);
      modelMap.put("data", ce.getCategoryList());
    } else {
      modelMap.put("success", false);
      modelMap.put("errMsg", "get category list failed");
    }

    return modelMap;
  }
}
