package br.com.aviapp.api.infra.gateways;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.domain.entities.AddressBO;
import br.com.aviapp.api.infra.mappers.AddressMapper;
import br.com.aviapp.api.infra.mysql.models.MySqlAddressEntity;
import br.com.aviapp.api.infra.mysql.repository.AddressRepositoryJPA;

public class AddressRepositoryImpl implements AddressRepository{

    
    private final AddressRepositoryJPA repository;
    private final AddressMapper addressMapper;



    public AddressRepositoryImpl(AddressRepositoryJPA repository, AddressMapper addressMapper) {
        this.repository = repository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressBO createAdrress(AddressBO address) {
        MySqlAddressEntity entity = addressMapper.toEntity(address);
        repository.save(entity);

        return addressMapper.toBO(entity);
    }

    @Override
    public List<AddressBO> listAllAdresses() {
        return addressMapper.toBOList(repository.findAll());
    }

    @Override
    public Optional<AddressBO> findAddress(Long addressID) {

        return repository.findById(addressID).map(addressMapper::toBO);
    }

    @Override
    public void deleteAddress(Long addressID) {
        repository.deleteById(addressID);
    }

    @Override
    public Optional<AddressBO> updateAddress(Long addressID, AddressBO updatedAddressBO) {
        // Passo 1: Buscar a entidade existente no banco de dados
        Optional<MySqlAddressEntity> existingAddressOpt = repository.findById(addressID);

        if (existingAddressOpt.isEmpty()) {
            return Optional.empty();  // Caso o endereço não seja encontrado, retorna Optional.empty()
        }

        // Passo 2: Se a entidade existir, atualizar os dados
        MySqlAddressEntity existingAddress = existingAddressOpt.get();

        existingAddress.setStreet(updatedAddressBO.getStreet());
        existingAddress.setNumber(updatedAddressBO.getNumber());
        existingAddress.setCep(updatedAddressBO.getCep());
        existingAddress.setNeighborhood(updatedAddressBO.getNeighborhood());
        existingAddress.setCity(updatedAddressBO.getCity());
        existingAddress.setState(updatedAddressBO.getState());

        // Passo 3: Salvar a entidade atualizada de volta no banco de dados
        MySqlAddressEntity updatedEntity = repository.save(existingAddress);

        // Passo 4: Converter a entidade atualizada de volta para BO e retornar
        return Optional.of(addressMapper.toBO(updatedEntity));
    }


    
}
