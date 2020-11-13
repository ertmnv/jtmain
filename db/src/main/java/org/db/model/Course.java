package org.db.model;

import org.db.dto.CourseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
public class Course {

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

    public Course(String name) {
        this.name = name;
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

}