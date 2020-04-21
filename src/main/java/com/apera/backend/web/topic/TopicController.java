package com.apera.backend.web.topic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apera.backend.dto.TopicCategoryExecution;
import com.apera.backend.dto.TopicExecution;
import com.apera.backend.entity.Topic;
import com.apera.backend.entity.TopicCategory;
import com.apera.backend.enums.TopicCategoryStateEnum;
import com.apera.backend.enums.TopicStateEnum;
import com.apera.backend.service.TopicCategoryService;
import com.apera.backend.service.TopicService;
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
@RequestMapping("/topic")
public class TopicController {

  @Autowired private TopicService topicService;
  @Autowired private TopicCategoryService topicCategoryService;

  // 添加分类
  @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> addTopic(@RequestBody String requestJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    Topic topic = null;
    List<TopicCategory> topicCategoryList = new ArrayList<TopicCategory>();

    try {
      JSONObject obj = JSON.parseObject(requestJson);
      JSONArray categoryListJSON = obj.getJSONArray("categoryList");
      List<String> categoryList = categoryListJSON.toJavaList(String.class);

      topic = mapper.readValue(requestJson, Topic.class);

      for (String categoryId : categoryList) {
        String topicId = topic.getTopicId();
        TopicCategory pc = new TopicCategory(topicId, categoryId);
        topicCategoryList.add(pc);
      }
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      TopicExecution ce = topicService.addTopic(topic);
      TopicCategoryExecution pce = topicCategoryService.addTopicCategory(topicCategoryList);

      if (ce.getState() == TopicStateEnum.SUCCESS.getState()
          && pce.getState() == TopicCategoryStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 更新分类
  @RequestMapping(value = "/updateTopic", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> updateTopic(@RequestBody String requestJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    Topic topic = null;
    List<TopicCategory> topicCategoryList = new ArrayList<TopicCategory>();

    try {
      JSONObject obj = JSON.parseObject(requestJson);
      JSONArray categoryListJSON = obj.getJSONArray("categoryList");
      List<String> categoryList = categoryListJSON.toJavaList(String.class);

      topic = mapper.readValue(requestJson, Topic.class);

      for (String categoryId : categoryList) {
        String topicId = topic.getTopicId();
        TopicCategory pc = new TopicCategory(topicId, categoryId);
        topicCategoryList.add(pc);
      }
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      String topicId = topic.getTopicId();
      TopicExecution ce = topicService.updateTopic(topic);
      TopicCategoryExecution pce1 = topicCategoryService.deleteTopicCategoryByTopicId(topicId);
      TopicCategoryExecution pce2 = topicCategoryService.addTopicCategory(topicCategoryList);

      if (ce.getState() == TopicStateEnum.SUCCESS.getState()
          && pce1.getState() == TopicCategoryStateEnum.SUCCESS.getState()
          && pce2.getState() == TopicCategoryStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 删除分类
  @RequestMapping(value = "/deleteTopic", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> deleteTopic(@RequestBody String requestJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();

    JSONObject obj = JSON.parseObject(requestJson);
    String topicId = (String) obj.get("topicId");

    try {
      TopicCategoryExecution ce1 = topicCategoryService.deleteTopicCategoryByTopicId(topicId);
      TopicExecution ce2 = topicService.deleteTopic(topicId);

      if (ce1.getState() == TopicStateEnum.SUCCESS.getState()
          && ce2.getState() == TopicStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 获取分类列表
  @RequestMapping(value = "/getTopicList", method = RequestMethod.GET)
  @ResponseBody
  private Map<String, Object> getTopicList() {
    Map<String, Object> modelMap = new HashMap<String, Object>();
    TopicExecution ce = topicService.getTopicList();

    if (ce.getState() == TopicStateEnum.SUCCESS.getState()) {
      modelMap.put("success", true);
      modelMap.put("data", ce.getTopicList());
    } else {
      modelMap.put("success", false);
      modelMap.put("errMsg", "get category list failed");
    }

    return modelMap;
  }
}
