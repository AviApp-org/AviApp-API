package br.com.aviapp.api.application.gateways;

import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.domain.entities.AddressBO;

public interface AddressRepository {
    
    AddressBO createAdrress (AddressBO address);

    List<AddressBO> listAllAdresses ();

    Optional<AddressBO> findAddress (Long addressID);

    void deleteAddress (Long addressID);

    Optional<AddressBO> updateAddress (Long addressID, AddressBO updatedAddressBO);

}
