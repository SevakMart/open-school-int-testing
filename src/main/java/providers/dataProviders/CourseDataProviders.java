package providers.dataProviders;

import dtos.CourseDto;
import dtos.CourseModuleDto;
import org.apache.commons.lang3.RandomStringUtils;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;

import java.util.ArrayList;
import java.util.List;

public class CourseDataProviders {
    static List<Integer> keywordIds=new ArrayList<>();
    static List<CourseModuleDto> createModule = new ArrayList<>();

    public static CourseDto createCourse() {

        String description = RandomStringUtils.randomAlphabetic(5);
        String title = RandomStringUtils.randomAlphabetic(3);
        String goal = RandomStringUtils.randomAlphabetic(4);
        RequestsUtils.get(Endpoints.GET_ALL_PARENT_CATEGORIES.url, SharedTestData.getToken());
        int categoryId = ResponseUtils.getIntFromResponse("content[0].id");
        int difficultyId=2;
        int languageId=2;
        keywordIds.add(2);
        createModule.add(CourseModuleDataProvider.createCourseModule());
        return new CourseDto(description,title,goal,categoryId,difficultyId,languageId,keywordIds,createModule);
    }
}
