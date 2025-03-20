package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.dto.CepResponseDTO;
import br.com.aviapp.api.application.usecases.address.*;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/addresses")
public class AddressController {
    private final CreateAddressUseCase createAddress;
    private final DeleteAddressUseCase deleteAddress;
    private final FindAddressByIdUseCase findAddressById;
    private final ListAddressesUseCase findAllAddresses;
    private final UpdateAddressUseCase updateAddress;
    private final ConsultCepUseCase consultCep;

    public AddressController(
            CreateAddressUseCase createAddress,
            DeleteAddressUseCase deleteAddress,
            FindAddressByIdUseCase findAddressById,
            ListAddressesUseCase findAllAddresses,
            UpdateAddressUseCase updateAddress, ConsultCepUseCase consultCep) {
        this.createAddress = createAddress;
        this.deleteAddress = deleteAddress;
        this.findAddressById = findAddressById;
        this.findAllAddresses = findAllAddresses;
        this.updateAddress = updateAddress;
        this.consultCep = consultCep;
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<AddressDTO> addresses = findAllAddresses.invoke();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Optional<AddressDTO> address = findAddressById.invoke(id);
        return address.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        AddressDTO newAddress = createAddress.invoke(addressDTO);
        URI location = URI.create("/api/addresses/" + newAddress.id());
        return ResponseEntity.created(location).body(newAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO) {
        Optional<AddressDTO> updatedAddress = updateAddress.invoke(id, addressDTO);
        return updatedAddress.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        deleteAddress.invoke(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<CepResponseDTO> consultCep(@PathVariable String cep){
        CepResponseDTO cepResponse = consultCep.invoke(cep);
        return ResponseEntity.ok(cepResponse);
    }

}
