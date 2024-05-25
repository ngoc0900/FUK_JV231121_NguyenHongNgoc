package com.ra.controller;

import com.ra.dto.CreateStudentForm;
import com.ra.entity.Student;
import com.ra.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("students",studentService.getAll());
        return "student/list";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("student",new CreateStudentForm());
        return "student/add";
    }
    @PostMapping("/add")
    public String doAdd(@ModelAttribute("student") CreateStudentForm createStudentForm){
        studentService.save(createStudentForm);
        return "redirect:/students/list";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id){
        Student student = studentService.findById(id);
        student.setDeleted(true);
        studentService.update(student);
        return "redirect:/students/list";
    }
    @GetMapping("/edit")
    public String edit(@RequestParam Integer id, Model model){
        Student student = studentService.findById(id);
        CreateStudentForm create = new CreateStudentForm();
        create.setStudentName(student.getStudentName());
        create.setAddress(student.getAddress());
        create.setPhoneNumber(student.getPhoneNumber());
        create.setSex(student.isSex());
        create.setBirthday(student.getBirthday());
        create.setBirthday(student.getBirthday());
        create.setId(student.getId());
        model.addAttribute("student",create);
        return "student/edit";
    }
    @PostMapping("/edit")
    public String update(@ModelAttribute("student") CreateStudentForm createStudentForm){
        studentService.save(createStudentForm);
        return "redirect:/students/list";
    }
    @GetMapping("/search")
    public String search(@RequestParam(name ="studentName") String name,Model model){
        model.addAttribute("students",studentService.findByName(name));
        return "student/list";
    }
}
