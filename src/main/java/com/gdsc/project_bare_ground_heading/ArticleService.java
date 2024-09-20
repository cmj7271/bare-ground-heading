package com.gdsc.project_bare_ground_heading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
  private final ArticleJPARepository articleRepository;

  @Autowired
  public ArticleService(ArticleJPARepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public ArticleDTO createArticle(String title, String content) {
    ArticleDomain article =
        ArticleDomain.builder()
            .title(title)
            .contents(content)
            .createdAt(LocalDateTime.now())
            .build();
    articleRepository.save(article);
    return new ArticleDTO(
        article.getTitle(), article.getContents(), article.getCreatedAt(), article.getUpdatedAt());
  }

  public ArticleDTO getArticle(Long id) {
    ArticleDomain article =
        articleRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Article not found"));
    return new ArticleDTO(
        article.getTitle(), article.getContents(), article.getCreatedAt(), article.getUpdatedAt());
  }

  public List<ArticleDTO> getAllArticles() {
    List<ArticleDomain> articles = articleRepository.findAll();
    List<ArticleDTO> articleDTOs = new ArrayList<>();
    for (ArticleDomain article : articles) {
      articleDTOs.add(
          new ArticleDTO(
              article.getTitle(),
              article.getContents(),
              article.getCreatedAt(),
              article.getUpdatedAt()));
    }
    return articleDTOs;
  }

  public ArticleDTO changeContent(long id, String Content) {
    ArticleDomain article =
        articleRepository.findById(id).orElseThrow(() -> new NullPointerException("No ID"));
    article.updateArticleContents(Content);
    articleRepository.save(article);
    return new ArticleDTO(
        article.getTitle(), article.getContents(), article.getCreatedAt(), article.getUpdatedAt());
  }

  public ArticleDTO changeTitle(long id, String title) {
    ArticleDomain article =
        articleRepository.findById(id).orElseThrow(() -> new NullPointerException("No ID"));
    article.updateArticleTitle(title);
    articleRepository.save(article);
    return new ArticleDTO(
        title, article.getContents(), article.getCreatedAt(), article.getUpdatedAt());
  }

  public ArticleDomain deleteArticle(long id) {
    ArticleDTO data = getArticle(id);
    ArticleDomain article =
        ArticleDomain.builder()
            .title(data.title())
            .contents(data.content())
            .createdAt(data.createdAt())
            .updatedAt(LocalDateTime.now())
            .build();
    articleRepository.delete(article);
    return article;
  }
}
