package br.com.aviapp.api.infra.mysql.models;

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

  private String observation;

}
