package dev.project.client.api.mapper;

import dev.project.client.api.domain.Address;
import dev.project.client.api.dto.AddressDetailDto;
import dev.project.client.api.dto.AddressDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    public Address toEntity(AddressDto dto) {
        Address address = new Address();
        address.setDescription(dto.getDescription());
        address.setAddressName(dto.getAddressName());
        address.setNumber(dto.getNumber());
        address.setComplement(dto.getComplement());
        address.setNeighborhood(dto.getNeighborhood());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZipCode(dto.getZipCode());
        address.setIsChargeAddress(dto.getIsChargeAddress());
        address.setIsServiceAddress(dto.getIsServiceAddress());
        return address;
    }

    public Address toUpdateEntity(Address address, AddressDto dto) {
        address.setDescription(dto.getDescription());
        address.setAddressName(dto.getAddressName());
        address.setNumber(dto.getNumber());
        address.setComplement(dto.getComplement());
        address.setNeighborhood(dto.getNeighborhood());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZipCode(dto.getZipCode());
        address.setIsChargeAddress(dto.getIsChargeAddress());
        address.setIsServiceAddress(dto.getIsServiceAddress());
        return address;
    }

    public AddressDetailDto toDto(Address address) {
        AddressDetailDto dto = new AddressDetailDto();
        dto.setId(address.getId());
        dto.setDescription(address.getDescription());
        dto.setAddressName(address.getAddressName());
        dto.setNumber(address.getNumber());
        dto.setComplement(address.getComplement());
        dto.setNeighborhood(address.getNeighborhood());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setZipCode(address.getZipCode());
        dto.setIsChargeAddress(address.getIsChargeAddress());
        dto.setIsServiceAddress(address.getIsServiceAddress());
        return dto;
    }

    public Set<Address> toListEntity(Set<AddressDto> addressDtoSet) {
        return addressDtoSet.stream().map(this::toEntity).collect(Collectors.toSet());
    }

    public Set<AddressDetailDto> toListDto(Set<Address> addressSet) {
        return addressSet.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
