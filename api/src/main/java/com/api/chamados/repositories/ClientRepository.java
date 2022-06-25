package com.api.chamados.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.chamados.models.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID>{}