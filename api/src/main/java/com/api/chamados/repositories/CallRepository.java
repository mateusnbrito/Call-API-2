package com.api.chamados.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.chamados.models.CallModel;

@Repository
public interface CallRepository extends JpaRepository<CallModel, UUID> {}
