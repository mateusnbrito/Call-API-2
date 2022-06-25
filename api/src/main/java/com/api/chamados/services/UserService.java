package com.api.chamados.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.chamados.dtos.UserDto;
import com.api.chamados.models.UserModel;
import com.api.chamados.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  private UserDto userByFirebase = null;

  public Optional<List<UserDto>> findAllUsers(){
    return Optional.of(modelToDto(userRepository.findAll()));
  }

  public Optional<UserDto> findOneUser(UUID id){
    return Optional.of(modelToDto(List.of(userRepository.findById(id).get())).get(0));
  }

  public Optional<UserDto> findOneUserByFirebase(String id){
    findAllUsers().get().forEach(item -> {
      if((item.getFirebaseId()).equals(id)){
        userByFirebase = item;
      }
    });

    return Optional.of(List.of(userByFirebase).get(0));
  }

  @Transactional
  public Optional<UserDto> saveOneUser(UserDto callDto){
    return Optional.of(modelToDto(List.of(userRepository.save(dtoToModel(List.of(callDto)).get(0)))).get(0));
  }

  @Transactional
  public Optional<UserDto> updateOneUser(UUID id, UserDto userDto){
    UserModel userModel = dtoToModel(List.of(userDto)).get(0);

    userRepository.save(userModel);
    userRepository.delete(userRepository.findById(id).get());

    return Optional.of(modelToDto(List.of(userModel)).get(0));
  }

  @Transactional
  public Optional<UserDto> deleteOneClient(UUID id){
    UserDto userDto = modelToDto(List.of(userRepository.findById(id).get())).get(0);

    userRepository.delete(userRepository.findById(id).get());

    return Optional.of(List.of(userDto).get(0));
  }

  @Transactional
  public void saveFile(UUID id, MultipartFile file) throws IllegalStateException, IOException{
    String directory = System.getProperty("user.dir");
    String relativeDirectory = "/src/main/resources/static/public/";
    String fileName = file.getOriginalFilename();

    file.transferTo(new File(directory+relativeDirectory+fileName));

    (userRepository.findById(id).get()).setPicture("http://localhost:8080/content/"+fileName);

    
  }

  private List<UserDto> modelToDto(List<UserModel> models){
    List<UserDto> dtos = new ArrayList<>();

    models.forEach(model -> {
      UserDto newDto = new UserDto();

      BeanUtils.copyProperties(model, newDto);

      dtos.add(newDto);
    });

    return dtos;
  }

  private List<UserModel> dtoToModel(List<UserDto> dtos){
    List<UserModel> models = new ArrayList<>();

    dtos.forEach(dto -> {
      UserModel newModel = new UserModel();

      BeanUtils.copyProperties(dto, newModel);

      models.add(newModel);
    });

    return models;
  }
}
