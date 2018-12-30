package com.travis.hibernate.demo;

import com.travis.hibernate.demo.entity.Course;
import com.travis.hibernate.demo.entity.Instructor;
import com.travis.hibernate.demo.entity.InstructorDetail;
import com.travis.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        try (Session session = factory.getCurrentSession(); factory) {
            Instructor instructor = new Instructor("travis", "joe", "weelgod@gmail.com");
            InstructorDetail instructorDetail = new InstructorDetail("www.youtube.com", "luv2code");
            Course course = new Course("sex position");
            Course course2 = new Course("play sluts");
            Review review1 = new Review("great!");
            Review review2 = new Review("nice!");
            Review review3 = new Review("bad!");
            Review review4 = new Review("sucks!");
            instructor.setInstructorDetail(instructorDetail);
            course.addReview(review1);
            course.addReview(review2);
            course.addReview(review3);
            course2.addReview(review4);
            instructor.add(course);
            instructor.add(course2);
            session.beginTransaction();
            session.save(course);
            session.save(course2);
            session.save(instructor);
            session.save(review1);
            session.save(review2);
            session.save(review3);
            session.save(review4);
            session.getTransaction().commit();
        }
    }
}
