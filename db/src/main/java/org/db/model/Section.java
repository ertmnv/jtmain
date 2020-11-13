package org.db.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.db.dto.SectionDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Section extends BaseEntity {

    @NotNull
    @Size(min=3, max=100, message="name of the section is not valid")
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Lesson> lessons;
    
}
