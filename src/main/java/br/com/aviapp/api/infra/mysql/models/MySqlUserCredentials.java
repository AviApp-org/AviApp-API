package br.com.aviapp.api.infra.mysql.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "user_credentials")
@Entity
public class MySqlUserCredentials {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "client_id")
  private MySqlClientEntity clientId;

  @Column(nullable = false, length = 18, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  private LocalDateTime created_at;
}
