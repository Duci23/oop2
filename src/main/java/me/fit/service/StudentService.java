package me.fit.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import me.fit.model.Student;

import java.util.List;

@ApplicationScoped
public class StudentService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createStudent(Student student) {
        entityManager.persist(student);
    }

    public List<Student> getAllStudents() {
        return entityManager
                .createQuery("from Student", Student.class)
                .getResultList();
    }

    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> searchByName(String name) {
        return entityManager
                .createQuery("from Student where lower(name)=lower(:name)", Student.class)
                .setParameter("name", name)
                .getResultList();
    }


}