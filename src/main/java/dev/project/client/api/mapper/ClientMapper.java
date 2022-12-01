package dev.project.client.api.mapper;

import dev.project.client.api.domain.Client;
import dev.project.client.api.dto.ClientCreateDto;
import dev.project.client.api.dto.ClientReadDto;
import dev.project.client.api.dto.ClientUpdateDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public Client toEntity(ClientCreateDto dto) {
        Client client = new Client();
        client.setName(dto.getName());
        client.setDocument(dto.getDocument());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setRegistrationDate(LocalDate.now());
        client.setAddresses(dto.getAddresses().stream().map(new AddressMapper()::toEntity).collect(Collectors.toSet()));
        return client;
    }

    public Client toUpdateEntity(Client client, ClientUpdateDto dto) {
        client.setName(dto.getName());
        client.setDocument(dto.getDocument());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        return client;
    }

    public ClientReadDto toReadDto(Client client) {
        ClientReadDto dto = new ClientReadDto();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setDocument(client.getDocument());
        dto.setEmail(client.getEmail());
        dto.setTelephone(client.getTelephone());
        dto.setRegistrationDate(client.getRegistrationDate());
        dto.setAddresses(client.getAddresses().stream().map(new AddressMapper()::toDto).collect(Collectors.toSet()));
        return dto;
    }

    public List<ClientReadDto> toListReadDto(List<Client> clients) {
        return clients.stream().map(this::toReadDto).collect(Collectors.toList());
    }
}
