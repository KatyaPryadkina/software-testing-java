package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public record GroupData(String id, String name, String header, String footer) {

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return Objects.equals(id, groupData.id) && Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }


  public String getid() {
    return id;
  }

  public String getname() {
    return name;
  }

  public String getheader() {
    return header;
  }

  public String getfooter() {
    return footer;
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