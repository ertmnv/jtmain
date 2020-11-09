package org.db.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.db.dto.LessonDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Lesson extends BaseEntity {

    @NotNull
    @Size(min=3, max=100, message="name of the lesson is not valid")
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Section section;

    public LessonDto toLessonDto() {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(this.getId());
        lessonDto.setName(this.getName());
        return lessonDto;
    }
}
