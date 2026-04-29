package me.fit.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.*;
import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Student() {
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Course> courses;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Instructor> instructors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    public List<TimezoneInfo> timezoneInfos = new ArrayList<>();

    public List<TimezoneInfo> getTimezoneInfos() {
        return timezoneInfos;
    }

    public void setTimezoneInfos(List<TimezoneInfo> timezoneInfos) {
        this.timezoneInfos = timezoneInfos;
    }

}