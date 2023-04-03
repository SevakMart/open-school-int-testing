package steps.discussionForum;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import manager.DeleteManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import providers.bodyProviders.BodyProvider;
import providers.dataProviders.Endpoints;
import providers.dataProviders.SharedTestData;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

public class AskPeersAndMentorsSteps {

    private final Map<String, Object> params = new HashMap<>();

    private final Map<String, Object> pathVariables = new HashMap<>();

    private String text;
    private String body;

    private final static Logger logger = LoggerFactory.getLogger(RequestsUtils.class);

    @And("Create questions for the specified course")
    public void createQuestionsForTheSpecifiedCourse() {
        text = "QuestionToPeers " + RandomStringUtils.randomAlphabetic(5);
        params.put("text", text);
        logger.info("The question for peers is -> {}", text);
        String body = BodyProvider.getBody("questionToPeers", params);
        int enrolledCourseId = SharedTestData.getEnrolledCourseId();
        pathVariables.put("enrolledCourseId", enrolledCourseId);
        logger.info("EnrolledCourseId is ---->{}", enrolledCourseId);
        RequestsUtils.post(Endpoints.ADD_QUESTIONS.url, body, pathVariables);
        SharedTestData.setQuestionIdToThePeers(ResponseUtils.getIntFromResponse("id"));
    }

    @And("Validate the response body by json schema")
    public void validateTheResponseBodyByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/discussionForum/askToPeersResponseBodySchema.json");
    }

    @Then("Delete the question to the peers")
    public void deleteTheQuestionToThePeers() {
        DeleteManager deleteManager = new DeleteManager();
        deleteManager.deleteQuestionToThePeers(SharedTestData.getQuestionIdToThePeers());
        deleteManager.deleteEnrolledCourse(SharedTestData.getEnrolledCourseId());
    }

    @And("Create questions for the non-existed course")
    public void createQuestionsForTheNonExistedCourse() {
        text = "QuestionToPeers " + RandomStringUtils.randomAlphabetic(5);
        params.put("text", text);
        logger.info("The question for peers is -> {}", text);
        pathVariables.put("enrolledCourseId", 0);
        body = BodyProvider.getBody("questionToPeers", params);
        RequestsUtils.post(Endpoints.ADD_QUESTIONS.url, body, pathVariables);
    }

    @And("Create question where the user has up to {int} symbols")
    public void createQuestionWhereTheUserHasUpToSymbols(int symbolsCount) {
        text = RandomStringUtils.randomAlphabetic(symbolsCount);
        logger.info("The question for peers is -> {}", text);
        params.put("text", text);
        body = BodyProvider.getBody("questionToPeers", params);
        int enrolledCourseId = SharedTestData.getEnrolledCourseId();
        pathVariables.put("enrolledCourseId", enrolledCourseId);
        logger.info("EnrolledCourseId is ---->{}", enrolledCourseId);
        RequestsUtils.post(Endpoints.ADD_QUESTIONS.url, body, pathVariables);
    }

    @And("Creation questions for the mentor with invalid course")
    public void creationQuestionsForTheMentorWithInvalidCourse() {
        text = "QuestionToTheMentors " + RandomStringUtils.randomAlphabetic(5);
        params.put("text", text);
        logger.info("The question for peers is -> {}", text);
        params.put("courseId", 0);
        logger.info("The enrolled courseId is -> {}", 0);
        body = BodyProvider.getBody("", params);
        RequestsUtils.post(Endpoints.ADD_QUESTIONS.url, body);
    }

    @And("Validate error message when course is not enrolled")
    public void validateErrorMessageWhenCourseIsNotEnrolled() {
        String actualResponseMessage = ResponseUtils.getStringFromResponse("message");
        logger.info("The actual response message is -> {}", actualResponseMessage);
        String expectedResponseMessage = "permission.denied";
        logger.info("The expected response message is -> {}", expectedResponseMessage);
        Assertions.assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);
    }

    @And("Create questions for the not enrolled course")
    public void createQuestionsForTheNotEnrolledCourse() {
        text = "QuestionToPeers " + RandomStringUtils.randomAlphabetic(5);
        params.put("text", text);
        logger.info("The question for peers is -> {}", text);
        pathVariables.put("enrolledCourseId", 3);
        body = BodyProvider.getBody("questionToPeers", params);
        RequestsUtils.post(Endpoints.ADD_QUESTIONS.url, body, pathVariables);
    }

    @Then("Validate error message about question length")
    public void validateErrorMessageAboutQuestionLength() {
        String actualResponseMessage = ResponseUtils.getStringFromResponse("text");
        logger.info("The actual response message is -> {}", actualResponseMessage);
        String expectedResponseMessage = "Maximum 500 symbols are allowed";
        logger.info("The expected response message is -> {}", expectedResponseMessage);
        Assertions.assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);
    }
}
