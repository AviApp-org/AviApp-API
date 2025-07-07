package br.com.aviapp.api.application.gateways;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.dto.AddressDTO;

public interface IAddress {

    AddressDTO createAddress(AddressDTO address);

    List<AddressDTO> listAllAddresses();

    Optional<AddressDTO> findAddress(Long addressID);

    void deleteAddress(Long addressID);

    Optional<AddressDTO> updateAddress(Long addressID, AddressDTO updatedAddress);
}
