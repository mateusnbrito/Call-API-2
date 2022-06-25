package com.api.chamados.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.api.chamados.dtos.ClientDto;
import com.api.chamados.services.ClientService;

@RestController
@RequestMapping
public class ClientController {
  @Autowired
  ClientService clientService;

  final String host = "/clientes";
  final String list = "/todos-os-clientes";
  final String create = "/criar-cliente";
  final String update = "/atualizar-cliente";
  final String delete = "/deletar-cliente";
  final String id = "/{id}";

  @GetMapping(host+list)
  public ResponseEntity<List<ClientDto>> getAllClients(){
    ResponseEntity<List<ClientDto>> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(clientService.findAllClients().get());

    return responseEntity;
  }

  @GetMapping(host+list+id)
  public ResponseEntity<ClientDto> getOneClient(@PathVariable UUID id){
    ResponseEntity<ClientDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(clientService.findOneClient(id).get());

    return responseEntity;
  }

  @PostMapping(host+create)
  public ResponseEntity<ClientDto> postOneClient(@RequestBody ClientDto callDto){
    ResponseEntity<ClientDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveOneClient(callDto).get());

    return responseEntity;
  }

  @PutMapping(host+update+id)
  public ResponseEntity<ClientDto> putOneClient(@PathVariable UUID id, @RequestBody ClientDto callDto){
    ResponseEntity<ClientDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(clientService.updateOneClient(id, callDto).get());

    return responseEntity;
  }

  @DeleteMapping(host+delete+id)
  public ResponseEntity<ClientDto> deleteOneClient(@PathVariable UUID id){
    ResponseEntity<ClientDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(clientService.deleteOneClient(id).get());

    return responseEntity;
  }
}
