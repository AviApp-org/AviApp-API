package br.com.aviapp.api.infra.mysql.models;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import br.com.aviapp.api.infra.mysql.enums.EmployeeRole;
import jakarta.persistence.Column;
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

@Data
@Entity
@Table(name = "employee")
public class MySqlEmployeeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String cpf;

  private String phone;

  @Enumerated(EnumType.STRING)
  private EmployeeRole role;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private Date createdAt;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private MySqlFarmEntity farmId;
}
