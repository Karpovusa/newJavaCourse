package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.MantisUser;


import java.util.ArrayList;
import java.util.List;

public class DBhelper {


    private final SessionFactory sessionFactory;
    private final ApplicationManager app;

    public DBhelper(ApplicationManager app){
        this.app=app;
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public ArrayList<MantisUser> users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from MantisUser where username !='administrator'" ).list();
        session.getTransaction().commit();
        session.close();

        return (ArrayList<MantisUser>)result;

    }

}


