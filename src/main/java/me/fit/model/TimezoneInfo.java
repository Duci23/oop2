package me.fit.model;

import jakarta.persistence.*;

@Entity
public class TimezoneInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timeZone;
    private String dateTime;
    private String date;
    private String time;
    private String dayOfWeek;

    @ManyToOne
    private Student student;

    public Long getId() { return id; }

    public String getTimeZone() { return timeZone; }
    public void setTimeZone(String timeZone) { this.timeZone = timeZone; }

    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}