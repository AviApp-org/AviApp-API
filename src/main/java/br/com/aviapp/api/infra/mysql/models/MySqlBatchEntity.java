package br.com.aviapp.api.infra.mysql.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

import br.com.aviapp.api.infra.mysql.enums.BatchStatusType;

@Data
@Entity
@Table(name = "batch")
public class MySqlBatchEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Date startDate;

  @Enumerated(EnumType.STRING)
  private BatchStatusType status;

  @ManyToOne
  @JoinColumn(name = "farm_id", nullable = false)
  private MySqlFarmEntity farmId;

  @OneToMany(mappedBy = "batchId", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MySqlAviaryEntity> aviaries;
}
