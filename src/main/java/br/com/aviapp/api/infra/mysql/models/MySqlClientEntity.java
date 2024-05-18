package br.com.aviapp.api.infra.mysql.models;

import br.com.aviapp.api.infra.mysql.enums.ClientStatusType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class MySqlClientEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, length = 11)
  private String cpf;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String telefone;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ClientStatusType status;
}
