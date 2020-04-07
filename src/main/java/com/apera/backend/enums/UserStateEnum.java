package com.apera.backend.enums;

public enum UserStateEnum {
  SUCCESS(1, "操作成功"),
  ERROR(-1001, "操作失败");

  private int state;

  private String stateInfo;

  private UserStateEnum(int state, String stateInfo) {
    this.state = state;
    this.stateInfo = stateInfo;
  }

  public static UserStateEnum stateOf(int index) {
    for (UserStateEnum state : values()) {
      if (state.getState() == index) {
        return state;
      }
    }
    return null;
  }

  public int getState() {
    return state;
  }

  public String getStateInfo() {
    return stateInfo;
  }
}
