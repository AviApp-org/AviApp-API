package br.com.aviapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.application.mappers.AddressMapperBO;
import br.com.aviapp.api.application.usecases.address.CreateAddressUseCase;
import br.com.aviapp.api.application.usecases.address.DeleteAddressUseCase;
import br.com.aviapp.api.application.usecases.address.FindAddressByIdUseCase;
import br.com.aviapp.api.application.usecases.address.ListAddressesUseCase;
import br.com.aviapp.api.application.usecases.address.UpdateAddressUseCase;
import br.com.aviapp.api.infra.mappers.AddressMapperEntity;

@Configuration
public class AddressConfig {

    @Bean
    CreateAddressUseCase createAddress(AddressRepository addressRepository, AddressMapperBO mapperBO) {
        return new CreateAddressUseCase(addressRepository, mapperBO);
    }

    @Bean
    DeleteAddressUseCase deleteAddress(AddressRepository addressRepository) {
        return new DeleteAddressUseCase(addressRepository);
    }

    @Bean
    FindAddressByIdUseCase findAddress(AddressRepository addressRepository, AddressMapperBO mapperBO) {
        return new FindAddressByIdUseCase(addressRepository, mapperBO);
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
    AddressMapperEntity returnAddressEntityMapper() {
        return new AddressMapperEntity();
    }

    @Bean
    AddressMapperBO returnAddressMapperBO() {
        return new AddressMapperBO();
    }
}
