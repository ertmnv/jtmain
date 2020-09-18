package com.iverbs.jtmain.dto;

import java.util.List;

public class SectionDto {

    private Long id;

    private String name;

    private Long courseId;

    private List<LessonDto> lessonDtoList;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public List<LessonDto> getLessonDtoList() {
        return lessonDtoList;
    }

    public void setLessonDtoList(List<LessonDto> lessonDtoList) {
        this.lessonDtoList = lessonDtoList;
    }

}
