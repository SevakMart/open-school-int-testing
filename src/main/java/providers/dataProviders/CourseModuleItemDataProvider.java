package providers.dataProviders;

import dtos.CourseModuleItemDto;
import org.apache.commons.lang3.RandomStringUtils;

public class CourseModuleItemDataProvider {

        public static CourseModuleItemDto createCourseModuleItem(){
             String title = RandomStringUtils.randomAlphabetic(3);
             int moduleItemTypeId=1;
             String link=RandomStringUtils.randomAlphabetic(3);
             int estimatedTime = 100;
             int modulId=1;
            return new CourseModuleItemDto(title,moduleItemTypeId,link,estimatedTime,modulId);
        }
    }
