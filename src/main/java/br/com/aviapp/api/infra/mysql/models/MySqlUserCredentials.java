package br.com.aviapp.api.infra.mysql.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "user_credentials")
@Entity
@Data
public class MySqlUserCredentials {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "client_id")
  private MySqlClientEntity client;

  @Column(nullable = false, unique = true)
  private String login;

  @Column(nullable = false)
  private String password;

  private LocalDateTime created_at;

}
