package br.com.aviapp.api.infra.mysql.models;

import br.com.aviapp.api.infra.mysql.enums.EggType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "collect_egg_data")
@Entity
public class MySqlCollectEggDataEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "aviary_id", nullable = false)
  private MySqlAviaryEntity aviary;

  @Column(nullable = false)
  private LocalDateTime collectionDate;

  @OneToMany(mappedBy = "eggCollection", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MySqlEggDetailEntity> eggDetails = new ArrayList<>();
}
