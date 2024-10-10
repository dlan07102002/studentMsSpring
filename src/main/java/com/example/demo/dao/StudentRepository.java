package com.example.demo.dao;

import com.example.demo.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "students")
public interface StudentRepository extends JpaRepository<Student, Integer> {
    public List<Student> findByLastName(String lastName);

    public List<Student> findByFirstName(String firstName);

    public Optional<Student> findByEmail(String email);
}
