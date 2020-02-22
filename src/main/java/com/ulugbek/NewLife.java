package com.ulugbek;

import com.Car;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class NewLife {
     @Test
    public void crud() {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        create(session);
        read(session);

        update(session);
        read(session);

        delete(session);
        read(session);

        session.close();
    }

    private void delete(Session session) {
        System.out.println("Deleting mondeo record...");
        Car mondeo = (Car) session.get(Car.class, "mondeo");

        session.beginTransaction();
        session.delete(mondeo);
        session.getTransaction().commit();
    }

    private void update(Session session) {
        System.out.println("Updating mustang price...");
        Car mustang = (Car) session.get(Car.class, "mustang");
        mustang.setName("mustang");
        mustang.setPrice(35250);

        session.beginTransaction();
        session.saveOrUpdate(mustang);
        session.getTransaction().commit();
    }

    private void create(Session session) {
        System.out.println("Creating car records...");

        Car mustang = new Car();
        mustang.setId(18);
        mustang.setName("mustang");
        mustang.setItems("new");
        mustang.setPrice(40);

        session.beginTransaction();
        session.save(mustang);
        session.getTransaction().commit();
    }

    private void read(Session session) {
        Query q = session.createQuery("select name, price from com.Car");

//        List cars = (List) q.list();


    }

}
