package com.gdsc.project_bare_ground_heading;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ArticleJPARepository {
  private EntityManager em;

  public ArticleJPARepository(EntityManager em) {
    this.em = em;
  }

  @Transactional
  public ArticleDomain save(ArticleDomain article) {
    em.persist(article);
    return article;
  }

  @Transactional(readOnly = true)
  public Optional<ArticleDomain> findById(Long id) {
    return Optional.ofNullable(em.find(ArticleDomain.class, id));
  }

  @Transactional(readOnly = true)
  public List<ArticleDomain> findAll() {
    return em.createQuery("select m from articles m", ArticleDomain.class).getResultList();
  }

  @Transactional
  public void delete(ArticleDomain article) {
    em.remove(em.merge(article));
  }
}
