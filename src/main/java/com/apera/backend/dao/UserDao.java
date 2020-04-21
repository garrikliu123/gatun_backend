package com.apera.backend.dao;

import com.apera.backend.entity.User;

import java.util.List;

public interface UserDao {

  int insertUser(User user);

  int updateUser(User user);

  int deleteUser(String userEmail);

  List<User> queryUserList();

  User queryUserByEmail(String userEmail);

  User queryUserById(String userId);
}
