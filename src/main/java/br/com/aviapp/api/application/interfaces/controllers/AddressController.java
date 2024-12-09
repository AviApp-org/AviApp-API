package br.com.aviapp.api.application.interfaces.controllers;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.mappers.AddressMapper;
import br.com.aviapp.api.application.usecases.Address.CreateAddress;
import br.com.aviapp.api.application.usecases.Address.DeleteAddress;
import br.com.aviapp.api.application.usecases.Address.FindAddressById;
import br.com.aviapp.api.application.usecases.Address.FindAllAdresses;
import br.com.aviapp.api.application.usecases.Address.UpdateAddress;
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

    private final CreateAddress createAddress;
    private final DeleteAddress deleteAddress;
    private final FindAddressById findAddressById;
    private final FindAllAdresses findAllAdresses;
    private final UpdateAddress updateAddress;

    public AddressController(
        CreateAddress createAddress,
        DeleteAddress deleteAddress,
        FindAddressById findAddressById,
        FindAllAdresses findAllAdresses,
        UpdateAddress updateAddress
    ) {
        this.createAddress = createAddress;
        this.deleteAddress = deleteAddress;
        this.findAddressById = findAddressById;
        this.findAllAdresses = findAllAdresses;
        this.updateAddress = updateAddress;
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<AddressDTO> addresses = AddressMapper.toDTOList(findAllAdresses.listarEnderecos());
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Optional<AddressBO> addressBO = findAddressById.procurarEnderecoPorID(id);

        return addressBO.map(AddressMapper::toDTO)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        AddressBO newAddress = createAddress.cadastrarAddressBO(AddressMapper.toBO(addressDTO));
        AddressDTO responseDTO = AddressMapper.toDTO(newAddress);

        URI location = URI.create("/api/addresses/" + newAddress.getId());
        return ResponseEntity.created(location).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO) {
        Optional<AddressBO> updatedAddressBO = updateAddress.atualizarEndereco(id, AddressMapper.toBO(addressDTO));

        return updatedAddressBO.map(AddressMapper::toDTO)
                               .map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        deleteAddress.deletarAddressBO(id);
        return ResponseEntity.noContent().build();
    }
}
