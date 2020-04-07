package com.apera.backend.enums;

public enum CategoryStateEnum {
  SUCCESS(1, "操作成功"),
  ERROR(-1001, "操作失败");

  private int state;

  private String stateInfo;

  private CategoryStateEnum(int state, String stateInfo) {
    this.state = state;
    this.stateInfo = stateInfo;
  }

  public static CategoryStateEnum stateOf(int index) {
    for (CategoryStateEnum state : values()) {
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
