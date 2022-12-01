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
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Transactional
    public ClientReadDto create(ClientCreateDto dto) {
        Client client = clientMapper.toEntity(dto);
        clientRepository.save(client);
        return clientMapper.toReadDto(client);
    }

    @Transactional
    public ClientReadDto update(ClientUpdateDto dto) {
        Client clientFound = clientRepository.findById(dto.getId())
                .orElseThrow(RuntimeException::new);

        Client client = clientMapper.toUpdateEntity(clientFound, dto);
        clientRepository.save(client);
        return clientMapper.toReadDto(client);
    }

    public List<ClientReadDto> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toListReadDto(clients);
    }

    public ClientReadDto findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        return clientMapper.toReadDto(client);
    }

    @Transactional
    public AddressReadDto addAddress(Long clientId, AddressCreateDto dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(RuntimeException::new);

        Address address = addressMapper.toEntity(dto);

        if(client.getAddresses().contains(address)){
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

        Address address = new Address();
        address.setId(addressId);

        var isRemoved = client.getAddresses().remove(address);
        if(!isRemoved){
            throw new RuntimeException();
        }
        clientRepository.save(client);
    }

    @Transactional
    public AddressReadDto updateAddress(Long clientId, Long addressId,
                                        AddressUpdateDto addressDto) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(RuntimeException::new);

        Optional<Address> addressFound = client.getAddresses()
                .stream()
                .filter(e -> e.getId().equals(addressId))
                .findFirst();

        if(addressFound.isEmpty()){
            throw new RuntimeException();
        }

        Address addressUpdated = addressMapper.toUpdateEntity(addressFound.get(), addressDto);
        clientRepository.save(client);
        return addressMapper.toDto(addressUpdated);
    }

    @Transactional
    public Set<AddressReadDto> addAllAddress(Long clientId, Set<AddressCreateDto> addressDtoSet) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(RuntimeException::new);

        Set<Address> addresses = addressMapper.toListEntity(addressDtoSet);
        client.addAllAddress(addresses);
        clientRepository.save(client);
        return addressMapper.toListDto(addresses);
    }
}
