package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData{
  private  Integer id = Integer.MAX_VALUE;
  private  String firstname;
  private  String lastname;
  private  String nickname;
  private  String address;
  private  String email;
  private  String mobilNumber;
  private  String homeNumber;
  private  String workNumber;
  private  String group;




  public ContactData withId(Integer id) {
    this.id = id;
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

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;

  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;

  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;

  }

  public ContactData withMobilNumber(String mobilNumber) {
    this.mobilNumber = mobilNumber;
    return this;

  }

  public ContactData withHomeNumber(String homeNumber) {
    this.homeNumber = homeNumber;
    return this;

  }

  public ContactData withWorkNumber(String workNumber) {
    this.workNumber = workNumber;
    return this;

  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;

  }


  public int id() {
    return id;
  }

  public String firstname() {
    return firstname;
  }

  public String lastname() {
    return lastname;
  }

  public String nickname() {
    return nickname;
  }

  public String address() {
    return address;
  }

  public String email() {
    return email;
  }

  public String mobilNumber() {
    return mobilNumber;
  }

  public String group() {
    return group;
  }


  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getMobilNumber() {
    return mobilNumber;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }


}