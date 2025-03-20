package br.com.aviapp.api.infra.mysql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "aviary")
@NoArgsConstructor
@AllArgsConstructor
public class MySqlAviaryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Integer initialAmountOfRoosters;

  private Integer initialAmountOfChickens;

  @ManyToOne
  @JoinColumn(name = "batch_id")
  private MySqlBatchEntity batchId;


}
