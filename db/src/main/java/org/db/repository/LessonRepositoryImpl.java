package org.db.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.db.model.Lesson;

@Repository
public class LessonRepositoryImpl implements LessonRepository {

    @Autowired
    EntityManager em;

    @Override
    public Lesson findById(Long id) {
        return em.find(Lesson.class, id);
    }

    @Override
    public void deleteById(Long id) {
        Lesson lesson = this.findById(id);
        em.remove(lesson);
    }

    @Override
    public Lesson save(Lesson lesson) {
        if (lesson.getId() == null) {
            em.persist(lesson);
        } else {
            em.merge(lesson);
        }
        return lesson;
    }

    @Override
    public List<Lesson> findAll() {
        return em.createQuery("SELECT l FROM Lesson l", Lesson.class).getResultList();
    }

    @Override
    public List<Lesson> getAllLessonsBySection(Long sectionId) {
        return em.createQuery("SELECT l FROM Lesson l LEFT JOIN l.section s WHERE s.id =:sectionId", Lesson.class)
                .setParameter("sectionId", sectionId).getResultList();
    }

}
