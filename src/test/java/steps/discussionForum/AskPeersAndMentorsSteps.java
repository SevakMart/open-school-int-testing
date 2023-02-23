package steps.discussionForum;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import manager.DeleteManager;
import org.apache.commons.lang3.RandomStringUtils;
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
    private String body;
    private String text;

    private final static Logger logger = LoggerFactory.getLogger(RequestsUtils.class);

    @And("Create questions for the specified course")
    public void createQuestionsForTheSpecifiedCourse() {
        text = "QuestionToPeers " + RandomStringUtils.randomAlphabetic(5);
        params.put("text", text);
        logger.info("The question for peers is -> {}", text);
        params.put("courseId", SharedTestData.getCourseId());
        logger.info("The enrolled courseId is -> {}", SharedTestData.getCourseId());
        body = BodyProvider.getBody("", params);
        RequestsUtils.post(Endpoints.ADD_QUESTIONS.url, body);
        SharedTestData.setQuestionIdToThePeers(ResponseUtils.getIntFromResponse(".id"));
    }

    @And("Validate the response body by json schema")
    public void validateTheResponseBodyByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/discussionForum/AddingQuestionToThePeersResponseBodySchema.json");
    }

    @And("Create questions for the non-existed course")
    public void createQuestionsForTheNonExistedCourse() {
        text = "QuestionToPeers " + RandomStringUtils.randomAlphabetic(5);
        params.put("text", text);
        logger.info("The question for peers is -> {}", text);
        params.put("courseId", 0);
        logger.info("The enrolled courseId is -> {}", 0);
        body = BodyProvider.getBody("", params);
        RequestsUtils.post(Endpoints.ADD_QUESTIONS.url, body);
    }

    @Then("Delete the question to the peers")
    public void deleteTheQuestionToThePeers() {
        DeleteManager deleteManager = new DeleteManager();
        deleteManager.deleteQuestionToThePeers(1);
    }

    @And("Create question where the user has up to {int} symbols")
    public void createQuestionWhereTheUserHasUpToSymbols(int symbolsCount) {
        text = RandomStringUtils.randomAlphabetic(5);
        params.put("text", text);
        logger.info("The question for peers is -> {}", text);
        params.put("courseId", SharedTestData.getCourseId());
        logger.info("The enrolled courseId is -> {}", SharedTestData.getCourseId());
        body = BodyProvider.getBody("", params);
        RequestsUtils.post(Endpoints.ADD_QUESTIONS.url, body);
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

    @And("Create questions for the mentor")
    public void createQuestionsForTheMentor() {
        text = "QuestionToPeers " + RandomStringUtils.randomAlphabetic(5);
        params.put("text", text);
        logger.info("The question for peers is -> {}", text);
        params.put("courseId", SharedTestData.getCourseId());
        logger.info("The enrolled courseId is -> {}", SharedTestData.getCourseId());
        body = BodyProvider.getBody("", params);
        RequestsUtils.post(Endpoints.ADD_QUESTIONS_TO_THE_MENTOR.url, body);
    }
}
