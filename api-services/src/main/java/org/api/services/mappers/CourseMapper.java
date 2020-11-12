package org.api.services.mappers;


import java.util.List;

import org.db.dto.CourseDto;
import org.db.model.Course;
import org.db.model.Student;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")    

public abstract class CourseMapper {
    
    @Mappings({ 
        @Mapping(target = "authorId", source = "author.id")
    })
    public abstract CourseDto courseToCourseDto(Course course);
    
    @IterableMapping(elementTargetType = String.class)
    protected abstract List<String> mapListStudentToListString(List<Student> students);

    protected String mapStudentsToString(Student student) {
        return String.valueOf(student.getId());
    }
    
}
