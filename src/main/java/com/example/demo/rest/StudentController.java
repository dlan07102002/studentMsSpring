package com.example.demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public String showStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students/students";
    }

    @GetMapping("/create")
    public String toStudentFormView(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "/students/students-form";
    }

    @PostMapping("/save")
    public String createStudent(@ModelAttribute("student") Student student) {
        studentService.updateStudent(student);

        return "redirect:/students/list";
    }

    @GetMapping("/update")
    public String toStudentFormView(@RequestParam("id") Integer id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "/student/students-form";
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("id") Integer id) {
        studentService.deleteStudentById(id);

        return "redirect:/students/list";
    }

}
