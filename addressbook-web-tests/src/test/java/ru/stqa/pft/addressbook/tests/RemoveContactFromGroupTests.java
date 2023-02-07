package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {
    @BeforeMethod
    public void unsurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("test3").withHeader("test4").withFooter("test4"));

        }
        app.goTo().GroupPage();

        Contacts contacts = app.contact().all();
        if (app.contact().all().size() == 0 ) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withLastname("Petrov").withFirstname("Petr").withNickname("petya").withAddress("Ленина 8").withEmail1("petrov@mail.ru").withMobilNumber("+79521458745"));
        }
        app.goTo().homePage();
        for(ContactData contact : contacts){
            if(contact.getGroups().size() == 0){
                break;
            }
            app.contact().create(new ContactData().withLastname("Petrov").withFirstname("Petr")
                    .withNickname("petya").withAddress("Ленина 8").withEmail1("petrov@mail.ru")
                    .withMobilNumber("+79521458745").inGroup(app.group().all().iterator().next()));
        }
    }
    @Test//(enabled = false)
    public void RemoveContactGroup() {

        ContactData contact = app.db().contacts().iterator().next();
        Groups groupDelete = contact.getGroups();
        app.contact().removeContactFromGroup(contact);
        assertThat(app.db().contactInGroup(contact.getId()).getGroups().contains(groupDelete), equalTo(false)); //contains - модуль 8
    }
}
