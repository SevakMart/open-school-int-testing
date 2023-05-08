package steps.discussionForum;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import manager.GettingIdManager;
import providers.dataProviders.Endpoints;
import providers.dataProviders.SharedTestData;
import steps.BaseSteps;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;

public class GettingQuestionsAndAnswersByIdSteps extends BaseSteps {

    @When("Get question by Id")
    public void getQuestionById() {
        int enrolledCourseId = SharedTestData.getEnrolledCourseId();
        logger.info("EnrolledCourseId is ---->{}", enrolledCourseId);
        int peersQuestionId = SharedTestData.getQuestionIdToThePeers();
        logger.info("peersQuestionId is ---->{}", peersQuestionId);
        pathVariables.put("enrolledCourseId", enrolledCourseId);
        pathVariables.put("peersQuestionId", peersQuestionId);
        RequestsUtils.get(Endpoints.GET_QUESTION_TO_PEERS_BY_ID.url,SharedTestData.getToken(),pathVariables);
    }

    @And("Validate the provided question response body by json schema")
    public void validateTheProvidedQuestionResponseBodyByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/discussionForum/GetQuestionToPeersResponseSchema.json");
    }

    @When("Get question by the wrong questionId")
    public void getQuestionByTheWrongQuestionId() {
        int enrolledCourseId = SharedTestData.getEnrolledCourseId();
        logger.info("EnrolledCourseId is ---->{}", enrolledCourseId);
        pathVariables.put("enrolledCourseId", enrolledCourseId);
        pathVariables.put("peersQuestionId", 0);
        RequestsUtils.get(Endpoints.GET_QUESTION_TO_PEERS_BY_ID.url,SharedTestData.getToken(),pathVariables);
    }

    @When("Get question by the wrong enrolledCourseId")
    public void getQuestionByTheWrongEnrolledcourseId() {
        GettingIdManager gettingIdManager = new GettingIdManager();
        int peersQuestionIdFromDB = gettingIdManager.getFromPeersQuestionTableFirstId();
        logger.info("EnrolledCourseId is ---->{}", 0);
        pathVariables.put("enrolledCourseId", 0);
        logger.info("PeersQuestionIdFromDB is ------>{}", peersQuestionIdFromDB);
        pathVariables.put("peersQuestionId", peersQuestionIdFromDB);
        RequestsUtils.get(Endpoints.GET_QUESTION_TO_PEERS_BY_ID.url,SharedTestData.getToken(),pathVariables);
    }

    @When("Get answer by Id")
    public void getAnswerById() {
        int enrolledCourseId = SharedTestData.getEnrolledCourseId();
        logger.info("EnrolledCourseId is ---->{}", enrolledCourseId);
        int peersAnswerId = SharedTestData.getAnswerIdToThePeers();
        logger.info("peersAnswerId is ---->{}", peersAnswerId);
        pathVariables.put("enrolledCourseId", enrolledCourseId);
        pathVariables.put("answerId", peersAnswerId);
        RequestsUtils.get(Endpoints.GET_ANSWER_TO_PEERS_QUESTION_BY_ID.url,SharedTestData.getToken(),pathVariables);
    }

    @And("Validate the provided answer response body by json schema")
    public void validateTheProvidedAnswerResponseBodyByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/discussionForum/GetAnswerToPeersResponseSchema.json");
    }
}
