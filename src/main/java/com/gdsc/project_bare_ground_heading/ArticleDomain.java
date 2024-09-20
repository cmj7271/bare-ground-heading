package com.gdsc.project_bare_ground_heading;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity(name = "articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ArticleDomain {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "contents", nullable = false)
  private String contents;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = true)
  private LocalDateTime updatedAt;

  @Builder(access = AccessLevel.PROTECTED)
  private ArticleDomain(
      Long id, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.title = title;
    this.contents = contents;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public void updateArticleContents(String contents) {
    this.contents = contents;
  }

  public void updateArticleTitle(String title) {
    this.title = title;
  }
}
