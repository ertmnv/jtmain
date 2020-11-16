package org.api.services.mappers;

import org.db.dto.LessonDto;
import org.db.model.Lesson;
import org.mapstruct.Mapper;

@Mapper
public interface LessonMapper {

    LessonDto lessonToLessonDto(Lesson lesson);

}
