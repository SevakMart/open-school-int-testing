package providers.dataProviders;

import dtos.CourseModuleDto;
import dtos.CourseModuleItemDto;
import org.apache.commons.lang3.RandomStringUtils;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;

import java.util.ArrayList;
import java.util.List;

public class CourseModuleDataProvider {


    static List<CourseModuleItemDto> createModuleItem = new ArrayList<>();

    public static CourseModuleDto createCourseModule(){
        String title = RandomStringUtils.randomAlphabetic(5);
        String description= RandomStringUtils.randomAlphabetic(2);
        RequestsUtils.get(Endpoints.CREATE_COURSE.url, SharedTestData.getToken());
        int courseId = ResponseUtils.getIntFromResponse("content[0].id");
        createModuleItem.add(CourseModuleItemDataProvider.createCourseModuleItem());
        return new CourseModuleDto(title,description,courseId,createModuleItem);
    }
}
