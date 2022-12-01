package dev.project.client.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateDto implements Serializable {

    @NotNull
    @Positive
    private Long id;

    @NotEmpty
    private String description;

    @NotEmpty
    private String addressName;

    @NotEmpty
    private String number;

    private String complement;

    @NotEmpty
    private String neighborhood;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

    @NotEmpty
    private String zipCode;

    @NotNull
    private Boolean isChargeAddress;

    @NotNull
    private Boolean isServiceAddress;

}
