package br.com.surb.surb.modules.movie.infra.jpa.entities;

import br.com.surb.surb.modules.score.infra.entities.Score;
import br.com.surb.surb.shared.enums.TypeStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_movie")
public class Movie implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private Double score;
  private Integer count;
  private String image;

  @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private Instant createdAt;

  @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private Instant updatedAt;

  private TypeStatus status;

  @OneToMany(mappedBy = "id.movie")
  private final Set<Score> scores = new HashSet<>();

  public Movie(){}

  public Movie(Long id, String title, Double score, Integer count, String image, Instant createdAt, Instant updatedAt
    , TypeStatus status) {
    this.id = id;
    this.title = title;
    this.score = score;
    this.count = count;
    this.image = image;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public TypeStatus getStatus() {
    return status;
  }

  public void setStatus(TypeStatus status) {
    this.status = status;
  }

  public Set<Score> getScores() {
    return scores;
  }

  @PrePersist
  public void prePersist(){
    createdAt = Instant.now();
    status = TypeStatus.ENABLED;
  }

  @PreUpdate
  public void preUpdate(){
    updatedAt = Instant.now();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Movie movie = (Movie) o;
    return id.equals(movie.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
