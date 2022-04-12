package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase{

  @Test
  public void testContactPhone(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getMobilNumber(), equalTo(cleaned(contactInfoFromEditForm.getMobilNumber())));
    assertThat(contact.getHomeNumber(), equalTo(cleaned(contactInfoFromEditForm.getHomeNumber())));
    assertThat(contact.getWorkNumber(), equalTo(cleaned(contactInfoFromEditForm.getWorkNumber())));

  }
  public String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]]","");          //преобразование регулярных выражений
  }
}
