package org.db.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO that is used to pass courses to client in format with similar to
 * {@link org.springframework.data.domain.Page} structure.
 */
@Data
@NoArgsConstructor
public class PageForCourses {

    private List<CourseDto> content;

    private Long totalElements;

}
