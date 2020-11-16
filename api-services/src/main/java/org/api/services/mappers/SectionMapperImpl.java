package org.api.services.mappers;

import java.util.ArrayList;
import java.util.List;
import org.db.dto.LessonDto;
import org.db.dto.SectionDto;
import org.db.model.Lesson;
import org.db.model.Section;
import org.mapstruct.factory.Mappers;

public class SectionMapperImpl implements SectionMapper {

    private final LessonMapper lessonMapper = Mappers.getMapper(LessonMapper.class);

    @Override
    public SectionDto sectionToSectionDto(final Section section) {
        if (section == null) {
            return null;
        }

        SectionDto sectionDto = new SectionDto();

        sectionDto.setLessons(lessonListToLessonDtoList(section.getLessons()));
        sectionDto.setId(section.getId());
        sectionDto.setName(section.getName());

        return sectionDto;
    }

    protected List<LessonDto> lessonListToLessonDtoList(final List<Lesson> list) {
        if (list == null) {
            return null;
        }

        List<LessonDto> list1 = new ArrayList<LessonDto>(list.size());
        for (Lesson lesson : list) {
            list1.add(lessonMapper.lessonToLessonDto(lesson));
        }

        return list1;
    }
}
