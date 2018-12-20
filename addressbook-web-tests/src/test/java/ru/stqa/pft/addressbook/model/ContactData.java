package ru.stqa.pft.addressbook.model;

public class ContactData {
  private String firstname;
  private String lastname;
  private String email;
  private String nickname;
  private String company;
  private String mobile;
  private String group;
  private int id = Integer.MAX_VALUE;

/*
    public ContactData(String firstname, String lastname, String email, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.group = group;
        this.id=Integer.MAX_VALUE;
    }
    public ContactData(int id,String firstname, String lastname, String email, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.group = group;
        this.id=id;
    }
    */

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

  public ContactData withEmail(String email) {
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
