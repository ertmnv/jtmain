package com.iverbs.jtmain.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iverbs.jtmain.dto.SectionDto;
import com.iverbs.jtmain.model.Course;
import com.iverbs.jtmain.model.Section;

@Repository
public class SectionRepositoryImpl implements SectionRepository {

    @Autowired
    EntityManager em;

    @Override
    public Section findById(Long id) {
        Section section = em.find(Section.class, id);
        return section;
    }

    @Override
    public void deleteById(Long id) {
        Section section = this.findById(id);
        em.remove(section);
    }

    @Override
    public Section save(Section section) {
        if (section.getId() == null) {
            em.persist(section);
        } else {
            em.merge(section);
        }
        return section;
    }

    @Override
    public List<Section> findAll() {
        return em.createQuery("SELECT s FROM Section s", Section.class).getResultList();
    }

    @Override
    public List<Section> getAllSectionsByCourse(Long courseId) {
        return em.createQuery("SELECT s FROM Section s LEFT JOIN s.course c WHERE c.id=:courseId", Section.class)
                .setParameter("courseId", courseId).getResultList();
    }

}
