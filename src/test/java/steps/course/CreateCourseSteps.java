package steps.course;

import com.fasterxml.jackson.core.JsonProcessingException;
import dtos.CourseDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pojo.Course;
import providers.dataProviders.CourseDataProviders;
import providers.dataProviders.Endpoints;
import providers.dataProviders.SharedTestData;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

public class CreateCourseSteps {
    private final Map<String, Object> patvar = new HashMap<>();

    @And("Create course")
    public void createCourse() throws JsonProcessingException {
        CourseDto courseDto = CourseDataProviders.createCourse();
        RequestsUtils.post(Endpoints.CREATE_COURSE.url, courseDto);
        SharedTestData.setCourseId(ResponseUtils.getObjectFromResponse("", Course.class).getId());
    }

    @Then("Validate by jsonSchema created course")
    public void validateByJsonSchemaCreatedCourse() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/course/CreatedCourseResponseBodySchema.json");
    }

    @Then("Delete course")
    public void deleteCourse() {
        patvar.put("id", SharedTestData.getCourseId());
        System.out.println("Course id ------->" + SharedTestData.getCourseId());
        RequestsUtils.delete(Endpoints.DELETE_COURSE.url, patvar);
    }
}
