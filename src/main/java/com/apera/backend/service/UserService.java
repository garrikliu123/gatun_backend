package com.apera.backend.service;

import com.apera.backend.dao.UserDao;
import com.apera.backend.dto.UserExecution;
import com.apera.backend.entity.User;
import com.apera.backend.enums.UserStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
  @Autowired private UserDao userDao;

  public UserExecution getUserList() {
    boolean success = false;
    List<User> list = null;

    try {
      list = userDao.queryUserList();
      success = true;
    } catch (Exception e) {
      throw new RuntimeException("Get user failed - 获取用户列表失败: " + e.toString());
    }

    if (success) {
      return new UserExecution(UserStateEnum.SUCCESS, list);
    } else {
      return new UserExecution(UserStateEnum.ERROR);
    }
  }

  public UserExecution getUserByEmail(String userEmail) {
    boolean success = false;
    User user = null;

    try {
      user = userDao.queryUserByEmail(userEmail);

      if (user == null) {
        throw new RuntimeException("User does not exist");
      } else {
        success = true;
      }

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }

    if (success) {
      return new UserExecution(UserStateEnum.SUCCESS, user);
    } else {
      return new UserExecution(UserStateEnum.ERROR);
    }
  }

  public UserExecution getUserById(String userId) {
    boolean success = false;
    User user = null;

    try {
      user = userDao.queryUserById(userId);

      if (user == null) {
        throw new RuntimeException("User does not exist");
      } else {
        success = true;
      }

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }

    if (success) {
      return new UserExecution(UserStateEnum.SUCCESS, user);
    } else {
      return new UserExecution(UserStateEnum.ERROR);
    }
  }

  @Transactional
  public UserExecution addUser(User user) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = userDao.insertUser(user);
      if (effectedRows <= 0) {
        throw new RuntimeException("Add user failed - 添加用户失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Add section failed - 添加区块失败: " + e.toString());
    }

    if (success) {
      return new UserExecution(UserStateEnum.SUCCESS, user);
    } else {
      return new UserExecution(UserStateEnum.ERROR);
    }
  }

  @Transactional
  public UserExecution updateUser(User user) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = userDao.updateUser(user);
      if (effectedRows <= 0) {
        throw new RuntimeException("Update user failed - 更新用户失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Update user failed - 更新用户失败: " + e.toString());
    }

    if (success) {
      return new UserExecution(UserStateEnum.SUCCESS, user);
    } else {
      return new UserExecution(UserStateEnum.ERROR);
    }
  }

  @Transactional
  public UserExecution deleteUser(String userEmail) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = userDao.deleteUser(userEmail);
      if (effectedRows <= 0) {
        throw new RuntimeException("Delete user failed - 删除用户失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Delete user failed - 删除用户失败: " + e.toString());
    }

    if (success) {
      return new UserExecution(UserStateEnum.SUCCESS);
    } else {
      return new UserExecution(UserStateEnum.ERROR);
    }
  }
}
