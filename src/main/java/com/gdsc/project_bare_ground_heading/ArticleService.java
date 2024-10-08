package com.gdsc.project_bare_ground_heading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {
  private final ArticleJPARepository articleRepository;

  public ArticleService(ArticleJPARepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  @Transactional
  public ArticleDTO createArticle(String title, String content) {
    ArticleDomain article =
        ArticleDomain.builder()
            .title(title)
            .contents(content)
            .createdAt(LocalDateTime.now())
            .build();

    ArticleDomain art = articleRepository.save(article);

    return new ArticleDTO(
        art.getId(), art.getTitle(), art.getContents(), art.getCreatedAt(), art.getUpdatedAt());
  }

  @Transactional(readOnly = true)
  public ArticleDTO getArticle(Long id) {
    ArticleDomain article =
        articleRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Article not found"));
    return new ArticleDTO(
        article.getId(),
        article.getTitle(),
        article.getContents(),
        article.getCreatedAt(),
        article.getUpdatedAt());
  }

  @Transactional(readOnly = true)
  public List<ArticleDTO> getAllArticles() {
    List<ArticleDomain> articles = articleRepository.findAll();
    List<ArticleDTO> articleDTOs = new ArrayList<>();
    for (ArticleDomain article : articles) {
      articleDTOs.add(
          new ArticleDTO(
              article.getId(),
              article.getTitle(),
              article.getContents(),
              article.getCreatedAt(),
              article.getUpdatedAt()));
    }
    return articleDTOs;
  }

  @Transactional
  public ArticleDTO changeArticle(long id, String contents, String title) {
    ArticleDomain article =
        articleRepository.findById(id).orElseThrow(() -> new NullPointerException("No ID"));
    if (title != null) {
      article.updateArticleTitle(title);
    }
    if (contents != null) {
      article.updateArticleContents(contents);
    }
    articleRepository.save(article);
    return new ArticleDTO(
        article.getId(),
        article.getTitle(),
        article.getContents(),
        article.getCreatedAt(),
        article.getUpdatedAt());
  }

  @Transactional
  public ArticleDomain deleteArticle(long id) {
    ArticleDTO data = getArticle(id);
    ArticleDomain article =
        ArticleDomain.builder()
            .id(data.id())
            .title(data.title())
            .contents(data.content())
            .createdAt(data.createdAt())
            .updatedAt(LocalDateTime.now())
            .build();
    articleRepository.delete(article);
    return article;
  }
}
