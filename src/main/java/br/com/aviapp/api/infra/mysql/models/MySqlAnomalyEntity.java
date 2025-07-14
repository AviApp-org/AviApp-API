package br.com.aviapp.api.infra.mysql.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "anomaly")
@NoArgsConstructor
@AllArgsConstructor
public class MySqlAnomalyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Lob
  @Column(nullable = true)
  private String description;

  @ManyToOne
  @JoinColumn(name = "aviary_id")
  private MySqlAviaryEntity aviary;
}
