package dev.project.client.api.controller;

import dev.project.client.api.dto.*;
import dev.project.client.api.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    public ResponseEntity<ClientDetailDto> create(@RequestBody @Valid ClientDto dto,
                                                  UriComponentsBuilder uriComponentsBuilder) {
        ClientDetailDto clientReadDto = service.create(dto);
        URI uri = uriComponentsBuilder.path("/api/v1/clients/{id}").buildAndExpand((clientReadDto.getId())).toUri();
        return ResponseEntity.created(uri).body(clientReadDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDetailDto> update(@PathVariable Long id,
                                                  @RequestBody @Valid ClientDto dto) {
        if (!id.equals(dto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        ClientDetailDto clientReadDto = service.update(dto);
        return ResponseEntity.ok(clientReadDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientDetailDto>> getAll() {
        List<ClientDetailDto> clientReadDtoList = service.findAll();
        return ResponseEntity.ok(clientReadDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDetailDto> getById(@PathVariable Long id) {
        ClientDetailDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{clientId}/addresses")
    public ResponseEntity<AddressDetailDto> addAddress(@PathVariable Long clientId,
                                                       @RequestBody AddressDto dto
    ) {

        AddressDetailDto addressDto = service.addAddress(clientId, dto);
        return ResponseEntity.ok(addressDto);
    }

    @DeleteMapping("/{clientId}/addresses/{addressId}")
    public ResponseEntity<?> removeAddress(@PathVariable Long clientId,
                                           @PathVariable Long addressId) {
        service.removeAddress(clientId, addressId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{clientId}/addresses/{addressId}")
    public ResponseEntity<AddressDetailDto> updateAddress(@PathVariable Long clientId,
                                                          @PathVariable Long addressId,
                                                          @RequestBody AddressDto addressDto) {

        AddressDetailDto addressDto2 = service.updateAddress(clientId, addressId, addressDto);
        return ResponseEntity.ok(addressDto2);
    }

    @PostMapping("/{clientId}/addresses/addAll")
    public ResponseEntity<Set<AddressDetailDto>> addAllAddress(@PathVariable Long clientId,
                                                               @RequestBody Set<AddressDto> addressCreateDtoSet) {
        Set<AddressDetailDto> addressReadDtoSet = service.addAllAddress(clientId, addressCreateDtoSet);
        return ResponseEntity.ok(addressReadDtoSet);
    }
}
