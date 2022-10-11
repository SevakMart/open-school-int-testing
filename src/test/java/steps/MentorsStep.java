package steps;

import business.RequestsUtils;
import business.ResponseUtils;
import business.SharedData;
import io.cucumber.java.en.When;


public class MentorsStep {
    @When("Get all mentors")
    public void getAllMentors() {
        RequestsUtils.get("mentors", SharedData.getToken());
        System.out.println(ResponseUtils.getResponse().extract().response().prettyPrint());
    }
}
