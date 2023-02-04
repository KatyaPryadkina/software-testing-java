package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();

        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

    }
    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from GroupData" ).list(); // запрос к объекту, вместо запроса sql
        session.getTransaction().commit();
        session.close();
        return  new Groups(result);

    }
    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list(); // запрос к объекту, вместо запроса sql, кроме удаленных с датой deprecated

        session.getTransaction().commit();
        session.close();
        return  new Contacts(result);
    }
    public ContactData contactInGroup(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ContactData result = (ContactData) session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list().get(0); // запрос к объекту, вместо запроса sql, кроме удаленных с датой deprecated

        session.getTransaction().commit();
        session.close();
        return  result;
    }
}
