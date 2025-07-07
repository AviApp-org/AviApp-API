package br.com.aviapp.api.infra.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.infra.mappers.AddressMapperEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlAddressEntity;
import br.com.aviapp.api.infra.mysql.repository.AddressRepositoryJPA;

@Repository
public class IAddress implements br.com.aviapp.api.application.gateways.IAddress {

    private final AddressRepositoryJPA repositoryJPA;
    private final AddressMapperEntity mapperEntity;

    public IAddress(AddressRepositoryJPA repositoryJPA, AddressMapperEntity mapperEntity) {
        this.repositoryJPA = repositoryJPA;
        this.mapperEntity = mapperEntity;
    }

    @Override
    public AddressDTO createAddress(AddressDTO address) {
        MySqlAddressEntity entity = mapperEntity.toEntity(address);
        MySqlAddressEntity savedEntity = repositoryJPA.save(entity);
        return mapperEntity.toDTO(savedEntity);
    }

    @Override
    public List<AddressDTO> listAllAddresses() {
        return mapperEntity.toDTOList(repositoryJPA.findAll());
    }

    @Override
    public Optional<AddressDTO> findAddress(Long addressID) {
        return repositoryJPA.findById(addressID)
                .map(mapperEntity::toDTO);
    }

    @Override
    public void deleteAddress(Long addressID) {
        repositoryJPA.deleteById(addressID);
    }

    @Override
    public Optional<AddressDTO> updateAddress(Long addressID, AddressDTO updatedAddressDTO) {
        return repositoryJPA.findById(addressID)
                .map(existingAddress -> {
                    if (updatedAddressDTO.street() != null) {
                        existingAddress.setStreet(updatedAddressDTO.street());
                    }
                    if (updatedAddressDTO.number() != null) {
                        existingAddress.setNumber(updatedAddressDTO.number());
                    }
                    if (updatedAddressDTO.cep() != null) {
                        existingAddress.setCep(updatedAddressDTO.cep());
                    }
                    if (updatedAddressDTO.neighborhood() != null) {
                        existingAddress.setNeighborhood(updatedAddressDTO.neighborhood());
                    }
                    if (updatedAddressDTO.city() != null) {
                        existingAddress.setCity(updatedAddressDTO.city());
                    }
                    if (updatedAddressDTO.state() != null) {
                        existingAddress.setState(updatedAddressDTO.state());
                    }

                    MySqlAddressEntity savedEntity = repositoryJPA.save(existingAddress);
                    return mapperEntity.toDTO(savedEntity);
                });
    }

}
