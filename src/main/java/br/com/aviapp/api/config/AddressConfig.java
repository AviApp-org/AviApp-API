package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.application.usecases.Address.CreateAddress;
import br.com.aviapp.api.application.usecases.Address.FindAllAdresses;
import br.com.aviapp.api.infra.gateways.AddressRepositoryImpl;
import br.com.aviapp.api.infra.mappers.AddressMapper;
import br.com.aviapp.api.infra.mysql.repository.AddressRepositoryJPA;

@Configuration
public class AddressConfig {

    @Bean
    CreateAddress createAddress(AddressRepository addressRepository) {
        return new CreateAddress(addressRepository);
    }

    @Bean
    FindAllAdresses findAllAdresses(AddressRepository addressRepository) {
        return new FindAllAdresses(addressRepository);
    }

    @Bean
    AddressRepositoryImpl saveAddressJpa(AddressRepositoryJPA addressRepositoryJpa, AddressMapper addressMapper) {
        return new AddressRepositoryImpl(addressRepositoryJpa, addressMapper);

    }

    @Bean
    AddressMapper returnMapper() {
        return new AddressMapper();
    }
}
