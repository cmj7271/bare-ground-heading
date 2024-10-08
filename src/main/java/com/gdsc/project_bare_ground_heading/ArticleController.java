package com.gdsc.project_bare_ground_heading;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@Slf4j
public class ArticleController {
  private final ArticleService articleService;

  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @PostMapping
  public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO contents) {
    return ResponseEntity.ok()
        .body(articleService.createArticle(contents.title(), contents.content()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ArticleDTO> getArticle(@PathVariable("id") Long id) {
    ArticleDTO article = articleService.getArticle(id);
    return ResponseEntity.ok().body(article);
  }

  @GetMapping
  public ResponseEntity<List<ArticleDTO>> getAllArticles() {
    List<ArticleDTO> articles = articleService.getAllArticles();
    return ResponseEntity.ok().body(articles);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ArticleDTO> updateArticle(
      @PathVariable("id") Long id, @RequestBody ArticleDTO Contents) {
    articleService.changeArticle(id, Contents.content(), Contents.title());

    ArticleDTO article = articleService.getArticle(id);
    return ResponseEntity.ok().body(article);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ArticleDTO> deleteArticle(@PathVariable("id") Long id) {
    ArticleDTO article = articleService.getArticle(id);
    articleService.deleteArticle(id);
    return ResponseEntity.ok().body(article);
  }
}
