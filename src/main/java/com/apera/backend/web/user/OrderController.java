package com.apera.backend.web.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apera.backend.dto.OrderExecution;
import com.apera.backend.entity.Order;
import com.apera.backend.enums.OrderStateEnum;
import com.apera.backend.service.OrderService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
  @Autowired private OrderService orderService;

  // 添加商品
  @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> addOrder(@RequestBody String requestJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    Order order = null;

    try {
      JSONObject obj = JSON.parseObject(requestJson);
      order = mapper.readValue(requestJson, Order.class);
    } catch (Exception e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
      return modelMap;
    }

    try {
      OrderExecution ce = orderService.addOrder(order);

      if (ce.getState() == OrderStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 删除商品
  @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
  @ResponseBody
  private Map<String, Object> deleteProduct(@RequestBody String orderIdJson) {

    Map<String, Object> modelMap = new HashMap<String, Object>();

    try {
      System.out.println(orderIdJson);
      JSONObject obj = JSON.parseObject(orderIdJson);
      String orderId = (String) obj.get("orderId");
      OrderExecution pe = orderService.deleteOrder(orderId);

      if (pe.getState() == OrderStateEnum.SUCCESS.getState()) {
        modelMap.put("success", true);
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }

  // 获取商品列表
  @RequestMapping(value = "/getOrderListByUserId", method = RequestMethod.GET)
  @ResponseBody
  private Map<String, Object> getOrderListByUserId(String userId) {
    Map<String, Object> modelMap = new HashMap<String, Object>();
    List<Order> orderList = null;

    try {
      OrderExecution ce = orderService.getOrderListByUserId(userId);
      if (ce.getState() == OrderStateEnum.SUCCESS.getState()) {
        orderList = ce.getOrderList();

        modelMap.put("success", true);
        modelMap.put("orderlist", orderList);
      } else {
        modelMap.put("success", false);
        modelMap.put("errMsg", "get order list failed");
      }
    } catch (RuntimeException e) {
      modelMap.put("success", false);
      modelMap.put("errMsg", e.getMessage());
    }

    return modelMap;
  }
}
