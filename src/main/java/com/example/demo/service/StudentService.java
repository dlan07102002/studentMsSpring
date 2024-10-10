package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Student;

public interface StudentService {
    public List<Student> getAllStudents();

    public Student getStudentById(int id);

    public Student addStudent(Student student);

    public Student updateStudent(Student student);

    public void deleteStudentById(int id);

    public List<Student> findByLastName(String lastName);

    public List<Student> findByFirstName(String firstName);

    public Optional<Student> findByEmail(String email);
}
