package dev.project.client.api.repository;

import dev.project.client.api.domain.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @EntityGraph(attributePaths = "addresses")
    Optional<Client> findById(Long id);

    @EntityGraph(attributePaths = "addresses")
    List<Client> findAll();
}
