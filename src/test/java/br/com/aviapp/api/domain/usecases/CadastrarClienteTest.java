package br.com.aviapp.api.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import br.com.aviapp.api.domain.dto.ClienteDTO;
import br.com.aviapp.api.infra.jakarta.services.ClienteService;

public class CadastrarClienteTest {

  CadastrarCliente makeCadastrarCliente() {
    ClienteService service = new ClienteService();
    CadastrarCliente sut = new CadastrarCliente(service);

    return sut;
  }
  
  @Test
  void shouldAddClientToDatabase() {
    ClienteDTO dto = new ClienteDTO(1l, "Test", "teste@teste.com", "00000000000", "000000000");

    CadastrarCliente sut = this.makeCadastrarCliente();

    ClienteDTO response = sut.execute(dto);

    assertNotNull(response);
  }
}
