package com.iverbs.jtmain.dto;

import java.util.List;

public class PageForCourses {

    List<CourseDto> content;

    Long totalElements;

    public List<CourseDto> getContent() {
        return content;
    }

    public void setContent(List<CourseDto> content) {
        this.content = content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

}
