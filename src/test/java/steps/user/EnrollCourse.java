
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import manager.DeleteManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import providers.dataProviders.Endpoints;
import providers.dataProviders.SharedTestData;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

public class EnrollCourse {

    private final static Logger logger = LoggerFactory.getLogger(RequestsUtils.class);
    private final Map<String, Object> pathVar = new HashMap<>();

    @Then("Enroll course for the user")
    public void enrollCourseForTheUser() {
        RequestsUtils.get("courses", SharedTestData.getToken());
        int courseId = ResponseUtils.getIntFromResponse("content[0].id");
        SharedTestData.setCourseId(courseId);
        logger.info("The course Id is {}", courseId);
        pathVar.put("userId", SharedTestData.getUserId());
        pathVar.put("courseId", courseId);
        logger.info("PathVariables are {}", pathVar);
        RequestsUtils.post(Endpoints.ENROLL_COURSE.url, "", pathVar);
    }

    @And("Validate enroll course for the user response body by jsonSchema")
    public void validateEnrollCourseForTheUserResponseBodyByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/usersSchemas/SaveCourseForMentorsResponseBodySchema.json");
    }

    @Then("Delete enrolled course")
    public void deleteEnrolledCourse() {
        DeleteManager courseManager = new DeleteManager();
        courseManager.deleteCourse(SharedTestData.getEnrolledCourseId());
    }

    @Then("Get just enrolled courseId")
    public void getJustEnrolledCourseId() {
        RequestsUtils.get("users/" + SharedTestData.getUserId() + "/courses/enrolled/", SharedTestData.getToken());
        int enrolledCourseiD = ResponseUtils.getIntFromResponse("[0].id");
        logger.info("The enrolledCourseId is -> {}", enrolledCourseiD);
        SharedTestData.setEnrolledCourseId(enrolledCourseiD);
    }

    @Then("Enroll course for non-existed user")
    public void enrollCourseForNonExistedUser() {
        RequestsUtils.get("courses", SharedTestData.getToken());
        int courseId = ResponseUtils.getIntFromResponse("content[1].id");
        logger.info("The CourseId is -> {}", courseId);
        pathVar.put("userId", 0);
        pathVar.put("courseId", courseId);
        logger.info("PathVariables are {}", pathVar);
        RequestsUtils.post(Endpoints.ENROLL_COURSE.url, "", pathVar);
    }

    @Then("Enroll non-existed course for the user")
    public void enrollNonExistedCourseForTheUser() {
        pathVar.put("userId", 0);
        pathVar.put("courseId", -1);
        logger.info("PathVariables are {}", pathVar);
        RequestsUtils.post(Endpoints.ENROLL_COURSE.url, "", pathVar);
    }
}

