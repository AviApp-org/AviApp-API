package br.com.aviapp.api.infra.mysql.models;

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

import java.util.Date;

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
}
