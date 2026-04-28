package me.fit.service;

import jakarta.enterprise.context.ApplicationScoped;
import me.fit.model.Student;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StudentService {

    private List<Student> students = new ArrayList<>();
    private Long counter = 1L;

    public void createStudent(Student student) {
        student.setId(counter++);
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    // PRETRAGA PO ID
    public Student getStudentById(Long id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // PRETRAGA PO IMENU
    public List<Student> searchByName(String name) {
        List<Student> result = new ArrayList<>();


        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                result.add(student);
            }
        }

        return result;
    }
}