package com.gdsc.project_bare_ground_heading;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/articles")
public class ArticleController {
  private final ArticleService articleService;

  @Autowired
  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @PostMapping("")
  public ResponseEntity<ArticleDomain> createArticle(@RequestBody ArticleDTO Contents) {
    ArticleDomain article = new ArticleDomain();
    article.setTitle(Contents.getTitle());
    article.setContents(Contents.getContent());

    article.setCreatedAt(LocalDateTime.now());
    article.setUpdatedAt(LocalDateTime.now());

    articleService.createArticle(article);
    return ResponseEntity.ok().body(article);
  }

  @GetMapping("{id}")
  public ResponseEntity<ArticleDomain> getArticle(@PathVariable("id") Long id) {
    ArticleDomain article = articleService.getArticle(id);
    return ResponseEntity.ok().body(article);
  }

  @GetMapping("")
  public ResponseEntity<List<ArticleDomain>> getAllArticles() {
    List<ArticleDomain> articles = articleService.getAllArticles();
    return ResponseEntity.ok().body(articles);
  }

  @PatchMapping("{id}")
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

  @DeleteMapping("{id}")
  public ResponseEntity<ArticleDomain> deleteArticle(@PathVariable("id") Long id) {
    ArticleDomain article = articleService.getArticle(id);
    articleService.deleteArticle(id);
    return ResponseEntity.ok().body(article);
  }
}
