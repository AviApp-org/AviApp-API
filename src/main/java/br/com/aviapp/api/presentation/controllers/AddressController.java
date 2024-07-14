package br.com.aviapp.api.presentation.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.domain.dto.AddressDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlAddressEntity;
import br.com.aviapp.api.services.AddressService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<AddressDTO> addresses = addressService.listAddresses();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {
        Optional<MySqlAddressEntity> addressEntityOptional = addressService.getaddressById(id);
        if (addressEntityOptional.isPresent()) {
            MySqlAddressEntity addressEntity = addressEntityOptional.get();
            AddressDTO addressDTO = addressService.toAddressDTO(addressEntity);
            return ResponseEntity.ok(addressDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        Long createdAddressId = addressService.save(addressDTO);
        URI location = URI.create("/api/addresses/" + createdAddressId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        AddressDTO updatedAddress = addressService.updateAddress(id, addressDTO);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
