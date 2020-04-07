package com.apera.backend.service;

import com.apera.backend.dao.HomeSectionDao;
import com.apera.backend.dto.HomeSectionExecution;
import com.apera.backend.entity.HomeSection;
import com.apera.backend.enums.HomeSectionStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HomeSectionService {
  @Autowired private HomeSectionDao homeSectionDao;

  public HomeSectionExecution getSectionList() {
    boolean success = false;
    List<HomeSection> list = null;

    try {
      list = homeSectionDao.querySectionList();
      success = true;
    } catch (Exception e) {
      throw new RuntimeException("Get section failed - 获取区块列表失败: " + e.toString());
    }

    if (success) {
      return new HomeSectionExecution(HomeSectionStateEnum.SUCCESS, list);
    } else {
      return new HomeSectionExecution(HomeSectionStateEnum.ERROR);
    }
  }

  @Transactional
  public HomeSectionExecution addSection(HomeSection homeSection) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = homeSectionDao.insertSection(homeSection);
      if (effectedRows <= 0) {
        throw new RuntimeException("Add section failed - 添加区块失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Add section failed - 添加区块失败: " + e.toString());
    }

    if (success) {
      return new HomeSectionExecution(HomeSectionStateEnum.SUCCESS, homeSection);
    } else {
      return new HomeSectionExecution(HomeSectionStateEnum.ERROR);
    }
  }

  @Transactional
  public HomeSectionExecution updateSection(HomeSection homeSection) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = homeSectionDao.updateSection(homeSection);
      if (effectedRows <= 0) {
        throw new RuntimeException("Update section failed - 更新区块失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Update section failed - 更新区块失败: " + e.toString());
    }

    if (success) {
      return new HomeSectionExecution(HomeSectionStateEnum.SUCCESS, homeSection);
    } else {
      return new HomeSectionExecution(HomeSectionStateEnum.ERROR);
    }
  }

  @Transactional
  public HomeSectionExecution deleteSection(String sectionId) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = homeSectionDao.deleteSection(sectionId);
      if (effectedRows <= 0) {
        throw new RuntimeException("Delete section failed - 删除区块失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Delete section failed - 删除区块失败: " + e.toString());
    }

    if (success) {
      return new HomeSectionExecution(HomeSectionStateEnum.SUCCESS);
    } else {
      return new HomeSectionExecution(HomeSectionStateEnum.ERROR);
    }
  }
}
