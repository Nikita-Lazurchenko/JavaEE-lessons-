package org.example;

import org.example.entity.*;
import org.example.util.HibernateUtil;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class HibernateRunnerTest {
    @Test
    public void changeCourseName(){
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession())
        {
            session.beginTransaction();

            Course course = session.get(Course.class, 2);
            course.setCourseName("Java Enterprise");

            session.getTransaction().commit();
        }
    }

    @Test
    public void addTrainerAndCourse(){
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession())
        {
            session.beginTransaction();

            Course course = session.get(Course.class,2);
            Trainer trainer = Trainer.builder()
                    .firstName("Andrey")
                    .lastName("Borysov")
                    .experience("Senior")
                    .build();

            session.persist(trainer);

            TrainerCourse trainerCourse = TrainerCourse.builder().build();
            trainerCourse.setCourse(course);
            trainerCourse.setTrainer(trainer);

            session.persist(trainerCourse);

            session.getTransaction().commit();
        }
    }

    @Test
    public void deleteCourse(){
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession())
        {
            session.beginTransaction();

            Course course = session.get(Course.class, 5);
            session.remove(course);

            session.getTransaction().commit();
        }
    }

    @Test
    public void addStudentToCourse(){
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession())
        {
            Student student = Student.builder()
                    .firstName("Egor")
                    .lastName("Lomov")
                    .build();

            StudentProfile studentProfile = StudentProfile.builder()
                    .progress((short) 7).build();

            session.beginTransaction();

            studentProfile.setStudent(student);
            student.setCourse(session.get(Course.class, 2));
            session.persist(student);

            session.getTransaction().commit();
        }
    }

    @Test
    public void getStudentsByProgress(){
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession())
        {
            session.beginTransaction();

            String sql = "SELECT s FROM Student s " +
                    "WHERE s.studentProfile.progress < :progress";

            List<Student> students = session.createQuery(sql,Student.class)
                    .setParameter("progress",6).list();

            students.forEach(student -> session.remove(student));

            session.getTransaction().commit();
        }
    }

    @Test
    public void getStudentsByCourse(){
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession())
        {
            session.beginTransaction();

            String sql = "SELECT s FROM Student s " +
                   "WHERE s.course.courseName = :courseName";

            List<Student> students = session.createQuery(sql,Student.class)
                    .setParameter("courseName","Java EE").list();

            students.forEach(System.out::println);

            session.getTransaction().commit();
        }
    }

}

