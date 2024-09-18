package com.gdsc.project_bare_ground_heading;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class articleController {
  private final articleService articleService;

  @Autowired
  public articleController(articleService articleService) {
    this.articleService = articleService;
  }

  @PostMapping("/api/articles")
  public ResponseEntity<ArticleDomain> createArticle(@RequestBody ArticleDTO Contents) {
    ArticleDomain article = new ArticleDomain();
    article.setTitle(Contents.getTitle());
    article.setContents(Contents.getContent());

    article.setCreatedAt(LocalDateTime.now());
    article.setUpdatedAt(LocalDateTime.now());

    articleService.createArticle(article);
    return ResponseEntity.ok().body(article);
  }

  @GetMapping("/api/articles/{id}")
  public ResponseEntity<ArticleDomain> getArticle(@PathVariable("id") Long id) {
    ArticleDomain article = articleService.getArticle(id);
    return ResponseEntity.ok().body(article);
  }

  @GetMapping("api/articles")
  public ResponseEntity<List<ArticleDomain>> getAllArticles() {
    List<ArticleDomain> articles = articleService.getAllArticles();
    return ResponseEntity.ok().body(articles);
  }

  @PatchMapping("api/articles/{id}")
  public ResponseEntity<ArticleDomain> updateArticle(
      @PathVariable("id") Long id, @RequestBody ArticleDTO Contents) {
    ArticleDomain article =
        articleService.changeContent(id, Contents.getContent()).orElse(new ArticleDomain());
    if (article.getTitle() == null && article.getContents() == null) {
      return ResponseEntity.notFound().build();
    }
    article = articleService.changeTitle(id, Contents.getTitle()).orElse(new ArticleDomain());
    if (article.getTitle() == null && article.getContents() == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(article);
  }

  @DeleteMapping("api/articles/{id}")
  public ResponseEntity<ArticleDomain> deleteArticle(@PathVariable("id") Long id) {
    ArticleDomain article = articleService.getArticle(id);
    articleService.deleteArticle(id);
    return ResponseEntity.ok().body(article);
  }
}
