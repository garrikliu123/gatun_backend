package com.apera.backend.web.article;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apera.backend.dto.ArticleExecution;
import com.apera.backend.entity.Article;
import com.apera.backend.enums.ArticleStateEnum;
import com.apera.backend.service.ArticleService;
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
@RequestMapping("/article")
public class ArticleController {
  @Autowired private ArticleService articleService;

  // 添加分类
  @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> addArticle(@RequestBody String articleJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    Article article = null;

    try {
      article = mapper.readValue(articleJson, Article.class);
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      ArticleExecution ce = articleService.addArticle(article);

      if (ce.getState() == ArticleStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 更新分类
  @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> updateArticle(@RequestBody String articleJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    Article article = null;

    try {
      article = mapper.readValue(articleJson, Article.class);
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      ArticleExecution ce = articleService.updateArticle(article);

      if (ce.getState() == ArticleStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 删除分类
  @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> deleteArticle(@RequestBody String articleIdJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();

    JSONObject obj = JSON.parseObject(articleIdJson);
    String articleId = (String) obj.get("articleId");

    try {
      ArticleExecution ce = articleService.deleteArticle(articleId);

      if (ce.getState() == ArticleStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 获取分类列表
  @RequestMapping(value = "/getArticleList", method = RequestMethod.GET)
  @ResponseBody
  private Map<String, Object> getArticleList() {
    Map<String, Object> modelMap = new HashMap<String, Object>();
    ArticleExecution ce = articleService.getArticleList();

    if (ce.getState() == ArticleStateEnum.SUCCESS.getState()) {
      modelMap.put("success", true);
      modelMap.put("data", ce.getArticleList());
    } else {
      modelMap.put("success", false);
      modelMap.put("errMsg", "get article list failed");
    }

    return modelMap;
  }
}
