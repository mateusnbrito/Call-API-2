package com.api.chamados.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.chamados.dtos.ClientDto;
import com.api.chamados.models.ClientModel;
import com.api.chamados.repositories.ClientRepository;
import com.api.chamados.repositories.UserRepository;

@Service
public class ClientService {
  @Autowired
  ClientRepository clientRepository;

  @Autowired
  UserRepository userRepository;

  public Optional<List<ClientDto>> findAllClients(){
    return Optional.of(modelToDto(clientRepository.findAll()));
  }

  public Optional<ClientDto> findOneClient(UUID id){
    return Optional.of(modelToDto(List.of(clientRepository.findById(id).get())).get(0));
  }

  @Transactional
  public Optional<ClientDto> saveOneClient(ClientDto clientDto){
    (userRepository.findById(clientDto.getUserModelId()).get()).addClientModel(dtoToModel(List.of(clientDto)).get(0));
    clientDto.setUserModel((userRepository.findById(clientDto.getUserModelId()).get()));

    return Optional.of(modelToDto(List.of(clientRepository.save(dtoToModel(List.of(clientDto)).get(0)))).get(0));
  }

  @Transactional
  public Optional<ClientDto> updateOneClient(UUID id, ClientDto clientDto){
    (userRepository.findById(clientDto.getUserModelId()).get()).addClientModel(dtoToModel(List.of(clientDto)).get(0));
    clientDto.setUserModel((userRepository.findById(clientDto.getUserModelId()).get()));

    clientRepository.save(dtoToModel(List.of(clientDto)).get(0));
    clientRepository.delete(clientRepository.findById(id).get());

    return Optional.of(clientDto);
  }

  @Transactional
  public Optional<ClientDto> deleteOneClient(UUID id){
    ClientDto clientDto = modelToDto(List.of(clientRepository.findById(id).get())).get(0);

    clientRepository.delete(clientRepository.findById(id).get());

    return Optional.of(List.of(clientDto).get(0));
  }

  private List<ClientDto> modelToDto(List<ClientModel> models){
    List<ClientDto> dtos = new ArrayList<>();

    models.forEach(model -> {
      ClientDto newDto = new ClientDto();

      BeanUtils.copyProperties(model, newDto);

      dtos.add(newDto);
    });

    return dtos;
  }

  private List<ClientModel> dtoToModel(List<ClientDto> dtos){
    List<ClientModel> models = new ArrayList<>();

    dtos.forEach(dto -> {
      ClientModel newModel = new ClientModel();

      BeanUtils.copyProperties(dto, newModel);

      models.add(newModel);
    });

    return models;
  }
}
