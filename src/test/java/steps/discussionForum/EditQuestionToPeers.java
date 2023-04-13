package steps.discussionForum;

import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import providers.bodyProviders.BodyProvider;
import providers.dataProviders.Endpoints;
import providers.dataProviders.SharedTestData;
import steps.BaseSteps;
import steps.CommonSteps;
import utils.api.RequestsUtils;

public class EditQuestionToPeers extends BaseSteps {

    private String text;
    private String body;

    @Then("Edit the question to the peers")
    public void editTheQuestionToThePeers() {
        text = "UpdatedQuestionToPeers " + RandomStringUtils.randomAlphabetic(5);
        logger.info("The updated question for peers is -> {}", text);
        int enrolledCourseId = SharedTestData.getEnrolledCourseId();
        logger.info("EnrolledCourseId is ---->{}", enrolledCourseId);
        int peersQuestionId = SharedTestData.getQuestionIdToThePeers();
        logger.info("peersQuestionId is ---->{}", peersQuestionId);
        pathVariables.put("enrolledCourseId", enrolledCourseId);
        pathVariables.put("peersQuestionId", peersQuestionId);
        params.put("text", text);
        body = BodyProvider.getBody("questionToPeers", params);
        RequestsUtils.put(Endpoints.EDIT_QUESTIONS_TO_PEERS.url, body, pathVariables);
    }

    @Then("Edit the question to the peers with wrong id")
    public void editTheQuestionToThePeersWithWrongId() {
        text = "UpdatedQuestionToPeers " + RandomStringUtils.randomAlphabetic(5);
        logger.info("The updated question for peers is -> {}", text);
        int enrolledCourseId = SharedTestData.getEnrolledCourseId();
        logger.info("EnrolledCourseId is ---->{}", enrolledCourseId);
        logger.info("peersQuestionId is ---->{}", 0);
        pathVariables.put("enrolledCourseId", enrolledCourseId);
        pathVariables.put("peersQuestionId", 0);
        params.put("text", text);
        body = BodyProvider.getBody("questionToPeers", params);
        RequestsUtils.put(Endpoints.EDIT_QUESTIONS_TO_PEERS.url, body, pathVariables);
    }
}
