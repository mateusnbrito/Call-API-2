package com.api.chamados.models;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "clients")
public class ClientModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @JsonManagedReference
  @OneToMany(mappedBy = "clientModel", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<CallModel> callModels;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "userModel_id", nullable = false)
  private UserModel userModel;

  @Column(nullable = false, length = 128)
  private String name;

  @Column(nullable = false)
  private BigInteger cnpj;

  @Column(nullable = false, length = 128)
  private String address;

  @Column(nullable = true)
  @CreationTimestamp
  private Date timestamp;

  public ClientModel() {}

  public ClientModel(UUID id, List<CallModel> callModels, UserModel userModel, String name, BigInteger cnpj, String address, Date timestamp) {
    this.id = id;
    this.callModels = callModels;
    this.userModel = userModel;
    this.name = name;
    this.cnpj = cnpj;
    this.address = address;
    this.timestamp = timestamp;
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

  public void addCallModel(CallModel callModel) {
    (this.callModels).add(callModel);
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
}
