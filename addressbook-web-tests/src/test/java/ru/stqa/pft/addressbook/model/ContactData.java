package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private Integer id; //= Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "nickname")
    private String nickname;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email1;

    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilNumber;

    @Column(name = "home")
    @Type(type = "text")
    private String homeNumber;

    @Column(name = "work")
    @Type(type = "text")
    private String workNumber;


    @Transient
    private String allPhones;

    @Transient
    private String allEmail;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;



    @ManyToMany(fetch = FetchType.EAGER) //из бд данные будут извлекаться по максисмуму за короткий промежуток проверки соединения "транзакции", чтобы не держать транзакцию на протежении всего теста
    @JoinTable(name= "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name= "group_id")) // joinColumns - тот столбец, кторый в классе текущем, inverseJoinColumns- с кем связан
    private Set<GroupData> groups = new HashSet<GroupData>(); //инициализация создания пустого множества

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getEmail2() {
        return email2;
    }


    public String getEmail3() {
        return email3;
    }


    public String getAllPhones() {
        return allPhones;
    }


    public String getAllEmail() {
        return allEmail;
    }


    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }


    public ContactData withId(Integer id) {
        this.id = id;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
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

    public ContactData withEmail1(String email) {
        this.email1 = email;
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
        return email1;
    }

    public String mobilNumber() {
        return mobilNumber;
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

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public String getEmail1() {
        return email1;
    }

    public String getMobilNumber() {
        return mobilNumber;
    }

    public Groups getGroups() {
        return new Groups(groups); //множество превратили в объект типа groups, при этом создается копия
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(address, that.address) && Objects.equals(email1, that.email1) && Objects.equals(mobilNumber, that.mobilNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, address, email1, mobilNumber);
    }
}