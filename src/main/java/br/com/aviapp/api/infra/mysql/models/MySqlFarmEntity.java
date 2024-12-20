package br.com.aviapp.api.infra.mysql.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

  @ManyToOne
  @JoinColumn(name = "address_id")
  private MySqlAddressEntity addressId;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private MySqlClientEntity clientId;

  @OneToMany
  @JoinColumn(name = "employee_id")
  private List<MySqlEmployeeEntity> employeeId;
}
