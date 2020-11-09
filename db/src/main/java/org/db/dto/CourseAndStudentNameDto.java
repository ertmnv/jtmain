package org.db.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseAndStudentNameDto {

    CourseDto course;
    
    List<String> studentName;

}
