package steps;

import utils.ResponseUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonSteps {

    private static final String BASE_URL = "http://open-school-dev.eu-central-1.elasticbeanstalk.com/";
    private static final String BASE_PATH = "api/v1/";
    private final Logger logger = LoggerFactory.getLogger(CommonSteps.class);

    @Given("Setup Rest Assured")
    public void setupRestAssured() {
        logger.info("The BASE_URL is {}", BASE_URL);
        logger.info("The BASE_PATH is {}", BASE_PATH);
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;
    }

    @Then("Status code should be {int}")
    public void statusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = ResponseUtils.getStatusCodeFromResponse();
        logger.info("The actualStatusCode is {}", actualStatusCode);
        Assertions.assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
    }

    @Then("Validate entity creation status")
    public void validateEntityCreationStatus() {
        int actualStatusCode = ResponseUtils.getStatusCodeFromResponse();
        Assertions.assertThat(actualStatusCode).isEqualTo(HttpStatus.SC_CREATED);
    }
}
