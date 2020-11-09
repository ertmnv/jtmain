package org.db.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Author extends BaseEntity {

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "author")
    private List<Course> courses = new ArrayList<>();

}
