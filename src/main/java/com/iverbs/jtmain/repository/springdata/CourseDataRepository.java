package com.iverbs.jtmain.repository.springdata;

import java.util.List;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import com.iverbs.jtmain.model.Course;

public interface CourseDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByAuthorId(Long authorId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "3000") })
    @Query(value = "SELECT count(c.id) FROM Course c")
    List<Object[]> getNumberOfCourses();

    @Query(value = "SELECT * FROM course c INNER JOIN student_courses sc ON c.id = sc.courses_id INNER JOIN student s ON sc.students_id = s.id WHERE s.id  = ?1", nativeQuery = true)
    List<Course> allStudentsInCourse(Long studentId);

}
