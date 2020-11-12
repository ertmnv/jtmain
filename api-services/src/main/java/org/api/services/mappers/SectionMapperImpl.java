package org.api.services.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.db.dto.LessonDto;
import org.db.dto.SectionDto;
import org.db.model.Lesson;
import org.db.model.Section;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-11T18:34:19+0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
public class SectionMapperImpl implements SectionMapper {

    private final LessonMapper lessonMapper = Mappers.getMapper( LessonMapper.class );

    @Override
    public SectionDto sectionToSectionDto(Section section) {
        if ( section == null ) {
            return null;
        }

        SectionDto sectionDto = new SectionDto();

        sectionDto.setLessons( lessonListToLessonDtoList( section.getLessons() ) );
        sectionDto.setId( section.getId() );
        sectionDto.setName( section.getName() );

        return sectionDto;
    }

    protected List<LessonDto> lessonListToLessonDtoList(List<Lesson> list) {
        if ( list == null ) {
            return null;
        }

        List<LessonDto> list1 = new ArrayList<LessonDto>( list.size() );
        for ( Lesson lesson : list ) {
            list1.add( lessonMapper.lessonToLessonDto( lesson ) );
        }

        return list1;
    }
}
