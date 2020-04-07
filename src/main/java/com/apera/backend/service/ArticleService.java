package com.apera.backend.service;

import com.apera.backend.dao.ArticleDao;
import com.apera.backend.dto.ArticleExecution;
import com.apera.backend.entity.Article;
import com.apera.backend.enums.ArticleStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {
  @Autowired private ArticleDao articleDao;

  /**
   * 查询分类列表
   *
   * @return
   */
  public ArticleExecution getArticleList() {
    boolean success = false;
    List<Article> articleList = null;

    try {
      articleList = articleDao.queryArticleList();
      success = true;
    } catch (Exception e) {
      throw new RuntimeException("Get article failed - 获取文章列表失败: " + e.toString());
    }

    if (success) {
      return new ArticleExecution(ArticleStateEnum.SUCCESS, articleList);
    } else {
      return new ArticleExecution(ArticleStateEnum.ERROR);
    }
  }

  /**
   * 添加分类信息
   *
   * @param article
   * @return
   */
  @Transactional
  public ArticleExecution addArticle(Article article) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = articleDao.insertArticle(article);
      if (effectedRows <= 0) {
        throw new RuntimeException("Add article failed - 添加文章失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Add article failed - 添加文章失败: " + e.toString());
    }

    if (success) {
      return new ArticleExecution(ArticleStateEnum.SUCCESS, article);
    } else {
      return new ArticleExecution(ArticleStateEnum.ERROR);
    }
  }

  /**
   * 更新分类信息
   *
   * @param article
   * @return
   */
  @Transactional
  public ArticleExecution updateArticle(Article article) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = articleDao.updateArticle(article);
      if (effectedRows <= 0) {
        throw new RuntimeException("Update article failed - 更新文章失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Update article failed - 更新文章失败: " + e.toString());
    }

    if (success) {
      return new ArticleExecution(ArticleStateEnum.SUCCESS, article);
    } else {
      return new ArticleExecution(ArticleStateEnum.ERROR);
    }
  }

  /**
   * 删除分类信息
   *
   * @param articleId
   * @return
   */
  @Transactional
  public ArticleExecution deleteArticle(String articleId) throws RuntimeException {
    boolean success = false;

    try {
      int effectedRows = articleDao.deleteArticle(articleId);
      if (effectedRows <= 0) {
        throw new RuntimeException("Delete article failed - 删除文章失败");
      } else {
        success = true;
      }
    } catch (Exception e) {
      throw new RuntimeException("Delete article failed - 删除文章失败: " + e.toString());
    }

    if (success) {
      return new ArticleExecution(ArticleStateEnum.SUCCESS);
    } else {
      return new ArticleExecution(ArticleStateEnum.ERROR);
    }
  }
}
