package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void unsurePreconditions() {

        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            Groups groups=app.db().groups();
            app.contact().create(new ContactData().withLastname("Petrov").withFirstname("Petr").withNickname("petya").withAddress("Ленина 8").withEmail1("petrov@mail.ru").withMobilNumber("+79521458745").inGroup(groups.iterator().next()));
        }
    }

    @Test//(enabled = false)
    public void contactModification() {

        app.goTo().homePage();
        Groups groups=app.db().groups();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withLastname("333").withFirstname("333").withNickname("333").withAddress("Ленина 8").withEmail1("petrov@mail.ru").withMobilNumber("+79521458745").inGroup(groups.iterator().next());
        app.contact().modify(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

        verifyContactListInUI();
    }


}
