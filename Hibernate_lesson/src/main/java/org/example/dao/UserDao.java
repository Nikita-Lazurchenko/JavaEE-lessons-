package org.example.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.entity.PersonalInfo;
import org.example.entity.PersonalInfo_;
import org.example.entity.User;
import org.example.entity.User_;
import org.hibernate.Session;


import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {
    @Getter
    private static final UserDao INSTANCE = new UserDao();

    private List<User> findAll(Session session) {
        var creatBuilder = session.getCriteriaBuilder();
        var criteria = creatBuilder.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user);

        return session.createQuery(criteria).list();
    }

    private List<User> findAllByFirstName(Session session, String firstName) {
        var creatBuilder = session.getCriteriaBuilder();
        var criteria = creatBuilder.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user).where(creatBuilder.equal(user.get(User_.personalInfo).get(PersonalInfo_.FIRST_NAME), firstName));

        return session.createQuery(criteria).list();
    }
}
