package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseModuleDto {
    private String title;
    private String description;
    private int courseId;
    private List <CourseModuleItemDto> createModuleItemRequests;
}
