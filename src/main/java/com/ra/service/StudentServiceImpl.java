package com.ra.service;

import com.ra.dto.CreateStudentForm;
import com.ra.entity.Student;
import com.ra.repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional


public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private UploadService uploadService;

    @Override
    public void update(Student student) {
        studentDAO.save(student);
    }

    @Override
    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    @Override
    public void save(CreateStudentForm createStudentForm) {
        // chuyển đổi dto -> entity
        Student student = Student.builder()
                .id(createStudentForm.getId())
                .studentName(createStudentForm.getStudentName())
                .address(createStudentForm.getAddress())
                .phoneNumber(createStudentForm.getPhoneNumber())
                .birthday(createStudentForm.getBirthday())
                .sex(createStudentForm.isSex())
                .isDeleted(false)
                .build();

        if (createStudentForm.getFileImg() != null){
            // upload ảnh
            if (createStudentForm.getFileImg().getSize()!=0){
                String url = uploadService.uploadFileToServer(createStudentForm.getFileImg());
                student.setImageUrl(url);
            }
        }
//        // upload ảnh
//        if (createStudentForm.getFileImg().getSize()!=0){
//            String url = uploadService.uploadFileToServer(createStudentForm.getFileImg());
//            student.setImageUrl(url);
//        }
        studentDAO.save(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentDAO.findById(id);
    }

    @Override
    public void delete(Integer id) {
        studentDAO.delete(id);
    }

    @Override
    public List<Student> findByName(String keyword) {
        return studentDAO.findByName(keyword);
    }
}
