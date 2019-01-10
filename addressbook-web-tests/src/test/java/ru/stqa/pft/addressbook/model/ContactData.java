package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
@Entity
@Table(name="addressbook")
public class ContactData {
  @Expose
  @Column
  private String firstname;
  @Expose
  @Column
  private String lastname;
  @Expose
  @Type(type="text")
  private String email;
  @Type(type="text")
  private String email2;
  @Type(type="text")
  private String email3;
  @Transient
  private String nickname;

  private String company;
  @Column
  @Type(type="text")
  private String mobile;
  @Column
  @Type(type="text")
  private String home;
  @Column
  @Type(type="text")
  private String work;
  @Expose
  @Transient
  private String group;
  @Id
  @Column
  private int id = Integer.MAX_VALUE;
  @Transient
  private String allPhones;

  public File getPhoto() {
    if(photo==null){
      return null;
    }
    return new File(photo);

  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }
  @Column(name="photo")
  @Type(type="text")
  private String photo;

  public String getAddress() {
    return address;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }
  @Transient
  private String address;

  public void setAllEmails(String allEmails) {
    this.allEmails = allEmails;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }
  @Transient
  private String allEmails;

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }



  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  public String getNickname() {
    return nickname;
  }

  public String getCompany() {
    return company;
  }

  public String getMobile() {
    return mobile;
  }

  public int getId() {
    return id;
  }

  public String getHome() {
    return home;
  }

  public String getWorkPhone() {
    return work;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }


  public ContactData withHomePhone(String homePhone) {
    this.home = homePhone;
    return this;
  }

  public ContactData withWorkPhone(String work) {
    this.work = work;
    return this;
  }


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withEmail1(String email) {
    this.email = email;
    return this;
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "email='" + email + '\'' +
            ", lastname='" + lastname + '\'' +
            ", firstname='" + firstname + '\'' +
            ", id='" + id + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + id;
    return result;
  }
}
