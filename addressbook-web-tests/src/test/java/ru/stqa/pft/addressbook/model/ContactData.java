package ru.stqa.pft.addressbook.model;

public class ContactData {
    private String firstname;
    private String lastname;
    private String email;
    private String nickname;
    private String company;
    private String mobile;
    private String group;


    public ContactData(String firstname, String lastname, String email, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.group = group;
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

    public String getGroup() {return group;}

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getMobile() {
        return mobile;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
