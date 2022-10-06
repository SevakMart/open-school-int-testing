package steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;

public class CommonSteps {

    private static final String BASE_URL = "http://open-school-dev.eu-central-1.elasticbeanstalk.com/";
    private static final String BASE_PATH = "api/v1/";

    @Given("Setup Rest Assured")
    public void setupRestAssured() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;
    }
}