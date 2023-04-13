package steps.discussionForum;

import io.cucumber.java.en.Then;
import providers.dataProviders.Endpoints;
import providers.dataProviders.SharedTestData;
import steps.BaseSteps;
import utils.api.RequestsUtils;

public class DeleteQuestionToPeersSteps extends BaseSteps {

    @Then("Deleting the question to the peers")
    public void deletingTheQuestionToThePeers() {
        int enrolledCourseId = SharedTestData.getEnrolledCourseId();
        logger.info("EnrolledCourseId is ---->{}", enrolledCourseId);
        int peersQuestionId = SharedTestData.getQuestionIdToThePeers();
        logger.info("peersQuestionId is ---->{}", peersQuestionId);
        pathVariables.put("enrolledCourseId", enrolledCourseId);
        pathVariables.put("peersQuestionId", peersQuestionId);
        RequestsUtils.delete(Endpoints.DELETE_QUESTION_TO_PEERS.url, pathVariables);
    }

    @Then("Deleting the question to the peers with wrong id")
    public void deletingTheQuestionToThePeersWithWrongId() {
        int enrolledCourseId = SharedTestData.getEnrolledCourseId();
        logger.info("EnrolledCourseId is ---->{}", enrolledCourseId);
        int peersQuestionId = 0;
        logger.info("peersQuestionId is ---->{}", peersQuestionId);
        pathVariables.put("enrolledCourseId", enrolledCourseId);
        pathVariables.put("peersQuestionId", peersQuestionId);
        RequestsUtils.delete(Endpoints.DELETE_QUESTION_TO_PEERS.url, pathVariables);
    }
}
