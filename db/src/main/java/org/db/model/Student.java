package org.db.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author snavrockiy
 *
 *         JPA entity representing a student. Student can participate in
 *         different courses.
 *
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends BaseEntity {

    @OneToOne
    private User user;

    @ManyToMany
    private List<Course> courses = new ArrayList<>();

    public void addCourse(final Course course) {
        this.courses.add(course);
    }

    public void removeCourse(final Course course) {
        this.courses.remove(course);
    }

}
