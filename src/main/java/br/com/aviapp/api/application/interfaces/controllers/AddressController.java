package br.com.aviapp.api.application.interfaces.controllers;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.mappers.AddressMapper;
import br.com.aviapp.api.application.usecases.address.CreateAddressUseCase;
import br.com.aviapp.api.application.usecases.address.DeleteAddressUseCase;
import br.com.aviapp.api.application.usecases.address.FindAddressByUseCase;
import br.com.aviapp.api.application.usecases.address.ListAddressesUseCase;
import br.com.aviapp.api.application.usecases.address.UpdateAddressUseCase;
import br.com.aviapp.api.domain.entities.AddressBO;
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
    private final FindAddressByUseCase findAddressById;
    private final ListAddressesUseCase findAllAdresses;
    private final UpdateAddressUseCase updateAddress;

    public AddressController(
        CreateAddressUseCase createAddress,
        DeleteAddressUseCase deleteAddress,
        FindAddressByUseCase findAddressById,
        ListAddressesUseCase findAllAdresses,
        UpdateAddressUseCase updateAddress
    ) {
        this.createAddress = createAddress;
        this.deleteAddress = deleteAddress;
        this.findAddressById = findAddressById;
        this.findAllAdresses = findAllAdresses;
        this.updateAddress = updateAddress;
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<AddressDTO> addresses = AddressMapper.toDTOList(findAllAdresses.invoke());
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Optional<AddressBO> addressBO = findAddressById.invoke(id);

        return addressBO.map(AddressMapper::toDTO)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        AddressBO newAddress = createAddress.invoke(AddressMapper.toBO(addressDTO));
        AddressDTO responseDTO = AddressMapper.toDTO(newAddress);

        URI location = URI.create("/api/addresses/" + newAddress.getId());
        return ResponseEntity.created(location).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO) {
        Optional<AddressBO> updatedAddressBO = updateAddress.invoke(id, AddressMapper.toBO(addressDTO));

        return updatedAddressBO.map(AddressMapper::toDTO)
                               .map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        deleteAddress.invoke(id);
        return ResponseEntity.noContent().build();
    }
}
