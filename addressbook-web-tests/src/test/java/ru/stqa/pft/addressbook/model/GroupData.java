package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@XStreamAlias("group")
@Entity
@Table(name="group_list")

public class GroupData {
  @XStreamOmitField
  @Id
  @Column(name="group_id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name="group_name")
  private  String name;
  @Expose
  @Column(name="group_header")
  @Type(type = "text")
  private  String header;
  @Expose
  @Column(name="group_footer")
  @Type(type = "text")
  private  String footer;




  public int getid() {
    return id;
  }

  public String name() {
    return name;
  }

  public String header() {
    return header;
  }

  public String footer() {
    return footer;
  }


  public GroupData withId(int id) {
    this.id = id;
    return this;
  }
  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id && Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {                                      //хэширование, более быстрая проверка, при помощи более быстрой операции. Быстрее,чем equals
    return Objects.hash(id, name);
  }

}
//так описывается более ранняя форма записи:  создания класса типа record
/*public class GroupData {
  private String name;
  private String header;
  private String footer;

  public GroupData(String name, String header, String footer) {
    this.name = name;
    this.header = header;
    this.footer = footer;
  }
}*/