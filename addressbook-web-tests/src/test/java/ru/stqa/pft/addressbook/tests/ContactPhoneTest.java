package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

  @BeforeMethod
  public void unsurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withLastname("Petrov").withFirstname("Petr").withNickname("petya").withAddress("Ленина 8").withEmail("petrov@mail.ru").withMobilNumber("+79521458745").withGroup("[none]"));
    }
  }
  @Test
  public void testContactPhone() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

  }

  private String mergePhones(ContactData contact) {                                                  //Метод обратных проверок
    return Arrays.asList(contact.getHomeNumber(), contact.getMobilNumber(), contact.getWorkNumber()) //массив
            .stream().filter((s) -> !s.equals(""))                                                  //преобразовываем в поток
            .map(ContactPhoneTest::cleaned)                                                         //делаем очистку
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");          //преобразование регулярных выражений
  }
}
