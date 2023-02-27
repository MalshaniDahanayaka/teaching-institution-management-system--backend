package com.isa.teachingInstitution.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table
@AllArgsConstructor
public class Course {

    @Id
    @Column(name = "course_id")
    private String courseID;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "about_course")
    private String aboutCourse;

    @Column(name = "time_slot")
    private String timeSlot;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    @OneToOne(mappedBy = "course")
    private Teacher teacher;

    public Course(String courseID, String courseName, String aboutCourse, String timeSlot) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.aboutCourse = aboutCourse;
        this.timeSlot = timeSlot;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAboutCourse() {
        return aboutCourse;
    }

    public void setAboutCourse(String aboutCourse) {
        this.aboutCourse = aboutCourse;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}