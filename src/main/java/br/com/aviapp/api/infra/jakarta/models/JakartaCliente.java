package br.com.aviapp.api.infra.jakarta.models;

import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class JakartaCliente {
  @Id
  private Long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "email")
  private String email;

  @Column(name = "cnpj")
  private String cnpj;

  @Column(name = "telefone")
  private String telefone;

  @Column(name = "status")
  private EnumStatusCliente status;
}
