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
  private MySqlClientEntity clientId;

  @Column(nullable = false, length = 18, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  private LocalDateTime created_at;

//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    return List.of();
//  }
//
//  @Override
//  public String getPassword() {
//    return password;
//  }
//
//  @Override
//  public String getUsername() {
//    return username;
//  }
//
//  @Override
//  public boolean isAccountNonExpired() {
//    return false;
//  }
//
//  @Override
//  public boolean isAccountNonLocked() {
//    return false;
//  }
//
//  @Override
//  public boolean isCredentialsNonExpired() {
//    return false;
//  }
//
//  @Override
//  public boolean isEnabled() {
//    return false;
//  }
}
