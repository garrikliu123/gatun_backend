package com.apera.backend.dto;

import com.apera.backend.entity.Article;
import com.apera.backend.enums.ArticleStateEnum;

import java.util.List;

public class ArticleExecution {
  // 结果状态
  private int state;
  // 状态标识
  private String stateInfo;
  // 操作的 category（增删改分类的时候用）
  private Article article;

  // 获取的 category 列表(查询商品列表的时候用)
  private List<Article> articleList;

  public ArticleExecution() {}

  // 失败的构造器
  public ArticleExecution(ArticleStateEnum stateEnum) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
  }

  // 成功的构造器
  public ArticleExecution(ArticleStateEnum stateEnum, Article article) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.article = article;
  }

  // 成功的构造器
  public ArticleExecution(ArticleStateEnum stateEnum, List<Article> articleList) {
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.articleList = articleList;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getStateInfo() {
    return stateInfo;
  }

  public void setStateInfo(String stateInfo) {
    this.stateInfo = stateInfo;
  }

  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
    this.article = article;
  }

  public List<Article> getArticleList() {
    return articleList;
  }

  public void setArticleList(List<Article> articleList) {
    this.articleList = articleList;
  }
}
