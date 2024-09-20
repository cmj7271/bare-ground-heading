package com.gdsc.project_bare_ground_heading;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
  private final ArticleJPARepository articleRepository;

  @Autowired
  public ArticleService(ArticleJPARepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public ArticleDomain createArticle(ArticleDomain article) {
    articleRepository.save(article);
    return article;
  }

  public ArticleDomain getArticle(Long id) {
    return articleRepository.findById(id).orElse(null);
  }

  public List<ArticleDomain> getAllArticles() {
    return articleRepository.findAll();
  }

  public Optional<ArticleDomain> changeContent(long id, String Content) {
    ArticleDomain article =
        articleRepository.findById(id).orElseThrow(() -> new NullPointerException("No ID"));
    article.setContents(Content);
    articleRepository.save(article);
    return Optional.of(article);
  }

  public Optional<ArticleDomain> changeTitle(long id, String title) {
    ArticleDomain article =
        articleRepository.findById(id).orElseThrow(() -> new NullPointerException("No ID"));
    article.setTitle(title);
    articleRepository.save(article);
    return Optional.of(article);
  }

  public Optional<ArticleDomain> deleteArticle(long id) {
    ArticleDomain article = getArticle(id);
    articleRepository.delete(article);
    return Optional.of(article);
  }
}
