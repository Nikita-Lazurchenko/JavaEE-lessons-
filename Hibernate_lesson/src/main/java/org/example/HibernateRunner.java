package org.example;


import org.example.converter.BirthdayConverter;
import org.example.entity.Birthday;
import org.example.entity.Role;
import org.example.entity.User;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(),true);
        try(var sessionFactory = configuration.buildSessionFactory();
            var session = sessionFactory.openSession())
        {
            session.beginTransaction();

            session.persist(User.builder()
                            .username("example@mail.com")
                            .firstName("Nikita")
                            .lastName("Lazurchenko")
                            .birthday(new Birthday(LocalDate.of(2004,5,14)))
                            .role(Role.ADMIN)
                    .build());

            session.getTransaction().commit();
        }
    }
}
