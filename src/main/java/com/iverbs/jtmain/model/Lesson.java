package com.iverbs.jtmain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.iverbs.jtmain.dto.LessonDto;

@Entity
public class Lesson extends BaseEntity {

    @NotNull
    @Size(min=3, max=100, message="name of the lesson is not valid")
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Section section;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public LessonDto toLessonDto() {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(this.getId());
        lessonDto.setName(this.getName());
        return lessonDto;
    }
}
