package com.apera.backend.dao;

import com.apera.backend.entity.HomeSection;

import java.util.List;

public interface HomeSectionDao {

  int insertSection(HomeSection homeSection);

  int updateSection(HomeSection homeSection);

  int deleteSection(String sectionId);

  List<HomeSection> querySectionList();
}
