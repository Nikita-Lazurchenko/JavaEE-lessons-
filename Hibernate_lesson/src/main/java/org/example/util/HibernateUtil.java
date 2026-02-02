package org.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        Configuration config = new Configuration().configure();
        config.configure();
        return config.buildSessionFactory();
    }
}
