package org.db.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO that is used to pass section's data to client.
 */
@Data
@NoArgsConstructor
public class SectionDto {

    private Long id;

    private String name;

    private Long courseId;

    private List<LessonDto> lessons;

}
