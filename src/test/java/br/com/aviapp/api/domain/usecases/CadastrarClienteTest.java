package br.com.aviapp.api.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.infra.mysql.repository.IClienteDatabaseRepositoryJPA;

public class CadastrarClienteTest {
/* 
  @Mock
  private IClienteDatabaseRepository repository;

  @Spy
  @InjectMocks
  private CadastrarCliente sut;

  @BeforeEach
  void init() {
    sut = new CadastrarCliente(repository);
  }

  @AfterEach
  void config() {
    Mockito.clearAllCaches();
  }

  @Disabled
  void shouldAddClienteToDatabase() {
    ClientDTO dto = new ClientDTO(null, "Test", "teste@teste.com", "00000000000", "000000000");
    when(sut.execute(any(ClientDTO.class))).thenReturn(dto);
    ClientDTO response = sut.execute(dto);

    assertNotNull(response.getId());
  }
    */
}
