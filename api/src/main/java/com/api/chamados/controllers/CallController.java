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

import com.api.chamados.dtos.CallDto;
import com.api.chamados.services.CallService;

@RestController
@RequestMapping
public class CallController {
  @Autowired
  CallService callService;

  final String host = "/chamados";
  final String list = "/todos-os-chamados";
  final String create = "/criar-chamado";
  final String update = "/atualizar-chamado";
  final String delete = "/deletar-chamado";
  final String id = "/{id}";

  @GetMapping(host+list)
  public ResponseEntity<List<CallDto>> getAllCalls(){
    ResponseEntity<List<CallDto>> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(callService.findAllCalls().get());

    return responseEntity;
  }

  @GetMapping(host+list+id)
  public ResponseEntity<CallDto> getOneCall(@PathVariable UUID id){
    ResponseEntity<CallDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(callService.findOneCall(id).get());

    return responseEntity;
  }

  @PostMapping(host+create)
  public ResponseEntity<CallDto> postOneCall(@RequestBody CallDto callDto){
    ResponseEntity<CallDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(callService.saveOneCall(callDto).get());

    return responseEntity;
  }

  @PutMapping(host+update+id)
  public ResponseEntity<CallDto> putOneCall(@PathVariable UUID id, @RequestBody CallDto callDto){
    ResponseEntity<CallDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(callService.updateOneCall(id, callDto).get());

    return responseEntity;
  }

  @DeleteMapping(host+delete+id)
  public ResponseEntity<CallDto> deleteOneCall(@PathVariable UUID id){
    ResponseEntity<CallDto> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

    responseEntity = ResponseEntity.status(HttpStatus.OK).body(callService.deleteOneCall(id).get());

    return responseEntity;
  }
}
