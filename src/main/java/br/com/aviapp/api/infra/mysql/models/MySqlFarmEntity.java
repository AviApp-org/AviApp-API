package br.com.aviapp.api.infra.mysql.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "farm")
@AllArgsConstructor
@NoArgsConstructor
public class MySqlFarmEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToOne
  @JoinColumn(name = "address_id", unique = true)
  private MySqlAddressEntity address;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private MySqlClientEntity clientId;

  @OneToMany
  @JoinColumn(name = "employee_id")
  private List<MySqlEmployeeEntity> employeeId;
}
