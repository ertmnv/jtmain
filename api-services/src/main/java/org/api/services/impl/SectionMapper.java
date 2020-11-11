package org.api.services.impl;

import org.db.dto.SectionDto;
import org.db.model.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses=LessonMapper.class)
public interface SectionMapper {

    @Mappings({ 
        @Mapping(target = "lessonDtoList", source = "lessons")
    })
    SectionDto sectionToSectionDto(Section section);
    
}
