package br.com.aviapp.api.infra.mysql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class MySqlReviewCollect {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "collect_id")
  private MySqlCollectEntity collectId;

  @OneToOne
  @JoinColumn(name = "employee_id")
  private MySqlEmployeeEntity reviwedBy;

}
