package br.com.aviapp.api.infra.mysql.models;

import br.com.aviapp.api.infra.mysql.enums.EggType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "collect_egg_data")
@Entity
public class MySqlCollectEggDataEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "collect_id")
  private MySqlCollectEntity collect;


  private EggType egg;

  private Integer quantity;
}
