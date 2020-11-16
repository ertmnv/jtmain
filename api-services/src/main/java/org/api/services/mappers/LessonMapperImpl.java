package org.api.services.mappers;

import org.db.dto.LessonDto;
import org.db.model.Lesson;

public class LessonMapperImpl implements LessonMapper {

    @Override
    public LessonDto lessonToLessonDto(final Lesson lesson) {
        if (lesson == null) {
            return null;
        }

        LessonDto lessonDto = new LessonDto();

        lessonDto.setId(lesson.getId());
        lessonDto.setName(lesson.getName());

        return lessonDto;
    }
}
