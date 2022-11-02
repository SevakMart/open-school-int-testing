package steps.mentor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;
import providers.dataProviders.SharedTestData;
import io.cucumber.java.en.When;


public class MentorsStep {

    private Logger logger = LoggerFactory.getLogger(MentorsStep.class);

    @When("Get all mentors")
    public void getAllMentors() {
        RequestsUtils.get("mentors", SharedTestData.getToken());
        logger.info(ResponseUtils.getResponse().extract().response().prettyPrint());
    }
}
