package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseModuleItemDto {
    private String title;
    private int moduleItemTypeId;
    private String link;
    private int estimatedTime;
    private int moduleId;
}
