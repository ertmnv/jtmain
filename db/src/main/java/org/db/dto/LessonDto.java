package org.db.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO that is used to pass lesson's data to client.
 */
@Data
@NoArgsConstructor
public class LessonDto {

    private Long id;

    private String name;

}
