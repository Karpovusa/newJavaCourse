package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;



@Entity
@Table(name="mantis_user_table")
public class MantisUser {
  @Id
  private int id;
  @Column
  private String username;
  @Column
  private String email;
  @Transient
  private boolean enabled;

  @Override
  public String toString() {
    return "MantisUser{" +
            "username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public boolean isEnabled() {
    return enabled;
  }
  public MantisUser(){
  }
  public MantisUser(String name,String email){
    this.username=name;
    this.email=email;

  }
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

}
