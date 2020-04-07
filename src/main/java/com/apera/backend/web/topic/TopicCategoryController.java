package com.apera.backend.web.topic;

import com.apera.backend.dto.TopicCategoryExecution;
import com.apera.backend.enums.TopicCategoryStateEnum;
import com.apera.backend.service.TopicCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/topicCategory")
public class TopicCategoryController {
  @Autowired private TopicCategoryService topicCategoryService;

  // 获取分类列表
  @RequestMapping(value = "/getTopicCategoryList", method = RequestMethod.GET)
  @ResponseBody
  private Map<String, Object> getProductList() {
    Map<String, Object> modelMap = new HashMap<String, Object>();
    TopicCategoryExecution pec = topicCategoryService.getTopicCategoryList();

    if (pec.getState() == TopicCategoryStateEnum.SUCCESS.getState()) {
      modelMap.put("success", true);
      modelMap.put("data", pec.getTopicCategoryList());
    } else {
      modelMap.put("success", false);
      modelMap.put("errMsg", "get topic category list failed");
    }

    return modelMap;
  }
}
