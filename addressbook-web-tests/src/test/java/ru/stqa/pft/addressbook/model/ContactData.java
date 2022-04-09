package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData{
  private  Integer id;
  private final String firstname;
  private final String lastname;
  private final String nickname;
  private final String address;
  private final String email;
  private final String mobilNumber;
  private final String group;



  public ContactData(Integer id, String firstname, String lastname, String nickname, String address, String email, String mobilNumber, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.address = address;
    this.email = email;
    this.mobilNumber = mobilNumber;
    this.group = group;
  }

  public ContactData( String firstname, String lastname, String nickname, String address, String email, String mobilNumber, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.address = address;
    this.email = email;
    this.mobilNumber = mobilNumber;
    this.group = group;
  }

  public void setId(Integer id) {
    this.id = id;
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
    return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }



}