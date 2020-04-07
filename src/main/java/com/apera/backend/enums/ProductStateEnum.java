package com.apera.backend.enums;

public enum ProductStateEnum {
  SUCCESS(1, "操作成功"),
  ERROR(-1001, "操作失败");

  private int state;

  private String stateInfo;

  private ProductStateEnum(int state, String stateInfo) {
    this.state = state;
    this.stateInfo = stateInfo;
  }

  public static ProductStateEnum stateOf(int index) {
    for (ProductStateEnum state : values()) {
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
