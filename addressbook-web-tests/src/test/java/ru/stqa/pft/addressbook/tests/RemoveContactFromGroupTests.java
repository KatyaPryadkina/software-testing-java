package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {
    @BeforeMethod
    public void unsurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().homePage();
            app.group().create( new GroupData().withName("test'3'").withHeader("test4").withFooter("test4"));
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            Groups groups=app.db().groups();
            app.contact().create(new ContactData().withLastname("Petrov").withFirstname("Petr").withNickname("petya").withAddress("Ленина 8").withEmail1("petrov@mail.ru").withMobilNumber("+79521458745").inGroup(groups.iterator().next()));
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
