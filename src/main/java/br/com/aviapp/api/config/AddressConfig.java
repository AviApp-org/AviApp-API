package br.com.aviapp.api.config;

import br.com.aviapp.api.application.usecases.address.*;
import br.com.aviapp.api.domain.services.AddressService;
import br.com.aviapp.api.domain.utils.CepService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aviapp.api.application.gateways.IAddress;
import br.com.aviapp.api.application.mappers.AddressMapperBO;
import br.com.aviapp.api.infra.mappers.AddressMapperEntity;

@Configuration
public class AddressConfig {

    @Bean
    CreateAddressUseCase createAddress(IAddress IAddress, AddressMapperBO mapperBO) {
        return new CreateAddressUseCase(IAddress, mapperBO);
    }

    @Bean
    ConsultCepUseCase consultCep(AddressService addressService) {
        return new ConsultCepUseCase(addressService);
    }

    @Bean
    AddressService addressService(CepService cepService) {
        return new AddressService(cepService);
    }


    @Bean
    DeleteAddressUseCase deleteAddress(IAddress IAddress) {
        return new DeleteAddressUseCase(IAddress);
    }

    @Bean
    FindAddressByIdUseCase findAddress(IAddress IAddress, AddressMapperBO mapperBO) {
        return new FindAddressByIdUseCase(IAddress, mapperBO);
    }

    @Bean
    ListAddressesUseCase listAllAdresses(IAddress IAddress) {
        return new ListAddressesUseCase(IAddress);
    }

    @Bean
    UpdateAddressUseCase updateAddress(IAddress IAddress) {
        return new UpdateAddressUseCase(IAddress);
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
