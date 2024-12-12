package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.application.usecases.address.CreateAddressUseCase;
import br.com.aviapp.api.application.usecases.address.DeleteAddressUseCase;
import br.com.aviapp.api.application.usecases.address.FindAddressByUseCase;
import br.com.aviapp.api.application.usecases.address.ListAddressesUseCase;
import br.com.aviapp.api.application.usecases.address.UpdateAddressUseCase;
import br.com.aviapp.api.infra.gateways.AddressRepositoryImpl;
import br.com.aviapp.api.infra.mappers.AddressMapper;
import br.com.aviapp.api.infra.mysql.repository.AddressRepositoryJPA;

@Configuration
public class AddressConfig {

    @Bean
    CreateAddressUseCase createAddress(AddressRepository addressRepository) {
        return new CreateAddressUseCase(addressRepository);
    }

    @Bean
    DeleteAddressUseCase deleteAddress(AddressRepository addressRepository) {
        return new DeleteAddressUseCase(addressRepository);
    }

    @Bean
    FindAddressByUseCase findAddress(AddressRepository addressRepository) {
        return new FindAddressByUseCase(addressRepository);
    }

    @Bean
    ListAddressesUseCase listAllAdresses(AddressRepository addressRepository) {
        return new ListAddressesUseCase(addressRepository);
    }

    @Bean
    UpdateAddressUseCase updateAddress(AddressRepository addressRepository) {
        return new UpdateAddressUseCase(addressRepository);
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
