package steps.discussionForum;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import providers.bodyProviders.BodyProvider;
import providers.dataProviders.Endpoints;
import providers.dataProviders.SharedTestData;
import steps.BaseSteps;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

public class AnswerToPeersQuestionSteps extends BaseSteps {

    private String text;
    private String body;

    private final static Logger logger = LoggerFactory.getLogger(RequestsUtils.class);

    @Then("Add answer to the peers question")
    public void addAnswerToThePeersQuestion() {
        text = "AnswerToPeersQuestion " + RandomStringUtils.randomAlphabetic(5);
        params.put("text", text);
        logger.info("The answer for the peers question is -> {}", text);
        int questionId = SharedTestData.getQuestionIdToThePeers();
        logger.info("The questionId is -> {}", questionId);
        params.put("questionId", questionId);
        body = BodyProvider.getBody("answerToPeers", params);
        int enrolledCourseId = SharedTestData.getEnrolledCourseId();
        pathVariables.put("enrolledCourseId", enrolledCourseId);
        logger.info("EnrolledCourseId is ---->{}", enrolledCourseId);
        RequestsUtils.post(Endpoints.ADD_ANSWER_FOR_THE_PEERS_QUESTION.url, body, pathVariables);
        SharedTestData.setAnswerIdToThePeers(ResponseUtils.getIntFromResponse("id"));
    }

    @And("Validate the answer response body by json schema")
    public void validateTheAnswerResponseBodyByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/discussionForum/answerToPeersQuestionResponseBodySchema.json");
    }

    @Then("Add answer to not existing question")
    public void addAnswerToNotExistingQuestion() {
        text = "AnswerToPeersQuestion ";
        params.put("text", text);
        logger.info("The answer for the peers question is -> {}", text);
        logger.info("The questionId is -> {}", 0);
        params.put("questionId", 0);
        String body = BodyProvider.getBody("answerToPeers", params);
        int enrolledCourseId = SharedTestData.getEnrolledCourseId();
        pathVariables.put("enrolledCourseId", enrolledCourseId);
        logger.info("EnrolledCourseId is ---->{}", enrolledCourseId);
        RequestsUtils.post(Endpoints.ADD_ANSWER_FOR_THE_PEERS_QUESTION.url, body, pathVariables);
    }

    @Then("Add answer to not enrolled course")
    public void addAnswerToNotEnrolledCourse() {
        text = "AnswerToPeersQuestion ";
        params.put("text", text);
        logger.info("The answer for the peers question is -> {}", text);
        int questionId = SharedTestData.getQuestionIdToThePeers();
        logger.info("The questionId is -> {}", questionId);
        params.put("questionId", questionId);
        body = BodyProvider.getBody("answerToPeers", params);
        pathVariables.put("enrolledCourseId", 1);
        logger.info("EnrolledCourseId is ---->{}", 1);
        RequestsUtils.post(Endpoints.ADD_ANSWER_FOR_THE_PEERS_QUESTION.url, body, pathVariables);
    }

    @When("Create answer where the user has up to {int} symbols")
    public void createAnswerWhereTheUserHasUpToSymbolsCountSymbols(int symbolCount) {
        text = RandomStringUtils.randomAlphabetic(symbolCount);
        params.put("text", text);
        logger.info("The answer for the peers question is -> {}", text);
        int questionId = SharedTestData.getQuestionIdToThePeers();
        logger.info("The questionId is -> {}", questionId);
        params.put("questionId", questionId);
        body = BodyProvider.getBody("answerToPeers", params);
        int enrolledCourseId = SharedTestData.getEnrolledCourseId();
        pathVariables.put("enrolledCourseId", enrolledCourseId);
        logger.info("EnrolledCourseId is ---->{}", enrolledCourseId);
        RequestsUtils.post(Endpoints.ADD_ANSWER_FOR_THE_PEERS_QUESTION.url, body, pathVariables);
    }
}
