package com.jeferson.jeclient.repositories;

import com.jeferson.jeclient.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
