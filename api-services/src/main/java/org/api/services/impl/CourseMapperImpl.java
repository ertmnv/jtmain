package org.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.db.dto.CourseDto;
import org.db.model.Author;
import org.db.model.Course;
import org.db.model.Student;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-11T18:34:19+0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl extends CourseMapper {

    @Override
    public CourseDto courseToCourseDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDto courseDto = new CourseDto();

        courseDto.setAuthorId( courseAuthorId( course ) );
        courseDto.setId( course.getId() );
        courseDto.setName( course.getName() );
        courseDto.setStudents( mapListStudentToListString( course.getStudents() ) );

        return courseDto;
    }

    @Override
    protected List<String> mapListStudentToListString(List<Student> students) {
        if ( students == null ) {
            return null;
        }

        List<String> list = new ArrayList<String>( students.size() );
        for ( Student student : students ) {
            list.add( mapStudentsToString( student ) );
        }

        return list;
    }

    private Long courseAuthorId(Course course) {
        if ( course == null ) {
            return null;
        }
        Author author = course.getAuthor();
        if ( author == null ) {
            return null;
        }
        Long id = author.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
