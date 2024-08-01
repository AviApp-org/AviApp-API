package br.com.aviapp.api.domain.repositories;

public interface IClientRepository<T> {
  public T findByCpf(String cpf);
}
