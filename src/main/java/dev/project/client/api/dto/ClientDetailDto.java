package dev.project.client.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetailDto extends AbstractDto {

    private  Long id;
    private String name;
    private String document;
    private String email;
    private String telephone;
    private LocalDate registrationDate;
    private Set<AddressDetailDto> addresses = new HashSet<>();

}
