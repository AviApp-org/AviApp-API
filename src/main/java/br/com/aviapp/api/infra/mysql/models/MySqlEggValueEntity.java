package br.com.aviapp.api.infra.mysql.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.aviapp.api.infra.mysql.enums.EggType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "egg_value")
public class MySqlEggValueEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private EggType egg;

  private LocalDateTime timestamp;

  private BigDecimal value;
}
