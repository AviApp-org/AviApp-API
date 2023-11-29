package br.com.aviapp.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.domain.dto.ClienteDTO;
import br.com.aviapp.api.domain.usecases.CadastrarCliente;
import br.com.aviapp.api.infra.jakarta.repository.JakartaClienteRepository;

@Service
public class ClienteService {

  @Autowired
  JakartaClienteRepository repository;

  public ClienteDTO cadastrarCliente(ClienteDTO dto) {
    CadastrarCliente cadastrar = new CadastrarCliente(repository);
    
    ClienteDTO response = cadastrar.execute(dto);

    return response;

  }
}
