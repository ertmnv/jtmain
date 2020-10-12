package com.iverbs.jtmain.model;

import com.iverbs.jtmain.dto.CourseDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Course {

    private static Logger LOGGER = LoggerFactory.getLogger(Course.class);

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min=3, max=100, message="name of the course is not valid")
    @Column(nullable = false)
    private String name;

    @ManyToOne()
    private Author author;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    List<Section> section;

    @ManyToMany(mappedBy = "courses")
    List<Student> students;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Section> getSection() {
        return section;
    }

    public void setSection(List<Section> section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return String.format("Course[%s]", name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    // CR1: It is not a good idea to couple business model and representation model.
    // 1. You couple two layes model and representative model
    // 2. What are going to do if you will have more complex cases, like dto which contains data of several entities?
    // It is better to use specific converters. Your model must, repos and business layer must know nothing about
    // representative layer.
    public CourseDto toCourseDto() {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(this.getId());
        courseDto.setName(this.getName());
        courseDto.setAuthorId(this.getAuthor().getId());
        if (this.getStudents() != null) {
            courseDto.setStudents(this.getStudents().stream().map(student -> String.valueOf(student.getId()))
                    .collect(Collectors.toList()));
        }
        return courseDto;
    }

}