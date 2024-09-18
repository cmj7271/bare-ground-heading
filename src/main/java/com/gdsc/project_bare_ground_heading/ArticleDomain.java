package com.gdsc.project_bare_ground_heading;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "articles")
public class ArticleDomain {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @Column(name = "title", nullable = false)
  @Setter
  @Getter
  private String title;

  @Column(name = "contents", nullable = false)
  @Setter
  @Getter
  private String contents;

  @Column(name = "created_at", nullable = false)
  @Setter
  @Getter
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = true)
  @Setter
  @Getter
  private LocalDateTime updatedAt;
}
