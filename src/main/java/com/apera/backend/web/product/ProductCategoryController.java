package com.apera.backend.web.product;

import com.apera.backend.dto.ProductCategoryExecution;
import com.apera.backend.enums.ProductCategoryStateEnum;
import com.apera.backend.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/productCategory")
public class ProductCategoryController {
  @Autowired private ProductCategoryService productCategoryService;

  // 获取分类列表
  @RequestMapping(value = "/getProductCategoryList", method = RequestMethod.GET)
  @ResponseBody
  private Map<String, Object> getProductList() {
    Map<String, Object> modelMap = new HashMap<String, Object>();
    ProductCategoryExecution pec = productCategoryService.getProductCategoryList();

    if (pec.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
      modelMap.put("success", true);
      modelMap.put("data", pec.getProductCategoryList());
    } else {
      modelMap.put("success", false);
      modelMap.put("errMsg", "get category list failed");
    }

    return modelMap;
  }
}
