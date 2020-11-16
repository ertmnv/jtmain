package org.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author snavrockiy
 *
 *         JPA entity representing a Lesson. Lesson is part of the section and
 *         section is part of course.
 *
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Lesson extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Section section;

}
