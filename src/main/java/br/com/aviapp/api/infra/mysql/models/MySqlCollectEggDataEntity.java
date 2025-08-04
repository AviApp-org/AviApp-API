package br.com.aviapp.api.infra.mysql.models;

import br.com.aviapp.api.infra.mysql.enums.EggType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
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

  @CreatedDate
  @Column(nullable = false)
  private LocalDateTime collectionDate;

  @OneToMany(mappedBy = "eggCollection", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MySqlEggDetailEntity> eggDetails = new ArrayList<>();

  // Métodos auxiliares para gerenciar o relacionamento bidirecional
  public void addEggDetail(MySqlEggDetailEntity detail) {
    eggDetails.add(detail);
    detail.setEggCollection(this);
  }

  public void removeEggDetail(MySqlEggDetailEntity detail) {
    eggDetails.remove(detail);
    detail.setEggCollection(null);
  }
}
