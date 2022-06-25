package com.api.chamados.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.chamados.dtos.CallDto;
import com.api.chamados.models.CallModel;
import com.api.chamados.repositories.CallRepository;
import com.api.chamados.repositories.ClientRepository;

@Service
public class CallService {
  @Autowired
  CallRepository callRepository;

  @Autowired
  ClientRepository clientRepository;

  public Optional<List<CallDto>> findAllCalls(){
    return Optional.of(modelToDto(callRepository.findAll()));
  }

  public Optional<CallDto> findOneCall(UUID id){
    return Optional.of(modelToDto(List.of(callRepository.findById(id).get())).get(0));
  }

  @Transactional
  public Optional<CallDto> saveOneCall(CallDto callDto){
    (clientRepository.findById(callDto.getClientModelId()).get()).addCallModel(dtoToModel(List.of(callDto)).get(0));
    callDto.setClientModel((clientRepository.findById(callDto.getClientModelId()).get()));

    return Optional.of(modelToDto(List.of(callRepository.save(dtoToModel(List.of(callDto)).get(0)))).get(0));
  }

  @Transactional
  public Optional<CallDto> updateOneCall(UUID id, CallDto callDto){
    (clientRepository.findById(callDto.getClientModelId()).get()).addCallModel(dtoToModel(List.of(callDto)).get(0));
    callDto.setClientModel((clientRepository.findById(callDto.getClientModelId()).get()));

    callRepository.save(dtoToModel(List.of(callDto)).get(0));
    callRepository.delete(callRepository.findById(id).get());

    return Optional.of(callDto);
  }

  @Transactional
  public Optional<CallDto> deleteOneCall(UUID id){
    CallDto callDto = modelToDto(List.of(callRepository.findById(id).get())).get(0);

    callRepository.delete(callRepository.findById(id).get());

    return Optional.of(List.of(callDto).get(0));
  }

  private List<CallDto> modelToDto(List<CallModel> models){
    List<CallDto> dtos = new ArrayList<>();

    models.forEach(model -> {
      CallDto newDto = new CallDto();

      BeanUtils.copyProperties(model, newDto);

      dtos.add(newDto);
    });

    return dtos;
  }

  private List<CallModel> dtoToModel(List<CallDto> dtos){
    List<CallModel> models = new ArrayList<>();

    dtos.forEach(dto -> {
      CallModel newModel = new CallModel();

      BeanUtils.copyProperties(dto, newModel);

      models.add(newModel);
    });

    return models;
  }
}
