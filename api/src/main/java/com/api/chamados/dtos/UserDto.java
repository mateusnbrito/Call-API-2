package com.api.chamados.dtos;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.api.chamados.models.ClientModel;

public class UserDto {
  private UUID id;
  private String firebaseId;
  private List<ClientModel> clientModels;
  private UUID clientModelId;
  private String name;
  private String email;
  private String password;
  private String picture;
  private Date timestamp;

  public UserDto() {}

  public UserDto(UUID id, String firebaseId, List<ClientModel> clientModels, UUID clientModelId, String name, String email, String password, String picture, Date timestamp) {
    this.id = id;
    this.firebaseId = firebaseId;
    this.clientModels = clientModels;
    this.clientModelId = clientModelId;
    this.name = name;
    this.email = email;
    this.password = password;
    this.picture = picture;
    this.timestamp = timestamp;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public List<ClientModel> getClientModels() {
    return clientModels;
  }

  public void setClientModels(List<ClientModel> clientModels) {
    this.clientModels = clientModels;
  }

  public UUID getClientModelId() {
    return clientModelId;
  }

  public void setClientModelId(UUID clientModelId) {
    this.clientModelId = clientModelId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getFirebaseId() {
    return firebaseId;
  }

  public void setFirebaseId(String firebaseId) {
    this.firebaseId = firebaseId;
  }
}
