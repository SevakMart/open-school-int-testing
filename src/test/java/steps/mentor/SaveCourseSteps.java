package steps.mentor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import providers.bodyProviders.BodyProvider;
import providers.dataProviders.SharedTestData;
import providers.dataProviders.TestDataProvider;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

public class SaveCourseSteps {

    private final static Logger logger = LoggerFactory.getLogger(RequestsUtils.class);

    private final Map<String, Object> params = new HashMap<>();

    @Then("Save a course for mentor")
    public void saveACourseForMentor() {
        RequestsUtils.get("courses", SharedTestData.getToken());
        int courseId = ResponseUtils.getIntFromResponse("content[0].id");
        logger.info("The course Id is {}", courseId);
        params.put("courseId", courseId);
        String body = BodyProvider.getBody("courseId", params);
        RequestsUtils.post("users/" + SharedTestData.getUserId() + "/courses/saved", body);
        SharedTestData.setCourseId(ResponseUtils.getIntFromResponse("id"));
        logger.info("Send to SharedTestData the courseId from the response -> {} ", ResponseUtils.getIntFromResponse("id"));
    }

    @Then("Validate save course for mentors response body by jsonSchema")
    public void validateSaveCourseForMentorsResponseBodyByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/usersSchemas/SaveCourseForMentorsResponseBodySchema.json");
    }

    @And("Get mentor's courses")
    public void getMentorSCourses() {
        RequestsUtils.get("mentors/" + SharedTestData.getUserId() + "/courses", SharedTestData.getToken());
        logger.info("Mentor's id is {}", SharedTestData.getUserId());
    }

    @Then("Save a not-existing course for a mentor")
    public void saveANotExistingCourseForAMentor() {
        logger.info("The course Id is {}", TestDataProvider.getPropertyValue("not-existingCourseId"));
        params.put("courseId", TestDataProvider.getPropertyValue("not-existingCourseId"));
        String body = BodyProvider.getBody("courseId", params);
        RequestsUtils.post("users/" + SharedTestData.getUserId() + "/courses/saved", body);
    }

    @Then("Validate error message about saving not-existing course for a mentor")
    public void validateErrorMessageAboutSavingNotExistingCourseForAMentor() {
        logger.info("The response message is -> {}", ResponseUtils.getStringFromResponse("message"));
        Assertions.assertThat(ResponseUtils.getStringFromResponse("message")).isEqualTo("Incorrect argument");
    }

    @When("Get not-existing mentor's courses")
    public void getNotExistingMentorSCourses() {
        RequestsUtils.get("mentors/" + TestDataProvider.getPropertyValue("invalidMentorId") + "/courses", SharedTestData.getToken());
        logger.info("Mentor's id is {}", TestDataProvider.getPropertyValue("invalidMentorId"));
    }

    @And("Validate error message about getting not-existing mentor's courses")
    public void validateErrorMessageAboutGettingNotExistingMentorSCourses() {
        logger.info("The response message is -> {}", ResponseUtils.getStringFromResponse("message"));
        Assertions.assertThat(ResponseUtils.getStringFromResponse("message")).isEqualTo("Incorrect argument");
    }

    @And("Delete the course saved by the mentor")
    public void deleteTheCourseSavedByTheMentor() {
        RequestsUtils.delete("users/" + SharedTestData.getUserId() + "/courses/" + SharedTestData.getCourseId() + "/saved");
    }

    @Then("Check that saved course is deleted")
    public void checkThatSavedCourseIsDeleted() {
        RequestsUtils.get("users/" + SharedTestData.getUserId() + "/courses/saved", SharedTestData.getToken());
        logger.info("The content size is -> {}", ResponseUtils.getListFromResponseWithoutType("content").size());
        Assertions.assertThat(ResponseUtils.getListFromResponseWithoutType("content").size()).isEqualTo(0);
    }
}
