package org.db.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageForCourses {

    List<CourseDto> content;

    Long totalElements;

}
