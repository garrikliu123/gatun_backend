package com.apera.backend.web.home;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apera.backend.dto.HomeSectionExecution;
import com.apera.backend.entity.HomeSection;
import com.apera.backend.enums.HomeSectionStateEnum;
import com.apera.backend.service.HomeSectionService;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
@RequestMapping("/homeSection")
public class HomeSectionController {
  @Autowired private HomeSectionService homeSectionService;

  // 添加分类
  @RequestMapping(value = "/addSection", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> addSection(@RequestBody String requestJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    HomeSection homeSection = null;

    try {
      JSONObject obj = JSON.parseObject(requestJson);
      homeSection = mapper.readValue(requestJson, HomeSection.class);
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      HomeSectionExecution ce = homeSectionService.addSection(homeSection);

      if (ce.getState() == HomeSectionStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 更新分类
  @RequestMapping(value = "/updateSection", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> updateSection(@RequestBody String requestJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    HomeSection homeSection = null;

    try {
      JSONObject obj = JSON.parseObject(requestJson);
      homeSection = mapper.readValue(requestJson, HomeSection.class);
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      HomeSectionExecution ce = homeSectionService.updateSection(homeSection);

      if (ce.getState() == HomeSectionStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 删除分类
  @RequestMapping(value = "/deleteSection", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> deleteSection(@RequestBody String requestJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();

    JSONObject obj = JSON.parseObject(requestJson);
    String sectionId = (String) obj.get("sectionId");

    try {
      HomeSectionExecution ce = homeSectionService.deleteSection(sectionId);

      if (ce.getState() == HomeSectionStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 获取分类列表
  @RequestMapping(value = "/getSectionList", method = RequestMethod.GET)
  @ResponseBody
  private Map<String, Object> getSectionList() {
    Map<String, Object> modelMap = new HashMap<String, Object>();
    HomeSectionExecution ce = homeSectionService.getSectionList();

    if (ce.getState() == HomeSectionStateEnum.SUCCESS.getState()) {
      modelMap.put("success", true);
      modelMap.put("data", ce.getHomeSectionList());
    } else {
      modelMap.put("success", false);
      modelMap.put("errMsg", "get category list failed");
    }

    return modelMap;
  }
}
