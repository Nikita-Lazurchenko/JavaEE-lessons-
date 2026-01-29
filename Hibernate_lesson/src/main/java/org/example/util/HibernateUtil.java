package org.example.util;

import org.example.converter.BirthdayConverter;
import org.example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        Configuration config = new Configuration().configure();
        config.configure();
        config.addAttributeConverter(new BirthdayConverter());
        return config.buildSessionFactory();
    }
}
