package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseDto {

    private String title;
    private String description;
    private String goal;
    private int categoryId;
    private int difficultyId;
    private int languageId;
    private List<Integer> keywordIds;
    private List<CourseModuleDto> createModuleRequests;
}
