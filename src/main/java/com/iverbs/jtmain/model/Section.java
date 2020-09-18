package com.iverbs.jtmain.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.iverbs.jtmain.dto.CourseDto;
import com.iverbs.jtmain.dto.SectionDto;

@Entity
public class Section extends BaseEntity {

    @NotNull
    @Size(min=3, max=100, message="name of the section is not valid")
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public SectionDto toSectionDto() {
        SectionDto sectionDto = new SectionDto();
        sectionDto.setId(this.getId());
        sectionDto.setName(this.getName());
        sectionDto.setCourseId(this.getCourse().getId());
        sectionDto.setLessonDtoList(
                this.getLessons().stream().map(lesson -> lesson.toLessonDto()).collect(Collectors.toList()));
        return sectionDto;
    }

}
