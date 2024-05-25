package com.ra.repository;

import com.ra.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Student> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Student where isDeleted = false", Student.class).list();
    }

    @Override
    public void save(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(student);
    }

    @Override
    public Student findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class,id);
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(findById(id));
    }

    @Override
    public List<Student> findByName(String keyword) {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> query = session.createQuery("from Student where studentName like :name", Student.class);
        query.setParameter("name","%"+keyword+"%" );
        return query.list();
    }
}
