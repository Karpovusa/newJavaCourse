package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;

public class DBhelper {


    private final SessionFactory sessionFactory;

    public DBhelper(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();

        return new Groups(result);

    }

    public Contacts contacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);

    }

    public ContactData contact(int id){

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from ContactData where deprecated = '0000-00-00' and id="+id ).list();
        session.getTransaction().commit();
        session.close();
        Contacts contacts = new Contacts(result);

        return contacts.iterator().next();

    }
}


