package br.com.aviapp.api.infra.mysql.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "collect")
public class MySqlCollectEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "aviary_id")
  private MySqlAviaryEntity aviary;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private MySqlEmployeeEntity employee;

  @CreatedDate
  @Column(name = "timestamp", nullable = false, updatable = false)
  private LocalDateTime timestamp;
}
