package com.api.chamados.controllers;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.chamados.dtos.UserDto;
import com.api.chamados.services.UserService;

@RestController
@RequestMapping
public class UserController {
  @Autowired
  UserService userService;

  final String host = "/usuarios";
  final String list = "/todos-os-usuarios";
  final String create = "/criar-usuario";
  final String update = "/atualizar-usuario";
  final String delete = "/deletar-usuario";
  final String image = "/salvar-imagem";
  final String login = "/login";
  final String id = "/{id}";

  @GetMapping(host+list)
  public ResponseEntity<List<UserDto>> getUsers(){
    ResponseEntity<List<UserDto>> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers().get());

    return responseEntity;
  }

  @GetMapping(host+list+id)
  public ResponseEntity<UserDto> getOneUser(@PathVariable UUID id){
    ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(userService.findOneUser(id).get());

    return responseEntity;
  }

  @GetMapping(host+login+list+id)
  public ResponseEntity<UserDto> getOneUserByFirebaseId(@PathVariable String id){
    ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(userService.findOneUserByFirebase(id).get());

    return responseEntity;
  }

  @PostMapping(host+create)
  public ResponseEntity<UserDto> postOneUser(@RequestBody UserDto userDto){
    ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(userService.saveOneUser(userDto).get());

    return responseEntity;
  }

  @PutMapping(host+update+id)
  public ResponseEntity<UserDto> putOneClient(@PathVariable UUID id, @RequestBody UserDto userDto){
    ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(userService.updateOneUser(id, userDto).get());

    return responseEntity;
  }

  @DeleteMapping(host+delete+id)
  public ResponseEntity<UserDto> deleteOneClient(@PathVariable UUID id){
    ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(userService.deleteOneClient(id).get());

    return responseEntity;
  }

  @PostMapping(value = host+id+image)
  public void postImage(@PathVariable UUID id, @RequestPart("file") MultipartFile file) throws IllegalStateException, IOException{
    userService.saveFile(id, file);
  }
}
