package com.gdsc.project_bare_ground_heading;

import java.time.LocalDateTime;

public record ArticleDTO(
    Long id, String title, String content, LocalDateTime createdAt, LocalDateTime updateAt) {}
