package br.com.aviapp.api.infra.postgresql.entities;

import br.com.aviapp.api.domain.enums.ClientStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
public class PgSqlClientEntity {
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
  private String phone;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ClientStatusEnum status;

  @Override
  public boolean equals(Object o) {
    PgSqlClientEntity that = (PgSqlClientEntity) o;
    if (!(o instanceof PgSqlClientEntity)) return false;
    return this.id.equals(that.id) || this.cpf.equals(that.cpf);
  }
}
