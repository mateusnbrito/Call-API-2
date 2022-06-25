package com.api.chamados.models;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.api.chamados.enums.StatusEnum;
import com.api.chamados.enums.SubjectsEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "calls")
public class CallModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "clientModel_id", nullable = false)
  private ClientModel clientModel;

  @Column(nullable = false)
  private SubjectsEnum subject;

  @Column(nullable = false)
  private StatusEnum status;

  @Column(nullable = true, length = 1024)
  private String text;

  @Column(nullable = true)
  @CreationTimestamp
  private Date timestamp;

  public CallModel() {}

  public CallModel(UUID id, ClientModel clientModel, SubjectsEnum subject, StatusEnum status, Date timestamp, String text) {
    this.id = id;
    this.clientModel = clientModel;
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
