package com.ra.service;

import com.ra.dto.CreateStudentForm;
import com.ra.entity.Student;

import java.util.List;

public interface StudentService {
    void update(Student student);
    List<Student> getAll();
    void save(CreateStudentForm createStudentForm);
    Student findById(Integer id);
    void delete(Integer id);
    List<Student> findByName(String keyword);
}
