package com.apera.backend.web.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apera.backend.dto.UserExecution;
import com.apera.backend.entity.User;
import com.apera.backend.enums.UserStateEnum;
import com.apera.backend.service.UserService;
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
@RequestMapping("/user")
public class UserController {
  @Autowired private UserService userService;

  // 添加分类
  @RequestMapping(value = "/addUser", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> addUser(@RequestBody String requestJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    User user = null;

    try {
      JSONObject obj = JSON.parseObject(requestJson);
      user = mapper.readValue(requestJson, User.class);
      user.setUserEmail(user.getUserEmail().toLowerCase());
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      UserExecution ce = userService.addUser(user);

      if (ce.getState() == UserStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 更新分类
  @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> updateUser(@RequestBody String requestJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    User user = null;

    try {
      JSONObject obj = JSON.parseObject(requestJson);
      user = mapper.readValue(requestJson, User.class);
      user.setUserEmail(user.getUserEmail().toLowerCase());
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      UserExecution ce = userService.updateUser(user);

      if (ce.getState() == UserStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 删除分类
  @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> deleteUser(@RequestBody String requestJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();

    JSONObject obj = JSON.parseObject(requestJson);
    String userEmail = (String) obj.get("userEmail");

    try {
      userEmail = userEmail.toLowerCase();
      UserExecution ce = userService.deleteUser(userEmail);

      if (ce.getState() == UserStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 获取分类列表
  @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
  @ResponseBody
  private Map<String, Object> getUserList() {
    Map<String, Object> modelMap = new HashMap<String, Object>();
    UserExecution ce = userService.getUserList();

    if (ce.getState() == UserStateEnum.SUCCESS.getState()) {
      List<User> list = new ArrayList<User>();

      for (User u : ce.getUserList()) {
        User user = new User();
        user.setUserEmail(u.getUserEmail());
        list.add(user);
      }

      modelMap.put("success", true);
      modelMap.put("data", list);
    } else {
      modelMap.put("success", false);
      modelMap.put("errMsg", "get user list failed");
    }

    return modelMap;
  }

  // 添加分类
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> login(@RequestBody String requestJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    User user = null;

    try {
      JSONObject obj = JSON.parseObject(requestJson);
      user = mapper.readValue(requestJson, User.class);
      user.setUserEmail(user.getUserEmail().toLowerCase());
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      UserExecution ce = userService.getUserByEmail(user.getUserEmail());
      if (ce.getState() == UserStateEnum.SUCCESS.getState()) {
        User dbUser = ce.getUser();
        if (dbUser.getUserPassword().equals(user.getUserPassword())) {
          modelMap.put("success", true);
          if ("manager".equals(dbUser.getUserType())) {
            modelMap.put("isManager", true);
          } else {
            modelMap.put("isManager", false);
          }
        } else {
          modelMap.put("success", false);
          modelMap.put("errMsg", "Password is wrong");
        }
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 获取分类列表
  @RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
  @ResponseBody
  private Map<String, Object> getUserByEmail(String userEmail) {
    Map<String, Object> modelMap = new HashMap<String, Object>();
    User user = null;

    try {
      userEmail = userEmail.toLowerCase();
      UserExecution ce = userService.getUserByEmail(userEmail);
      if (ce.getState() == UserStateEnum.SUCCESS.getState()) {
        user = ce.getUser();

        user.setUserPassword("");

        modelMap.put("success", true);
        modelMap.put("data", user);
      } else {
        modelMap.put("success", false);
        modelMap.put("errMsg", "get user failed");
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }
}
