package com.apera.backend.dao;

import com.apera.backend.entity.Article;

import java.util.List;

public interface ArticleDao {
  /**
   * 插入文章
   *
   * @param article
   * @return
   */
  int insertArticle(Article article);

  /**
   * 更新文章
   *
   * @param article
   * @return
   */
  int updateArticle(Article article);

  /**
   * 删除文章
   *
   * @param articleId
   * @return
   */
  int deleteArticle(String articleId);

  /**
   * 查询文章
   *
   * @return
   */
  List<Article> queryArticleList();
}
