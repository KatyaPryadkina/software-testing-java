package ru.stqa.pft.addressbook.model;

public record GroupData(String name, String header, String footer) {
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