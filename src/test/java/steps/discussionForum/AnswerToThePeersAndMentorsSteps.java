package steps.discussionForum;

import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import providers.bodyProviders.BodyProvider;
import providers.dataProviders.Endpoints;
import providers.dataProviders.SharedTestData;
import utils.api.RequestsUtils;

import java.util.HashMap;
import java.util.Map;

public class AnswerToThePeersAndMentorsSteps {

    private final Map<String, Object> params = new HashMap<>();
    private String body;
    private String text;

    private final static Logger logger = LoggerFactory.getLogger(RequestsUtils.class);

    @Then("Add answer for the just creating question")
    public void addAnswerForTheJustCreatingQuestion() {
        params.put("text", RandomStringUtils.randomAlphabetic(5));
        logger.info("The question for peers is -> {}", text);
        params.put("questionId", SharedTestData.getQuestionIdToThePeers());
        logger.info("The just created questionId is -> {}", SharedTestData.getQuestionIdToThePeers());
        body = BodyProvider.getBody("answer", params);
        RequestsUtils.post(Endpoints.ADD_QUESTIONS.url + Endpoints.ADD_ANSWER_FOR_THE_PEERS_Question, body);
    }
}
