package dev.project.client.api.service;

import dev.project.client.api.domain.Address;
import dev.project.client.api.domain.Client;
import dev.project.client.api.dto.*;
import dev.project.client.api.mapper.AddressMapper;
import dev.project.client.api.mapper.ClientMapper;
import dev.project.client.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientService implements ServiceContract<ClientDetailDto, ClientDto> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Transactional
    public ClientDetailDto create(ClientDto dto) {
        Client client = clientMapper.toEntity(dto);
        clientRepository.save(client);
        return clientMapper.toReadDto(client);
    }

    @Transactional
    public ClientDetailDto update(ClientDto dto) {
        Client clientFound = clientRepository.findById(dto.getId())
                .orElseThrow(RuntimeException::new);

        Client client = clientMapper.toUpdateEntity(clientFound, dto);
        clientRepository.save(client);
        return clientMapper.toReadDto(client);
    }

    public List<ClientDetailDto> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toListReadDto(clients);
    }

    public ClientDetailDto findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        return clientMapper.toReadDto(client);
    }

    @Transactional
    public AddressDetailDto addAddress(Long clientId, AddressDto dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(RuntimeException::new);

        Address address = addressMapper.toEntity(dto);

        if (client.getAddresses().contains(address)) {
            throw new RuntimeException();
        }
        client.addAddress(address);

        clientRepository.save(client);
        return addressMapper.toDto(address);
    }

    @Transactional
    public void removeAddress(Long clientId, Long addressId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(RuntimeException::new);

        Address address = client.findAddressById(addressId);

        if (address == null) {
            throw new RuntimeException();
        }

        client.removeAddress(address);

        clientRepository.save(client);
    }

    @Transactional
    public AddressDetailDto updateAddress(Long clientId, Long addressId,
                                          AddressDto addressDto) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(RuntimeException::new);

        Optional<Address> addressFound = client.getAddresses()
                .stream()
                .filter(e -> e.getId().equals(addressId))
                .findFirst();

        if (addressFound.isEmpty()) {
            throw new RuntimeException();
        }

        Address addressUpdated = addressMapper.toUpdateEntity(addressFound.get(), addressDto);
        clientRepository.save(client);
        return addressMapper.toDto(addressUpdated);
    }

    @Transactional
    public Set<AddressDetailDto> addAllAddress(Long clientId, Set<AddressDto> addressDtoSet) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(RuntimeException::new);

        Set<Address> addresses = addressMapper.toListEntity(addressDtoSet);
        client.addAllAddress(addresses);
        clientRepository.save(client);
        return addressMapper.toListDto(addresses);
    }

}
