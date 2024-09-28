package com.gdsc.project_bare_ground_heading;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleJPARepository {
  private EntityManager em;

  public ArticleJPARepository(EntityManager em) {
    this.em = em;
  }

  public ArticleDomain save(ArticleDomain article) {
    em.persist(article);
    return em.find(ArticleDomain.class, article.getId());
  }

  public Optional<ArticleDomain> findById(Long id) {
    return Optional.ofNullable(em.find(ArticleDomain.class, id));
  }

  public List<ArticleDomain> findAll() {
    return em.createQuery("select m from articles m", ArticleDomain.class).getResultList();
  }

  public void delete(ArticleDomain article) {
    em.remove(em.merge(article));
  }
}
