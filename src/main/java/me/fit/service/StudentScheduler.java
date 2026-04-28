package me.fit.service;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StudentScheduler {

    @Inject
    StudentService studentService;

    @Scheduled(every = "30s")
    void countStudents() {
        System.out.println("Broj studenata: " + studentService.getAllStudents().size());
    }
}