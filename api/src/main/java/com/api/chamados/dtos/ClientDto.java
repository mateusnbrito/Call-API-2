package com.api.chamados.dtos;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.api.chamados.models.CallModel;
import com.api.chamados.models.UserModel;

public class ClientDto {
  private UUID id;
  private UserModel userModel;
  private UUID userModelId;
  private List<CallModel> callModels;
  private UUID callModelId;
  private String name;
  private BigInteger cnpj;
  private String address;
  private Date timestamp;

  public ClientDto() {}

  public ClientDto(UUID id, List<CallModel> callModels, UUID callModelId, UserModel userModel, String name, BigInteger cnpj, String address, Date timestamp, UUID userModelId) {
    this.id = id;
    this.callModels = callModels;
    this.callModelId = callModelId;
    this.userModel = userModel;
    this.name = name;
    this.cnpj = cnpj;
    this.address = address;
    this.timestamp = timestamp;
    this.userModelId = userModelId;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public List<CallModel> getCallModels() {
    return callModels;
  }

  public void setCallModels(List<CallModel> callModels) {
    this.callModels = callModels;
  }

  public UUID getCallModelId() {
    return callModelId;
  }

  public void setCallModelId(UUID callModelId) {
    this.callModelId = callModelId;
  }

  public UserModel getUserModel() {
    return userModel;
  }

  public void setUserModel(UserModel userModel) {
    this.userModel = userModel;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigInteger getCnpj() {
    return cnpj;
  }

  public void setCnpj(BigInteger cnpj) {
    this.cnpj = cnpj;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public UUID getUserModelId() {
    return userModelId;
  }

  public void setUserModelId(UUID userModelId) {
    this.userModelId = userModelId;
  }
}
