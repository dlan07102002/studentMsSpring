package com.example.demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public void studentRepository(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/select-by-fname/{name}")
    public List<Student> getByFirstName(@PathVariable String name) {
        return this.studentService.findByFirstName(name);
    }

    @GetMapping("/select-by-lname/{name}")
    public List<Student> getByLastName(@PathVariable String name) {
        return this.studentService.findByLastName(name);
    }

    @GetMapping("/select-by-email/{email}")
    public Student getByEmail(@PathVariable String email) {
        return this.studentService.findByEmail(email).get();
    }

}
