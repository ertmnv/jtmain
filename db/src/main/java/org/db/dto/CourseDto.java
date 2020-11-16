package org.db.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO that is used to pass course's data to client.
 */
@Data
@NoArgsConstructor
public class CourseDto {

    private Long id;

    private String name;

    private Long authorId;

    private List<String> students;

}
