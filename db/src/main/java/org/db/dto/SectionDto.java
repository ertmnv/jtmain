package org.db.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SectionDto {

    private Long id;

    private String name;

    private Long courseId;

    private List<LessonDto> lessonDtoList;
    
}
