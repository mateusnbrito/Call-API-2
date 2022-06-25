package com.api.chamados.models;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class UserModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, length = 128)
  private String firebaseId;

  @JsonManagedReference
  @OneToMany(mappedBy = "userModel", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<ClientModel> clientModels;

  @Column(nullable = false, length = 128)
  private String name;

  @Column(nullable = false, length = 128)
  private String email;

  @Column(nullable = false, length = 128)
  private String password;

  @Column(nullable = true, length = 1024)
  private String picture;

  @Column(nullable = true)
  @CreationTimestamp
  private Date timestamp;

  public UserModel() {}

  public UserModel(UUID id, String firebaseId, List<ClientModel> clientModels, String name, String email, String password, String picture, Date timestamp) {
    this.id = id;
    this.firebaseId = firebaseId;
    this.clientModels = clientModels;
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

  public void addClientModel(ClientModel clientModel) {
    (this.clientModels).add(clientModel);
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
