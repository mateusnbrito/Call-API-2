package com.api.chamados.dtos;

import java.sql.Date;
import java.util.UUID;

import com.api.chamados.enums.StatusEnum;
import com.api.chamados.enums.SubjectsEnum;
import com.api.chamados.models.ClientModel;

public class CallDto {
  private UUID id;
  private ClientModel clientModel;
  private UUID clientModelId;
  private SubjectsEnum subject;
  private StatusEnum status;
  private Date timestamp;
  private String text;

  public CallDto() {}

  public CallDto(UUID id, ClientModel clientModel, UUID clientModelId, SubjectsEnum subject, StatusEnum status, Date timestamp, String text) {
    this.id = id;
    this.clientModel = clientModel;
    this.clientModelId = clientModelId;
    this.subject = subject;
    this.status = status;
    this.timestamp = timestamp;
    this.text = text;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public ClientModel getClientModel() {
    return clientModel;
  }

  public void setClientModel(ClientModel clientModel) {
    this.clientModel = clientModel;
  }

  public UUID getClientModelId() {
    return clientModelId;
  }

  public void setClientModelId(UUID clientModelId) {
    this.clientModelId = clientModelId;
  }

  public SubjectsEnum getSubject() {
    return subject;
  }

  public void setSubject(SubjectsEnum subject) {
    this.subject = subject;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
