package org.api.services.mappers;

import javax.annotation.Generated;
import org.db.dto.LessonDto;
import org.db.model.Lesson;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-11T18:34:19+0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
public class LessonMapperImpl implements LessonMapper {

    @Override
    public LessonDto lessonToLessonDto(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonDto lessonDto = new LessonDto();

        lessonDto.setId( lesson.getId() );
        lessonDto.setName( lesson.getName() );

        return lessonDto;
    }
}
