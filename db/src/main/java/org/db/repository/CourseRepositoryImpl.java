package org.db.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.QueryHint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.jdbc.core.JdbcTemplate;


import org.db.model.Author;
import org.db.model.Course;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    @Autowired
    EntityManager em;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Course findById(Long id) {
        Course course = em.find(Course.class, id);
        return course;
    }

    @Override
    public void deleteById(Long id) {
        Course course = this.findById(id);
        em.remove(course);
    }

    @Override
    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    @Override
    @Transactional
    public List<Course> findAll(Integer page, Integer size) {
        List<Course> listCourse = jdbcTemplate.query("SELECT * FROM course", (rs, rowNum) -> {
            Course c = new Course();
            c.setId(rs.getLong("id"));
            return c;
        });
        List<Course> courses = em.createQuery("SELECT c FROM Course c", Course.class).setFirstResult(page * size)
                .setMaxResults(size).getResultList();
        return courses;
    }

    @Override
    public List<Course> getAllCoursesManagedByAuthor(Long authorId) {
        return em.createQuery("SELECT c FROM Course c LEFT JOIN c.author a WHERE a.id=:authorId", Course.class)
                .setParameter("authorId", authorId).getResultList();
    }

    @Override
    public Collection<Course> getAllCoursesTakenByStudent(Long studentId) {
        return em.createQuery("SELECT c FROM Course c LEFT JOIN c.students s WHERE s.id=:studentId", Course.class)
                .setParameter("studentId", studentId).getResultList();
    }

    @Override
    public Long getNumberOfCourses() {
        Long countResult = (long) em.createQuery("SELECT count(c.id) FROM Course c").getSingleResult();
        return countResult;
    }

    public Map<Long, List<String>> getStudentNameByCourse() {
        Query q = em.createNativeQuery(
                "SELECT c.id, u.email FROM course c " + "inner join student_courses sc on c.id = sc.courses_id "
                        + "inner join student s on sc.students_id = s.id " + "inner join users u on s.user_id = u.id");
        List<Object[]> usersByCourse = q.getResultList();
        Map<Long, List<String>> usersByCourseMap = new HashMap<Long, List<String>>();
        for (Object[] a : usersByCourse) {
            if (usersByCourseMap.get(((BigInteger) a[0]).longValue()) != null) {
                usersByCourseMap.get(((BigInteger) a[0]).longValue()).add((String) a[1]);
            } else {
                List<String> studentNameList = new ArrayList<String>();
                studentNameList.add((String) a[1]);
                usersByCourseMap.put(((BigInteger) a[0]).longValue(), studentNameList);
            }
        }
        return usersByCourseMap;
    }

}
