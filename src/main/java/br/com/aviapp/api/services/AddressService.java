package br.com.aviapp.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aviapp.api.domain.dto.AddressDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlAddressEntity;
import br.com.aviapp.api.infra.mysql.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<MySqlAddressEntity> findAll() {
        return addressRepository.findAll();
    }

    public AddressDTO findById(Long id) {
        Optional<MySqlAddressEntity> addressEntityOptional = addressRepository.findById(id);
        if (addressEntityOptional.isPresent()) {
            MySqlAddressEntity addressEntity = addressEntityOptional.get();
            return toAddressDTO(addressEntity);
        } else {
            return null; // ou lançar uma exceção personalizada
        }
    }

    public Long save(AddressDTO addressDTO) {

        var entity = new MySqlAddressEntity(
                null,
                addressDTO.getStreet(),
                addressDTO.getNumber(),
                addressDTO.getCep(),
                addressDTO.getNeighborhood(),
                addressDTO.getCity(),
                addressDTO.getState());

        var addressSalvo = addressRepository.save(entity);

        return addressSalvo.getId();
    }

    public AddressDTO toAddressDTO(MySqlAddressEntity mySqlAddressEntity) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(mySqlAddressEntity.getStreet());
        addressDTO.setNumber(mySqlAddressEntity.getNumber());
        addressDTO.setCep(mySqlAddressEntity.getCep());
        addressDTO.setNeighborhood(mySqlAddressEntity.getNeighborhood());
        addressDTO.setCity(mySqlAddressEntity.getCity());
        addressDTO.setState(mySqlAddressEntity.getState());
        // Mapear outros campos, se necessário
        return addressDTO;
    }

    public void deleteAddress(Long id) {
        var addressExiste = addressRepository.existsById(id);

        if (addressExiste) {
            addressRepository.deleteById(id);
        }
    }

    public List<AddressDTO> listAddresses() {
        List<MySqlAddressEntity> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(this::toAddressDTO)
                .collect(Collectors.toList());
    }

    public Optional<MySqlAddressEntity> getaddressById(Long id) {
        var address = addressRepository.findById(id);
        return address;
    }

    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        Optional<MySqlAddressEntity> optionalAddress = addressRepository.findById(id);

        if (optionalAddress.isPresent()) {
            MySqlAddressEntity existingAddress = optionalAddress.get();

            existingAddress.setStreet(addressDTO.getStreet());
            existingAddress.setNumber(addressDTO.getNumber());
            existingAddress.setCep(addressDTO.getCep());
            existingAddress.setNeighborhood(addressDTO.getNeighborhood());
            existingAddress.setCity(addressDTO.getCity());
            existingAddress.setState(addressDTO.getState());

            MySqlAddressEntity updatedAddress = addressRepository.save(existingAddress);

            return toAddressDTO(updatedAddress);
        } else {
            throw new RuntimeException("Endereço não encontrado com o ID: " + id);
        }
    }

}
