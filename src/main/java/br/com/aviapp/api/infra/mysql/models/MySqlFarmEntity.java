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
  @JoinColumn(name = "client_id")
  private MySqlClientEntity clientId;

  @OneToMany
  @JoinColumn(name = "employee_id")
  private List<MySqlEmployeeEntity> employeeId;

  @Column(name = "street")
  private String street;

  @Column(name = "number")
  private String number;

  @Column(name = "cep")
  private String cep;

  @Column(name = "neighborhood")
  private String neighborhood;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

}
