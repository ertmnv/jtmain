package com.iverbs.jtmain.dto;

import java.util.List;

public class CourseAndStudentNameDto {

    CourseDto course;
    
    List<String> studentName;

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public List<String> getStudentName() {
        return studentName;
    }

    public void setStudentName(List<String> studentName) {
        this.studentName = studentName;
    }

}
