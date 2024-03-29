package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactAddInGroupTests extends TestBase {


    @BeforeMethod
    public void unsurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("test3").withHeader("test4").withFooter("test4"));

        }
        app.goTo().GroupPage();

        Contacts contacts = app.db().contacts();
        if (app.db().contacts().size() == 0 || contactOutsideTheGroup(contacts) == null) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withLastname("Petrov").withFirstname("Petr")
                    .withNickname("petya").withAddress("Ленина 8").withEmail1("petrov@mail.ru")
                    .withMobilNumber("+79521458745"));
        }
        app.goTo().homePage();


    }

    @Test//(enabled = false)
    public void ContactAddInGroup() {
        Contacts contacts = app.db().contacts();
        ContactData contactSelect = contactOutsideTheGroup(contacts);
        int before = contactSelect.getGroups().size();
        app.contact().selectObjectById(contactSelect.getId());
        app.contact().addInGroup(groupOutsideTheContact().getName());
        Contacts allContacts = app.db().contacts();
        ContactData selectAddAfter = findContact(allContacts, contactSelect.getId());
        int after = selectAddAfter.getGroups().size();
        assertThat(after, equalTo(before+1));
        verifyContactListInUI();
    }

    private ContactData findContact(Contacts contacts, int id){
        return contacts.stream().filter((c) -> c.getId() == id).findFirst().get();
    }

    public GroupData groupOutsideTheContact() {
        Contacts contacts = app.db().contacts();
        Groups groupsInContact = contactOutsideTheGroup(contacts).getGroups();
        Groups groups = app.db().groups();
        groups.removeAll(groupsInContact);
        GroupData group = groups.iterator().next();
        return group;
    }


    public ContactData contactOutsideTheGroup(Contacts contacts) {
        for (ContactData contact : contacts) {
            Set<GroupData> contactInGroup = contact.getGroups();
            Groups allGroup=app.db().groups();
            if (contactInGroup.size() < allGroup.size()) {
                return contact;
            }
        }
        return null;
    }


}
