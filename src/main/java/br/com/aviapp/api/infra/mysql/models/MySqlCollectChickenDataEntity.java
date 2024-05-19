package br.com.aviapp.api.infra.mysql.models;

import br.com.aviapp.api.infra.mysql.enums.ChickenDeathCause;
import br.com.aviapp.api.infra.mysql.enums.ChickenGender;
import br.com.aviapp.api.infra.mysql.enums.ChickenStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class MySqlCollectChickenDataEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "collect_id")
  private Long collectId;

  @Enumerated(EnumType.STRING)
  private ChickenGender gender;

  private Integer quantity;

  @Column(name = "death_cause")
  @Enumerated(EnumType.STRING)
  private ChickenDeathCause deathCause;

  @Enumerated(EnumType.STRING)
  private ChickenStatus status;

  private String observation;

}
