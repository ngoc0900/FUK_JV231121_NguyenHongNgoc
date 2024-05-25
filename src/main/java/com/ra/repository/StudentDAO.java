package com.ra.repository;

import com.ra.entity.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> getAll();
    void save(Student student);
    Student findById(Integer id);
    void delete(Integer id);
    List<Student> findByName(String keyword);
}
