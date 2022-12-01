package dev.project.client.api.domain;

import jakarta.persistence.*;
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
@Entity
@Table(name = "client")
public class Client extends BaseEntity {

    private String name;
    private String document;
    private String email;
    private String telephone;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    public void addAddress(Address address) {
        this.addresses.add(address);
        address.setClient(this);
    }

    public void addAllAddress(Set<Address> addresses) {
        addresses.forEach(e -> e.setClient(this));
        this.addresses.addAll(addresses);
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
        addresses.forEach(e -> e.setClient(this));
    }
}
