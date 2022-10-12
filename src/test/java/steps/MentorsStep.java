package steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;
import io.cucumber.java.en.When;


public class MentorsStep {

    Logger logger = LoggerFactory.getLogger(MentorsStep.class);

    @When("Get all mentors")
    public void getAllMentors() {
        RequestsUtils.get("mentors", SharedTestData.getToken());
        logger.info(ResponseUtils.getResponse().extract().response().prettyPrint());
    }
}
