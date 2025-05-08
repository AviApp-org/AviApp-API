package br.com.aviapp.api.infra.mysql.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Data
@Entity
@Table(name = "collect_chicken_data")
public class MySqlCollectChickenDataEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "aviary_id")
  private MySqlAviaryEntity aviary;

  @Column(name = "deadRoosters")
  private Integer deadRoosters;

  @Column(name = "deadChickens")
  private Integer deadChickens;

  @CreatedDate
  @Column(nullable = false)
  private LocalDateTime collectionDate;

  private String observation;

}
