package org.db.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;


import org.db.model.Course;

public interface CourseRepository {

    Course findById(Long id);

    void deleteById(Long id);

    Course save(Course course);

    List<Course> findAll(Integer page, Integer size);

    List<Course> getAllCoursesManagedByAuthor(Long authorId);

    Collection<Course> getAllCoursesTakenByStudent(Long studentId);

    Long getNumberOfCourses();

    Map<Long, List<String>> getStudentNameByCourse();
    
    int getCourseCountByCourseIdAndAuthorId(Long authorId, Long courseId);
    
    int getCourseCountByLessonIdAndAuthorId(Long authorId, Long lessonId);

    int getCourseCountBySectionIdAndAuthorId(Long authorId, Long sectionId);
}
