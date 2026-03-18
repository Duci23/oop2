package me.fit.service;

import jakarta.enterprise.context.ApplicationScoped;
import me.fit.model.Student;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StudentService {

    private List<Student> students = new ArrayList<>();

    public void createStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }
}
