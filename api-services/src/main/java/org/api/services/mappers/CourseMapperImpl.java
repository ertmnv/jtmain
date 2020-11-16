package org.api.services.mappers;

import java.util.ArrayList;
import java.util.List;
import org.db.dto.CourseDto;
import org.db.model.Author;
import org.db.model.Course;
import org.db.model.Student;

public class CourseMapperImpl extends CourseMapper {

    @Override
    public CourseDto courseToCourseDto(final Course course) {
        if (course == null) {
            return null;
        }

        CourseDto courseDto = new CourseDto();

        courseDto.setAuthorId(courseAuthorId(course));
        courseDto.setId(course.getId());
        courseDto.setName(course.getName());
        courseDto.setStudents(mapListStudentToListString(course.getStudents()));

        return courseDto;
    }

    @Override
    protected List<String> mapListStudentToListString(final List<Student> students) {
        if (students == null) {
            return null;
        }

        List<String> list = new ArrayList<String>(students.size());
        for (Student student : students) {
            list.add(mapStudentsToString(student));
        }

        return list;
    }

    private Long courseAuthorId(final Course course) {
        if (course == null) {
            return null;
        }
        Author author = course.getAuthor();
        if (author == null) {
            return null;
        }
        Long id = author.getId();
        if (id == null) {
            return null;
        }
        return id;
    }
}
