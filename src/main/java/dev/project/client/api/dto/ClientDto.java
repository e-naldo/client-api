package dev.project.client.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ClientDto extends AbstractDto {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 150)
    private String name;

    @Size(min = 14, max = 14)
    @CNPJ
    private String document;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telephone;

    private Set<AddressDto> addresses;
}
